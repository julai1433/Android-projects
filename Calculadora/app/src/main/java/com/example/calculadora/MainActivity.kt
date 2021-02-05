package com.example.calculadora

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.View.OnClickListener
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),View.OnClickListener {
    override fun onClick(v: View?) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        when(v?.id){
            R.id.btnAdd ->{
                if(!(etxData1.text.toString().isBlank()||etxData2.text.toString().isBlank())) {
                    txtResult.text = "Result: ${etxData1.text.toString().toInt() + etxData2.text.toString().toInt()}"
                }else{
                    showMessage("No Data")
                }
            }
            R.id.btnSus ->{
                if(!(etxData1.text.toString().isBlank()||etxData2.text.toString().isBlank())) {
                    txtResult.text = "Result: ${etxData1.text.toString().toInt() - etxData2.text.toString().toInt()}"
                }else{
                    showMessage("No Data")
                }
            }
            R.id.btnMul ->{
                if(!(etxData1.text.toString().isBlank()||etxData2.text.toString().isBlank())) {
                    txtResult.text = "Result: ${etxData1.text.toString().toInt() * etxData2.text.toString().toInt()}"
                }else{
                    showMessage("No Data")
                }
            }
            R.id.btnDiv ->{ if(!(etxData1.text.toString().isBlank()||etxData2.text.toString().isBlank())) {
                if (etxData2.text.toString().toInt() == 0) {
                    showMessage("Impossible Division")
                } else {
                    txtResult.text =
                        "Result: ${etxData1.text.toString().toFloat() / etxData2.text.toString().toFloat()}"
                }
            }else showMessage("No Data")
            }

        }
    }

    @SuppressLint("SetTextI18n")


/*    object MyOnClick: OnClickListener {
        override fun onClick(v: View?) {
            //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            when(v?.id){
                R.id.btnAdd ->{

                }
                R.id.btnSus ->{ }
                R.id.btnMul ->{ }
                R.id.btnDiv ->{ }

            }
        }
    }*/


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnAdd.setOnClickListener(this)
        btnSus.setOnClickListener(this)
        btnMul.setOnClickListener(this)
        btnDiv.setOnClickListener(this)



/*        btnAdd.setOnClickListener {

            if(!(etxData1.text.toString().isBlank()||etxData2.text.toString().isBlank())) {
                txtResult.text = "Result: ${etxData1.text.toString().toInt() + etxData2.text.toString().toInt()}"
            }else{
                showMessage("No Data")
            }
        }

        btnSus.setOnClickListener {

            if(!(etxData1.text.toString().isBlank()||etxData2.text.toString().isBlank())) {
                txtResult.text = "Result: ${etxData1.text.toString().toInt() - etxData2.text.toString().toInt()}"
            }else{
                showMessage("No Data")
            }
        }

        btnMul.setOnClickListener {

            if(!(etxData1.text.toString().isBlank()||etxData2.text.toString().isBlank())) {
                txtResult.text = "Result: ${etxData1.text.toString().toInt() * etxData2.text.toString().toInt()}"
            }else{
                showMessage("No Data")
            }
        }

        btnDiv.setOnClickListener {
            if(!(etxData1.text.toString().isBlank()||etxData2.text.toString().isBlank())) {
                if (etxData2.text.toString().toInt() == 0) {
                    showMessage("Impossible Division")
                } else {
                    txtResult.text =
                        "Result: ${etxData1.text.toString().toFloat() / etxData2.text.toString().toFloat()}"
                }
            }else{
                showMessage("No Data")
            }

        }*/
    } // end of onCreate

    private fun showMessage(s: String) {
        Toast.makeText(this,s,Toast.LENGTH_SHORT).show()
    }
}