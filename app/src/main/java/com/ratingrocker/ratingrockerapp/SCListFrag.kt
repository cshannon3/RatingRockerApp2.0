package com.ratingrocker.ratingrockerapp

import android.app.Fragment
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.Toast.makeText
import com.ratingrocker.ratingrockerapp.DataObjects.SCsongdata
import com.ratingrocker.ratingrockerapp.R.id.connect_list2
import com.ratingrocker.ratingrockerapp.R.id.parent
import java.util.*
import kotlin.collections.ArrayList

class SCListFrag : View.OnClickListener, android.support.v4.app.Fragment() {
    var listNotes = ArrayList<SCsongdata>()
    var ConnectList =  ArrayList<Int>()
    var sconnectView: ListView? = null


    var p = 1;
    var t = 0;
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.songconnect_list_frag, container, false)

        val db = MyDBHandler(activity)
        if (listNotes.size<1) {
           // listNotes = db.getsongconnectlist() as ArrayList<Basicsong>
        }
        sconnectView = v.findViewById<ListView>(connect_list2) as ListView
        //listNotes.add(Basicsong(70, "haha", "2"))
       // listNotes.add(Basicsong(75, "haeha", "2"))

        val songlistAdapter = SongListAdapter(activity, listNotes)
        sconnectView!!.adapter= songlistAdapter
       // connect_list2.adapter = songlistAdapter
        sconnectView!!.onItemClickListener = AdapterView.OnItemClickListener { adaptView, view, position, id ->
           p = position
           Log.e("d", p.toString())
            Toast.makeText(activity, "Click on " + listNotes[position].song_name, Toast.LENGTH_SHORT).show()
        }
    return v
    }

    override fun onClick(v: View?) {

        when (v?.id) {
            R.id.queuenumber -> {
                val tv: TextView = v.findViewById<TextView>(R.id.queuenumber) as TextView
                val number = Integer.parseInt(tv.text.toString()) - 1
                Log.e("tv", tv.text.toString())
                tv.text = number.toString()
                v.findViewWithTag<TextView>(R.integer.queue_view)
            }

            else -> {
                Log.e("tv", p.toString())
                val pv: Button = v?.findViewById<Button>(R.id.playbutton) as Button
               // (activity as PlayerActivity).playsong(listNotes.get(tv.text.toString())._song_id)
                val tv: TextView = v.findViewById<TextView>(R.id.queuenumber) as TextView
                val ov: TextView = v.findViewById<TextView>(R.id.tvContent) as TextView
                (activity as PlayerActivity).playsong(ov.text.toString())
                val number = Integer.parseInt(tv.text.toString()) - 1
                Log.e("tv", ov.text.toString())
                // lvNotes.adapter = notesAdapter
            }
        }
    }

    fun onClick2(v: View?) {
        when (v?.id) {
            R.id.queuenumber -> {
                val tv: TextView = v.findViewById<TextView>(R.id.queuenumber) as TextView
                val number = Integer.parseInt(tv.text.toString()) - 1
                Log.e("tv2", tv.text.toString())
                tv.text = number.toString()
            } R.id.tvTitle -> {
           // val tv: TextView = v.findViewWithTag<TextView>(R.integer.queue_view)
           // val number = Integer.parseInt(tv.text.toString()) - 1
            Log.e("tv2", ConnectList.toString())
            //tv.text = number.toString() * /


        }
        }
    }


    inner class SongListAdapter(context: Context, songList: ArrayList<SCsongdata>) : BaseAdapter() {

        var  songList = ArrayList<SCsongdata>()


        private var context: Context? = null
       // @RequiresApi(Build.VERSION_CODES.O)
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {

            val view: View?
            val vh: ViewHolder
            //p = position;
            if (convertView == null) {
                view = layoutInflater.inflate(R.layout.songlist_layout, parent, false)
                vh = ViewHolder(view)
                view?.tag = vh
                Log.i("JSA", "set Tag for ViewHolder, position: " + position)
            } else {
                view = convertView
                vh = view.tag as ViewHolder
            }
           vh.upbtn.setTag(R.integer.btn_plus_view, convertView)
           vh.upbtn.setTag(R.integer.btn_plus_pos, position)

            vh.tvTitle.text = songList[position].song_name
            vh.tvContent.text = songList[position].song_id
            vh.queuenum.text = position.toString()
            vh.playbtn.setTag(R.integer.play_btn_view, convertView)
            vh.playbtn.setTag(R.integer.play_btn_pos, position)
            if (vh.upbtn.isChecked && !ConnectList.contains(songList[position].id)){
                vh.upbtn.isChecked = false
            } else if (ConnectList.contains(songList[position].id)){
                vh.upbtn.isChecked = true
            }
            vh.upbtn.setOnClickListener {
                if (vh.upbtn.isChecked){
                    ConnectList.add(songList[position].id)
            }else {
                    ConnectList.remove(songList[position].id)
            }}
            vh.downbtn.setOnClickListener { onClick2(vh.tvTitle)
            }
            vh.playbtn.setOnClickListener { onClick(getView(position, convertView, parent)) }


            /*  val tempview = vh.downbtn.getTag(R.integer.btn_plus_view) as View
          val tv: TextView = tempview.findViewById<TextView>(R.id.queuenumber) as TextView
          val pos= vh.downbtn.getTag(R.integer.btn_plus_pos) as Int
          val number = Integer.parseInt(tv.text.toString()) -1
          tv.setText(number)
          // MainActivity.modelArrayList.get(pos).setNumber(number)
      */
            return view
        }

        override fun getItem(position: Int): Any {
            return songList[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return songList.size
        }

        init {
            this.songList = songList
            this.context = context
        }
    }

    private class ViewHolder(view: View?) {
        val tvTitle: TextView = view?.findViewById<TextView>(R.id.tvTitle) as TextView
        val tvContent: TextView = view?.findViewById<TextView>(R.id.tvContent) as TextView
        val upbtn: CheckBox = view?.findViewById<CheckBox>(R.id.ivup) as CheckBox
        val downbtn: Button = view?.findViewById<Button>(R.id.ivdown) as Button
        val queuenum: TextView = view?.findViewById<TextView>(R.id.queuenumber) as TextView
        val playbtn: Button = view?.findViewById<Button>(R.id.playbutton) as Button

        // init {
        //  this.tvTitle = view?.findViewById(R.id.tvTitle) as TextView
        //  this.tvContent = view?.findViewById(R.id.tvContent) as TextView
        //}

    }
    fun getList(b:List<SCsongdata> ){
         listNotes = b as ArrayList<SCsongdata>
        val songlistAdapter = SongListAdapter(activity, listNotes)
        sconnectView!!.adapter = songlistAdapter

    }
    fun getSCChecked(): String{
        val p: String = ConnectList.toString()
        ConnectList.clear()
        return p
    }

  /*  override fun addsongconnectlist(currentsong: FullSongData) {

        //        choosenlist.clear();
        val db = MyDBHandler(activity)

        val sclist = db.getsongconnectlist1(currentsong) as ArrayList<Basicsong>
        Collections.sort(sclist, Collections.reverseOrder<String>())
        val sconnectView: ListView = view?.findViewById<ListView>(connect_list2) as ListView
        //listNotes.add(Basicsong(70, "haha", "2"))
        // listNotes.add(Basicsong(75, "haeha", "2"))
        val songlistAdapter = SongListAdapter(activity, sclist)
        sconnectView.adapter= songlistAdapter
        if (sclist.size != 0) {
            for (j in sclist.indices) {
                val id = sclist.get(j).substring(sclist.get(j).indexOf("/") + 1, sclist.get(j).length)
                sconnectids.add(id)
                sconnectlist2.add(sclist.get(j).substring(0, sclist.get(j).indexOf("/")))
                Log.e(sconnectlist2.get(j), sconnectids.get(j))
            }

        }
        val songconnectlist22 = sconnectlist2
        val adapter = ArrayAdapter<String>(activity,
                android.R.layout.simple_list_item_multiple_choice, songconnectlist22)
        sconnectView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE)
        sconnectView.setAdapter(adapter)
    }*/
}