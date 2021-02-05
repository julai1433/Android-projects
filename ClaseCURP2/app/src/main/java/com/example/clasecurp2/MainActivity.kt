package com.example.clasecurp2

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val states = arrayOf("Aguascalientes","Baja Calidornia","Baja California Sur","Campeche","Chihuahua","Ciudad de México")
    val statesA = arrayOf("AG","BC","BCS","CA","CH","DF")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        txtv.setText(states[0]);

    }

    val myAdapter = ArrayAdapter(context: this,android.R.layout.simple_list_item_1,states)
            states.adapter=myAdapter
    states.onItemSelectedListener=(object: AdapterView.OnItemSelectedListener(
        override fun
    ))

}
