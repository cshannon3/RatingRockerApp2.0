/*package com.ratingrocker.ratingrockerapp

import android.content.Context
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.SimpleAdapter
import android.widget.TextView
import java.util.*

public class SongListAdapter : SimpleAdapter {

    var songList = ArrayList<Basicsong>()
    private var context: Context? = null

    constructor(context: Context, SongList: ArrayList<Basicsong>) : super() {
        this.songList = songList
        this.context = context
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {

        val view: View?
        val vh: ViewHolder
        //p = position;
        if (convertView == null) {
            view = View?.layoutInflater.inflate(R.layout.note, parent, false)
            vh = ViewHolder(view)
            view.tag = vh
            Log.i("JSA", "set Tag for ViewHolder, position: " + position)
        } else {
            view = convertView
            vh = view.tag as ViewHolder
        }

        vh.tvTitle.text = songList[position]._songname
        //vh.tvContent.text = songList[position].
        vh.queuenum.text = position.toString()
        vh.playbtn.setTag(R.integer.play_btn_view, convertView)
        vh.playbtn.setTag(R.integer.play_btn_pos, position)
        vh.upbtn.setTag(R.integer.btn_plus_view, convertView)
        vh.upbtn.setTag(R.integer.btn_plus_pos, position)
        vh.upbtn.setOnClickListener{onClick(vh.queuenum)

        }
        vh.downbtn.setOnClickListener {(vh.queuenum)
        }
        vh.playbtn.setOnClickListener{onClick(getView(position, convertView,parent))}


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
}

private class ViewHolder(view: View?) {
    val tvTitle: TextView = view?.findViewById<TextView>(R.id.tvTitle) as TextView
    val tvContent: TextView = view?.findViewById<TextView>(R.id.tvContent) as TextView
    val upbtn: Button = view?.findViewById<Button>(R.id.ivup) as Button
    val downbtn: Button = view?.findViewById<Button>(R.id.ivdown) as Button
    val queuenum: TextView = view?.findViewById<TextView>(R.id.queuenumber) as TextView
    val playbtn: Button = view?.findViewById<Button>(R.id.playbutton) as Button

    // init {
    //  this.tvTitle = view?.findViewById(R.id.tvTitle) as TextView
    //  this.tvContent = view?.findViewById(R.id.tvContent) as TextView
    //}

}*/