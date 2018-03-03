package com.ratingrocker.ratingrockerapp

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import java.util.*
import kotlin.collections.ArrayList


class FilterListFragment: View.OnClickListener, android.support.v4.app.Fragment() {

    var resultsView: ListView?= null
    var filtsongList: MutableList<Basicsong>? = java.util.ArrayList()
    var songreqids: ArrayList<String> = java.util.ArrayList()
   // var songreqlist: ArrayList<String>()
   // internal var mRecyclerView: RecyclerView? = null

   // private var mAdapter: LineAdapter? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val v = inflater!!.inflate(R.layout.filter_list_fragment_layout, container, false)
        resultsView = v.findViewById<View>(R.id.results_list2) as ListView
        if (filtsongList==null){
            filtsongList = (activity as PlayerActivity).sendfilt()
            Log.e("f", filtsongList!!.get(1)._songname)
        }
        val songlistAdapter = SongListAdapter(activity, filtsongList as ArrayList<Basicsong>)
        resultsView!!.adapter = songlistAdapter
        resultsView!!.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, i, l ->
            Log.e("Item", filtsongList!![i]._songname)
            //(activity as PlayerActivity).playsong(songreqids[i])
        }
       /* resultsView.onItemLongClickListener = AdapterView.OnItemLongClickListener { adapterView, view, i, l ->
            Log.e("Item", i.toString())

            //(activity as PlayerActivity).queuesong(filtsongList[i]._song_id)

        }*/
       /* val listbar = Arrays.asList(v.findViewById<View>(R.id.listplaybutton) as Button, v.findViewById<View>(R.id.listshuffbutton) as Button, v.findViewById<View>(R.id.playlistcreatebutton) as Button)
        for (b in listbar.indices) {
            listbar[b].setOnClickListener {
                if (b < 2) {
                    if (filtsongList!!.size != 0) {
                        for (p in filtsongList!!.indices) {
                            // String rating = String.valueOf(reqsongs.get(p).get_ratingval());

                            songreqids.add(filtsongList!![p]._song_id)
                        }
                        (activity as PlayerActivity).setqueue(songreqids, b)
                    }
                }}}
*/
        return v
    }
    override fun onClick(v: View?) {

        when (v?.id) {
        }
        }
    inner class SongListAdapter(context: Context, songList: ArrayList<Basicsong>) : BaseAdapter() {

        var  songList = ArrayList<Basicsong>()
        private var context: Context? = null
        // @RequiresApi(Build.VERSION_CODES.O)
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {

            val view: View?
            val vh: ViewHolder
            //p = position;
            if (convertView == null) {
                view = layoutInflater.inflate(R.layout.filter_list_item_layout, parent, false)
                vh = ViewHolder(view)
                view?.tag = vh
                Log.i("JSA", "set Tag for ViewHolder, position: " + position)
            } else {
                view = convertView
                vh = view.tag as ViewHolder
            }
            vh.upbtn.setTag(R.integer.btn_plus_view, convertView)
            vh.upbtn.setTag(R.integer.btn_plus_pos, position)

            vh.songTitle.text = songList[position]._songname
            vh.tvContent.text = songList[position]._song_id
            vh.queuenum.text = position.toString()
            vh.playbtn.setTag(R.integer.play_btn_view, convertView)
            vh.playbtn.setTag(R.integer.play_btn_pos, position)


            vh.deletebtn.setOnClickListener { (activity as PlayerActivity).deletesong(songList[position]._song_id)
            }
            vh.playbtn.setOnClickListener { (activity as PlayerActivity).playsong(songList[position]._song_id) }


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
        val songTitle: TextView = view?.findViewById<TextView>(R.id.songTitle) as TextView
        val tvContent: TextView = view?.findViewById<TextView>(R.id.tvContent) as TextView
        val upbtn: Button = view?.findViewById<Button>(R.id.fulldata) as Button
        val deletebtn: Button = view?.findViewById<Button>(R.id.delete) as Button
        val queuenum: TextView = view?.findViewById<TextView>(R.id.queuenumber) as TextView
        val playbtn: Button = view?.findViewById<Button>(R.id.playbutton) as Button

        // init {
        //  this.tvTitle = view?.findViewById(R.id.tvTitle) as TextView
        //  this.tvContent = view?.findViewById(R.id.tvContent) as TextView
        //}

    }
    /*fun addfiltersongs2(reqsongs: List<List<String>>) {

        val adapter = ArrayAdapter(activity,
                R.layout.spinner_item, reqsongs[0])
        resultsView.adapter = adapter
        songreqids = reqsongs[1]
    }*/

    fun addfiltersongs( reqsongs: List<Basicsong>) {

        filtsongList = reqsongs as java.util.ArrayList
        val songlistAdapter = SongListAdapter(activity, filtsongList as ArrayList<Basicsong>)
        resultsView!!.adapter = songlistAdapter

        /* val songreqlist2 = ArrayList<String>()
         val songreqids2 = ArrayList<String>()

         if (reqsongs.size != 0) {

             for (p in reqsongs.indices) {

                 // String rating = String.valueOf(reqsongs.get(p).get_ratingval());
                 songreqlist2.add(reqsongs[p]._songname)
                 songreqids2.add(reqsongs[p]._song_id)
             }
             val adapter = ArrayAdapter(activity,
                     R.layout.spinner_item, songreqlist2)
             resultsView.adapter = adapter

             songreqlist = songreqlist2
             songreqids = songreqids2
         }*/
    }

  /*  private fun setupRecycler() {

        // Configurando o gerenciador de layout para ser uma lista.
        val layoutManager = LinearLayoutManager(activity)
        mRecyclerView!!.layoutManager = layoutManager

        // Adiciona o adapter que irá anexar os objetos à lista.
        // Está sendo criado com lista vazia, pois será preenchida posteriormente.
        mAdapter = LineAdapter(ArrayList<Any>(0))
        mRecyclerView!!.adapter = mAdapter

        // Configurando um dividr entre linhas, para uma melhor visualização.
        mRecyclerView!!.addItemDecoration(
                DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
    }*/
}




