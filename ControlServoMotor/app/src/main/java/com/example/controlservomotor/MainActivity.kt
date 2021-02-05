package com.example.controlservomotor

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import android.content.Intent
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    val myUUID:UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB")
    val myBTMac = "98:D3:32:31:92:DE"
    //var angulo = "100"

    //var angulo = findViewById<EditText>(R.id.etAngle).toString()
    private lateinit var myBTAdapter : BluetoothAdapter
    private lateinit var myBTDevice : BluetoothDevice
    private var         myBTSocket : BluetoothSocket? = null
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==1){
            if(resultCode==0){
                sendMsg("Bluetooth module not enabled")
            }else{sendMsg("Bluetooth module enabled")}
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myBTAdapter = BluetoothAdapter.getDefaultAdapter()
        if(myBTAdapter==null){
            sendMsg("Not Bluetooth module")
            finish()
        }else{
            if(!myBTAdapter.isEnabled){
                val intent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                startActivityForResult(intent, 1)
            }
        }

        var angulo = "0"

        btnConnect.setOnClickListener {
            //angulo = etAngle?.text.toString()
            if (myBTSocket == null){
                BTConnect().execute()
                sendMsg("Connecting Device")
                tvStatus.text = "Status: Connected. Ángulo:$angulo"
                //tvStatus.append(": Connected. Ángulo:$angulo")
            }else{
            sendMsg("Device is already connected")
            }

        }
        btnDisconnect.setOnClickListener {
            //angulo = etAngle?.text.toString()
            if(myBTSocket != null){
                myBTSocket?.close()
                myBTSocket = null
                sendMsg("Disconnecting Device")
                tvStatus.text = "Status: Disconnected. Ángulo:$angulo"
                //tvStatus.append(": Disconnected. Ángulo:$angulo")
                //finish()
            }else{
                sendMsg("Device is already disconnected")
            }
        }
        btnSend.setOnClickListener {
            angulo = etAngle?.text.toString()
            if(myBTSocket!= null){
                if(angulo.toInt() in 0..180){
                sendCommand(angulo)
                sendMsg("Desplazando a $angulo grados")
                tvStatus.text = "Status: Connected. Ángulo:$angulo"
                }else{//etAngle.error = "Incorrect Angle"
                    sendMsg("Incorrect Angle! (0-180)") }

            }else{
                sendMsg("Device disconnected")
            }
        }
    }
    private fun sendCommand(command: String){
        if (myBTSocket != null) {
            myBTSocket!!.outputStream.write(command.toByteArray())
        }else{
            sendMsg("Unable to send the angle")
        }
    }
    private fun sendMsg(s: String) {
        Toast.makeText(this@MainActivity,s,Toast.LENGTH_LONG).show()
    }
    private inner class BTConnect : AsyncTask<Void,Void,Void>(){
        override fun doInBackground(vararg params: Void?): Void? {
            //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            try{
                myBTDevice = myBTAdapter.getRemoteDevice(myBTMac)
                myBTSocket = myBTDevice.createInsecureRfcommSocketToServiceRecord(myUUID)
                myBTSocket?.connect()
            }catch(e:Exception){
                publishProgress()
            }
            return null
        }
    }

}
