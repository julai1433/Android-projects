package com.example.ninjafruits

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

class MyAdapter(val items:ArrayList<MyFruits>):RecyclerView.Adapter<MyAdapter.MyViewHolder> () {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        var v = LayoutInflater.from(p0.context).inflate(R.layout.mylayout,p0,false)
        return MyViewHolder(v)
    }

    override fun getItemCount(): Int {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        return items.size
    }

    override fun onBindViewHolder(p0: MyViewHolder, p1: Int) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        p0.tvI.setText(items[p1].name)
        p0.ivI.setImageBitmap(items[p1].image)

    }

    class MyViewHolder(v:View):RecyclerView.ViewHolder(v){
        var tvI:TextView = itemView.findViewById(R.id.tvFruit)
        var ivI:ImageView = itemView.findViewById(R.id.ivFruit)


    }

}