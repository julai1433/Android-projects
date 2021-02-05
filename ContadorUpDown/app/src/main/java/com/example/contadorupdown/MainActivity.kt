package com.example.contadorupdown

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner

class MainActivity : AppCompatActivity(),AdapterView.OnItemSelectedListener {

    var Estados = arrayOf("Aguascalientes","Baja California","B.C.S.","Chihuahua","CdMx")
    var spnKotlin: Spinner = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    spnKotlin = this.spinnerKotlin
    spnKotlin!!.SetOnItemSelectedListener(this)
    val aa = ArrayAdapter(this, android.R.layout.simple_spinner_item, Estados)
    aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdwon_item)
    spnKotlin!!.setAdapter(aa)



}
