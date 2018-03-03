package com.ratingrocker.ratingrockerapp

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.crystal.crystalrangeseekbar.widgets.CrystalRangeSeekbar
import com.ratingrocker.ratingrockerapp.DataObjects.filterrangeseekdata
import java.util.*



class FilterFragment3 : View.OnClickListener, android.support.v4.app.Fragment() {


    var maingenrespinners2: List<Spinner>? = java.util.ArrayList()
    var genrespinners2: List<MultiSelectionSpinner>? = java.util.ArrayList()
    var genrelist2: List<List<String>>? = java.util.ArrayList()
    var maingenrelist2: List<String>? = java.util.ArrayList()
    var spotvals: MutableList<Int>? = java.util.ArrayList()
    var genvals: MutableList<Int>? = java.util.ArrayList()
    var rvals: MutableList<MutableList<Int>>? = java.util.ArrayList()
    var hitlist: MutableList<MutableList<Int>>? = java.util.ArrayList()
    var filterlists: List<ListView>? = java.util.ArrayList()
    var data: List<List<filterrangeseekdata>> = java.util.ArrayList()
    var filterrangebars = ArrayList<List<CrystalRangeSeekbar>>()
    val TAG = "FilterFragment"
    // private val data = HashMap<String, List<Int>

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val v = inflater!!.inflate(R.layout.filter_layout4, container, false)


        maingenrespinners2 = Arrays.asList(v.findViewById<View>(R.id.maingenre12) as Spinner, v.findViewById<View>(R.id.maingenre22) as Spinner, v.findViewById<View>(R.id.maingenre32) as Spinner)
        genrespinners2 = Arrays.asList(v.findViewById<View>(R.id.genre1spinner2) as MultiSelectionSpinner, v.findViewById<View>(R.id.genre2spinner2) as MultiSelectionSpinner, v.findViewById<View>(R.id.genre3spinner2) as MultiSelectionSpinner)
        genrelist2 = Arrays.asList(Arrays.asList(" ", "-indie", "-pop", "-edm", "-hip hop"), Arrays.asList(" ", "-indie", "-alternative", "-classic", "-modern"), Arrays.asList(" ", "-edm", "-indie", "-funk", "-soul"),
                Arrays.asList(" ", "-house", "-tropical", "-dance", "-chillwave"), Arrays.asList(" ", " indie", "-pop", "-dance", "-downbeat"), Arrays.asList(" ", "-reggae", "-instrumental", "-country", "-foreign"))
        maingenrelist2 = Arrays.asList("Genre", " Rap", " Rock", " Pop", " EDM", " Indie", " Other")
        data = Arrays.asList((Arrays.asList(filterrangeseekdata("Popularity", 0f, 100f, 20f),
                filterrangeseekdata("Added Month", 1f, 12f, 0f), filterrangeseekdata("Release Year", 2010f, 2018f, 0f))
                ), Arrays.asList(filterrangeseekdata("Chill Rating", 0f, 100f, 20f),
                filterrangeseekdata("Party", 0f, 100f, 20f), filterrangeseekdata("Mellow", 0f, 100f, 20f))
                , Arrays.asList(filterrangeseekdata("Tempo", 0f, 160f, 20f), filterrangeseekdata("Energy", 0f, 100f, 20f),
                filterrangeseekdata("Valence", 0f, 100f, 20f), filterrangeseekdata("Danceability", 0f, 100f, 20f))
        )
        for (y in data.indices) {
            var templist: MutableList<Int> = java.util.ArrayList()
            for (x in data.get(y).indices) {
                templist.add(data[y][x].minval.toInt())
                templist.add(data[y][x].maxval.toInt())
            }
            rvals!!.add(templist)
            var emp: MutableList<Int> = java.util.ArrayList()
            emp.add(-1)
            hitlist!!.add(emp)
        }

        var titles: List<String> = Arrays.asList("General", "User", "Extras")
        for (y in titles.indices) {
            var generalAdapter = TitleFilterAdapter(activity, titles.get(y), y)

            var filtView: ListView = v.findViewById(R.id.filter_list)
            filtView!!.adapter = generalAdapter
        }
        val dataAdapter = ArrayAdapter(activity,
                R.layout.spinner_item, maingenrelist2)

