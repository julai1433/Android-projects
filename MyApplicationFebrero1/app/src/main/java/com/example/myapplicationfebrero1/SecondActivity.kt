package com.example.myapplicationfebrero1

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val ir = intent.extras
        val rMessage = ir.getString("etMessage") //Se recupera el mensaje capturado en MainActivity en el editText de PrimerAPellido
        tvS.setText(rMessage)

        btnS.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                val i = Intent(this@SecondActivity,ThirdActivity::class.java) //Creamos objecto de la clase Intent, pasa de la Second a la Third activiyy
                startActivity(i)

        }})
    }
}
