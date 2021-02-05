package com.example.firstapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var add: (Int, Int)-> = {a,b -> a+b}

        et1.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                //TODO("not implemented") //To change body of create
            }
        })
    }
override fun onTextChanged(s: CharSequence?,start: Int, before: Int, count: Int) {

    var data1S = s
    var data2S = et2.text.toString()
    if (data1S!!.isEmpty()!! || data2S.isEmpty()) {
        Toast.makeText(this, "Is Empty", Toast.LENGTH_LONG).show()
        return
    } else {
        tv.setText("${add(data1S.toString().toInt(), data2S.toInt())}")

    }
}

}