        for (y in maingenrespinners2!!.indices) {
            maingenrespinners2!![y].adapter = dataAdapter


            //genrespinners.get(y).setSelection(0);

            maingenrespinners2!![y].onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adapterView: AdapterView<*>, view: View, i: Int, l: Long) {
                    if (i != 0) {
                        genrespinners2!![y].setItems(genrelist2!![i - 1])
                        genrespinners2!![y].setSelection(0)
                    }
                }

                override fun onNothingSelected(adapterView: AdapterView<*>) {}
            }
        }
        maingenrespinners2!![1].visibility = View.GONE
        genrespinners2!![1].visibility = View.GONE
        maingenrespinners2!![2].visibility = View.GONE
        genrespinners2!![2].visibility = View.GONE

        val addgenrebutton2 = v.findViewById<View>(R.id.addgenrebutton2) as Button
        addgenrebutton2.setOnClickListener {
            var f = 0
            while (f < maingenrespinners2!!.size) {
                // final int j = f;
                if (maingenrespinners2!!.get(f).selectedItemPosition < 1) {
                    maingenrespinners2!![f].visibility = View.VISIBLE
                    genrespinners2!![f].visibility = View.VISIBLE
                    //  genrespinners.get(f).setSelection(0);
                    f = maingenrelist2!!.size
                }
                f++
            }
        }
        val searchbtn = v.findViewById<View>(R.id.searchbtn) as Button
        searchbtn.setOnClickListener { v ->
            Log.e(TAG, rvals.toString())
            Log.e(TAG, hitlist.toString())
            (activity as PlayerActivity).getFilterParams(getfiltergenres(v), rvals, hitlist)
            /*val results = filterresultsFunc(v)
        val handler2 = Handler()
        handler2.postDelayed({
            (activity as PlayerActivity).sendFliterList(results)
            chosenGenres()
        }, 1000)*/
        }
        return v
    }

    override fun onClick(v: View?) {
        when (v?.id) {

        }

    }
    inner class TitleFilterAdapter(context: Context, title: String, y:Int) : BaseAdapter() {
        var title: String = title
        var y: Int = y


        private var context: Context? = null
        // @RequiresApi(Build.VERSION_CODES.O)
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {

            val view: View?
            val vh: TitleViewHolder
            //p = position;
            if (convertView == null) {
                view = layoutInflater.inflate(R.layout.filter_category_layout, parent, false)
                vh = TitleViewHolder(view)
                view?.tag = vh
                Log.i("JSA", "set Tag for ViewHolder, position: " + position)
            } else {
                view = convertView
                vh = view.tag as TitleViewHolder
            }
            // vh.max.setTag(R.integer.btn_plus_view, convertView)
            // vh.max.setTag(R.integer.btn_plus_pos, position)
            vh.title.text = title
            vh.expandbutton.setTag(R.integer.play_btn_view, convertView)
            vh.expandbutton.setTag(R.integer.play_btn_pos, position)
            vh.filtersList.setTag(R.integer.play_btn_view, convertView)
            vh.filtersList.setTag(R.integer.play_btn_pos, position)
            vh.expandbutton.setOnClickListener {
                if (vh.expandbutton.isChecked){
                var filterAdapter = FilterAdapter(activity, data.get(y), y)
                vh.filtersList.adapter = filterAdapter }
           }

            return view
        }

        override fun getItem(position: Int): Any {
            return data[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return data.size
        }

        init {
            this.context = context
        }
    }

    private class TitleViewHolder(view: View?) {
        val title: TextView = view?.findViewById<TextView>(R.id.filtersubtitle) as TextView
        val expandbutton: ToggleButton = view?.findViewById<ToggleButton>(R.id.expandbutton) as ToggleButton
        val filtersList: ListView = view?.findViewById<ListView>(R.id.filter_seekbar_list) as ListView


    }



    inner class FilterAdapter(context: Context, filterList: List<filterrangeseekdata>, y: Int) : BaseAdapter() {

        var filterList: List<filterrangeseekdata> = filterList
        private var group: Int

        private var context: Context? = null
        // @RequiresApi(Build.VERSION_CODES.O)
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {

            val view: View?
            val vh: ViewHolder
            //p = position;
            if (convertView == null) {
                view = layoutInflater.inflate(R.layout.filter_rangefinder_list_item, parent, false)
                vh = ViewHolder(view)
                view?.tag = vh
                Log.i("JSA", "set Tag for ViewHolder, position: " + position)
            } else {
                view = convertView
                vh = view.tag as ViewHolder
            }
            // vh.max.setTag(R.integer.btn_plus_view, convertView)
            // vh.max.setTag(R.integer.btn_plus_pos, position)
            vh.itemtitle.text = filterList[position].name
            vh.min.text = filterList[position].minval.toString()
            vh.max.text = filterList[position].minval.toString()
            vh.rangebar.setTag(R.integer.play_btn_view, convertView)
            vh.rangebar.setTag(R.integer.play_btn_pos, position)
            vh.rangebar.setGap(filterList[position].gap)
            vh.rangebar.setMaxValue(filterList[position].maxval)
            vh.rangebar.setMinValue(filterList[position].minval)
            vh.rangebar.setOnRangeSeekbarChangeListener { minValue, maxValue ->
                vh.min.text = minValue.toString()
                vh.max.text = maxValue.toString()
            };
            vh.rangebar.setOnRangeSeekbarFinalValueListener { minValue, maxValue ->
                Log.d("CRS=>", minValue.toString() + " : " + maxValue.toString())
                var ind: Int = position*2
                rvals!!.get(group).set(ind, minValue.toInt())
                rvals!!.get(group).set(ind+1, maxValue.toInt())
                if (!hitlist!![group].contains(ind)) {
                    hitlist!!.get(group).add(ind)
                }
            }


            return view
        }

        override fun getItem(position: Int): Any {
            return filterList[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return filterList.size
        }

        init {
            this.context = context
            this.group = y
        }
    }

    private class ViewHolder(view: View?) {
        val itemtitle: TextView = view?.findViewById<TextView>(R.id.itemtitle) as TextView
        val min: TextView = view?.findViewById<TextView>(R.id.min) as TextView
        val max: TextView = view?.findViewById<TextView>(R.id.max) as TextView
        val rangebar: CrystalRangeSeekbar = view?.findViewById<CrystalRangeSeekbar>(R.id.filterrangeseekbar) as CrystalRangeSeekbar


    }


    fun getfiltergenres(v: View): List<List<String>> {


        val resultgenres: HashMap<String, String> = java.util.HashMap()
        val userselected = StringBuilder()
        val filtgenres: MutableList<MutableList<String>> = java.util.ArrayList()
        var foundone = false
        val maingenres = Arrays.asList("", " rap", " rock", " pop", " edm", " indie")
        var k = 0
        while (maingenrespinners2!![k].isShown) {
            if (maingenrespinners2!![k].selectedItemPosition > 0) {
                //val gentry: MutableList<String> = java.util.ArrayList();
                // gentry.add(maingenres[maingenrespinners2!![k].selectedItemPosition])
                try {
                    // userselected.append(genrespinners2!![k].selectedStrings)
                    filtgenres.add(Arrays.asList(maingenres[maingenrespinners2!![k].selectedItemPosition], genrespinners2!![k].selectedItemsAsString))
                    resultgenres.put(maingenres[maingenrespinners2!![k].selectedItemPosition], genrespinners2!![k].selectedItemsAsString)

                } catch (e: Exception) {
                    filtgenres.add(Arrays.asList(maingenres[maingenrespinners2!![k].selectedItemPosition], "NA"))
                    resultgenres.put(maingenres[maingenrespinners2!![k].selectedItemPosition], "NA")
                }

            }
            k += 1

        }
        Log.e(TAG, resultgenres.toString())

        return filtgenres
    }
}

/*
        return rvals
        db.getslimrequestlistgen1(vals!![0], vals!![1], addedmonth, releaseyear)

    }
*/