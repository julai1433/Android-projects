package com.example.calculator

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.vieW.View

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnAdd.setOnClickListener(object: View.onClickListener{
            override fun onCLick(v: View) {
                // TODO(reason: "not implemented") //To change body
                var data1S:String = etxData1.text.toString()
                if (data1S.isEmpty()) {
                etxData1.setError("Error")
                }
                else{
                    var data1 = data1S.toInt()
                    var data2S = etxData2.text.toString()
                    var data2 = data2S.toInt()
                    var add = data1 + data2
                    textView6.setText("$add")
                }
            }
        })

        btnSus.setOnClickListener(object: View.onClickListener{
            override fun onCLick(v: View) {
                // TODO(reason: "not implemented") //To change body
                var data1S:String = etxData1.text.toString()
                var data1 = data1S.toInt()
                var data2S = etxData2.text.toString()
                var data2 = data2S.toInt()
                var sus = data1+data2
                textView6.setText("$sus")
            }
        })

        btnMul.setOnClickListener(object: View.onClickListener{
            override fun onCLick(v: View) {
                // TODO(reason: "not implemented") //To change body
                var data1S:String = etxData1.text.toString()
                var data1 = data1S.toInt()
                var data2S = etxData2.text.toString()
                var data2 = data2S.toInt()
                var mul = data1+data2
                textView6.setText("$mul")
            }
        })

        btnDiv.setOnClickListener(object: View.onClickListener{
            override fun onCLick(v: View) {
                // TODO(reason: "not implemented") //To change body
                var data1S:String = etxData1.text.toString()
                var data1 = data1S.toInt()
                var data2S = etxData2.text.toString()
                var data2 = data2S.toInt()
                var div = data1+data2
                textView6.setText("$div")
            }
        })
    }


}
