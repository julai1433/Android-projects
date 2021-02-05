package com.example.ninjafruits

import android.content.Intent
import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast.LENGTH_LONG
import android.widget.Toast.makeText
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val fruitNames = arrayOf("apple","grapes","mango","pineapple","strawberry")
    val fruitImages = intArrayOf(R.drawable.apple,R.drawable.grapes,R.drawable.mango,R.drawable.pineapple,R.drawable.strawberry)

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_main)

        var items = ArrayList<MyFruits>()
        items.add(MyFruits("apple",BitmapFactory.decodeResource(resources,R.drawable.apple)))
        items.add(MyFruits("grapes",BitmapFactory.decodeResource(resources,R.drawable.grapes)))
        items.add(MyFruits("mango",BitmapFactory.decodeResource(resources,R.drawable.mango)))
        items.add(MyFruits("pineapple",BitmapFactory.decodeResource(resources,R.drawable.pineapple)))
        items.add(MyFruits("strawberry",BitmapFactory.decodeResource(resources,R.drawable.strawberry)))

        rvFruits.layoutManager=LinearLayoutManager(this@MainActivity, LinearLayout.VERTICAL,false)
        val adapter = MyAdapter(items)
        rvFruits.adapter=adapter


    }
}