package com.example.myapplicationfebrero1

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import android.widget.*
import android.widget.Toast.makeText
import kotlinx.android.synthetic.main.activity_main.*




class MainActivity : AppCompatActivity(),View.OnClickListener{

    override fun onClick(v: View?) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

        when(v?.id) {
            R.id.btnLook ->{
                position = spnState.selectedItemPosition
                if(position>0){
                    setCURP()}else{
                    makeText(this@MainActivity, "Select a state",Toast.LENGTH_SHORT).show()
                }
            }
            R.id.btnClean ->{
                txtResult.text = ""
                firstLastNameIn.setText("")
                secLastnameIn.setText("")
                firstNameIn.setText("")
                secNameIn.setText("")
                ddFechaNac.setText("")
                mmFechaNac.setText("")
                aaFechaNac.setText("")
                spnState.setSelection(0)
                makeText(this@MainActivity, "Data Cleaned", Toast.LENGTH_SHORT).show()
            }
        }
    }

    var states = arrayOf("Select a state","Aguascalientes","Baja California","Baja California Sur","Campeche","Coahuila","Colima","Chiapas","Chihuahua","Distrito Federal","Durango","Guanajuato","Guerrero","Hidalgo","Jalisco","Estado de México","Michoacán","Morelos","Nayarit","Nuevo León","Oaxaca","Puebla","Querétaro","Quintana Roo","San Luis Potosí","Sinaloa","Sonora","Tabasco","Tlaxcala","Veracruz","Yucatán","Zacatecas")
    var statesAbbrev = arrayOf("XX","AG","BC","BS","CC","CL","CM","CS","CH","DF","DG","GT","GR","HG","JC","MC","MN","MS","NT","NL","OC","PL","QT","QR","SP","SL","SR","TC","TL","VZ","YN","ZS")
    var position :Int = 0
    var letraDiez :Char = 'D'


    fun setCURP() {

        var letrasEstado = statesAbbrev[position]

        var firstLast = firstLastNameIn?.text.toString().toUpperCase()
        var secLast = secLastnameIn?.text.toString().toUpperCase()
        var firstName = firstNameIn?.text.toString().toUpperCase()
        var secName = secNameIn?.text.toString().toUpperCase()
        var ddFecha = ddFechaNac?.text.toString()
        var mmFecha = mmFechaNac?.text.toString()
        var aaFecha = aaFechaNac?.text.toString()

        if (firstLast.isNotEmpty()&&secLast.isNotEmpty()&&firstName.isNotEmpty()) {
            if(ddFecha.isNotEmpty()&&mmFecha.isNotEmpty()&&aaFecha.isNotEmpty()&&aaFecha.length==4&&mmFecha.length==2&&ddFecha.length==2&&aaFecha.toInt()<2000&&aaFecha.toInt()>1940&&mmFecha.toInt()<12&&ddFecha.toInt()<31){
                    val letraCero = firstLast[0]
                    var letraUno =
                        'X'                     //La letra 1 se asigna abajo, será la primera vocal interna del primer apellido
                    val letraDos = secLast[0]
                    val letraTres = firstName[0]
                    val letraCuatro = aaFecha[2]
                    val letraCinco = aaFecha[3]
                    val letraSeis = mmFecha[0]
                    val letraSiete = mmFecha[1]
                    val letraOcho = ddFecha[0]
                    val letraNueve = ddFecha[1]
                    //var letraDiez           = 'x'                       //La letra 10 se asigna en rgGender... debe ser H ó M
                    val letraOnce = letrasEstado[0]
                    val letraDoce = letrasEstado[1]
                    var letraTrece = 'X'
                    var letraCatorce = 'X'
                    var letraQuince =
                        'X'                       //Las letras 13,14 y 15 se asignan en los siguientes ciclos

                    var i: Int = 1
                    var max: Int = firstLast.length
                    while (i < max) {
                        letraUno = firstLast[i]
                        when {
                            firstLast[i] == 'A' || firstLast[i] == 'E' || firstLast[i] == 'I' || firstLast[i] == 'O' || firstLast[i] == 'U' -> max =
                                i
                        }
                        i++
                    }

                    i = 1
                    max = firstLast.length
                    while (i < max) {
                        letraTrece = firstLast[i]
                        when {
                            firstLast[i] == 'A' || firstLast[i] == 'E' || firstLast[i] == 'I' || firstLast[i] == 'O' || firstLast[i] == 'U' -> null
                            else -> max = i
                        }
                        i++
                    }

                    i = 1
                    max = secLast.length
                    while (i < max) {
                        letraCatorce = secLast[i]
                        when {
                            secLast[i] == 'A' || secLast[i] == 'E' || secLast[i] == 'I' || secLast[i] == 'O' || secLast[i] == 'U' -> null
                            else -> max = i
                        }
                        i++
                    }

                    i = 1
                    max = firstName.length
                    while (i < max) {
                        letraQuince = firstName[i]
                        when {
                            firstName[i] == 'A' || firstName[i] == 'E' || firstName[i] == 'I' || firstName[i] == 'O' || firstName[i] == 'U' -> null
                            else -> max = i
                        }
                        i++
                    }


                    var curp =
                        "$letraCero$letraUno$letraDos$letraTres$letraCuatro$letraCinco$letraSeis$letraSiete$letraOcho$letraNueve$letraDiez$letraOnce$letraDoce$letraTrece$letraCatorce$letraQuince"
                    if (letraDiez == 'D') {
                        makeText(this@MainActivity, "Select the sex", Toast.LENGTH_SHORT).show()
                    } else {
                        txtResult.text = curp
                    }
            }else{
                if(ddFecha.isEmpty()&&ddFecha.toInt()>31){makeText(this@MainActivity, "Birth Date is incorrect (Day)",Toast.LENGTH_SHORT).show()}
                if(mmFecha.isEmpty()&&mmFecha.toInt()>12){makeText(this@MainActivity, "Birth Date is incorrect (Month)",Toast.LENGTH_SHORT).show()}
                if(aaFecha.isEmpty()&&aaFecha.toInt()>1999&&aaFecha.toInt()>1940){makeText(this@MainActivity, "Birth Date is incorrect (Year)",Toast.LENGTH_SHORT).show()}
            }
        }else{

            if(firstLast.isEmpty()){makeText(this@MainActivity, "Name is incorrect (Firs Lastname)",Toast.LENGTH_SHORT).show()}
            if(secLast.isEmpty()){makeText(this@MainActivity, "Name is incorrect (Sec Lastname)",Toast.LENGTH_SHORT).show()}
            if(firstName.isEmpty()){makeText(this@MainActivity, "Name is incorrect (Firs Name)",Toast.LENGTH_SHORT).show()}
        }

    }
/*    fun checkAccents(s :String): Boolean {
        var n = 0
        var max :Int = s.lenght
        while (n < max) {
            letraUno = firstLast[i]
            when {
                firstLast[i] == 'A' || firstLast[i] == 'E' || firstLast[i] == 'I' || firstLast[i] == 'O' || firstLast[i] == 'U' -> max =
                    i
            }
            i++
        }
    }*/


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        spnState.setSelection(0, false)

        val myAdapter = ArrayAdapter(this, R.layout.mylayout3, states)
        spnState.adapter = myAdapter
        spnState.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }}
        rgGender.setOnCheckedChangeListener(object: RadioGroup.OnCheckedChangeListener {
            override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
                //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                if (checkedId == R.id.rbH) {
                    letraDiez  = 'H'
                    makeText(this@MainActivity, "Hombre",Toast.LENGTH_SHORT).show()
                } else if (checkedId == R.id.rbM) {
                    letraDiez   = 'M'
                    makeText(this@MainActivity, "Mujer",Toast.LENGTH_SHORT).show()
                }
            }
        })
        btnLook.setOnClickListener(this)
        btnClean.setOnClickListener(this)
    }
}