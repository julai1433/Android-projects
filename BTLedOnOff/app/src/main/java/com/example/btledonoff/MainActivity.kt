package com.example.btledonoff

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import android.content.Intent
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    val myUUID:UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB")
    val myBTMac = "98:D3:32:31:92:DE"

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==1){
            if(resultCode==0){
                sendMsg("Bluetooth module not enabled")
            }else{sendMsg("Bluetooth module enabled")}
        }
    }

    private lateinit var myBTAdapter : BluetoothAdapter
    private lateinit var myBTDevice : BluetoothDevice
    private var         myBTSocket : BluetoothSocket? = null

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

        tvStatus.movementMethod=ScrollingMovementMethod()

        btnPD.setOnClickListener ( object: View.OnClickListener{
            override fun onClick(v: View?) {
                //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                val pairedDevices : Set<BluetoothDevice> = myBTAdapter.bondedDevices
                if(pairedDevices.size > 0){
                    for(device:BluetoothDevice in pairedDevices){
                        tvStatus.append("\r\n" + device.name + device.address + "\r\n")
                    }
                }

            }
        } )

        btnConnect.setOnClickListener {
            BTConnect().execute()
            sendMsg("Device connected")
            Toast.makeText(this@MainActivity,"Connected",Toast.LENGTH_SHORT)
        }
        btnLedOn.setOnClickListener {
            sendCommand("1")
            sendMsg("On")
        }
        btnLedOff.setOnClickListener {
            sendCommand("0")
            sendMsg("Off")
        }
        btnDisconnect.setOnClickListener {
            if(myBTSocket != null){
                myBTSocket?.close()
                myBTSocket = null
                sendMsg("Device Disconnected")
                //finish()
            }
        }
    }



    private fun sendCommand(command: String){

        if (myBTSocket != null) {
            myBTSocket!!.outputStream.write(command.toByteArray())
        }

    }


    private fun sendMsg(s: String) {
        Toast.makeText(this@MainActivity,s,Toast.LENGTH_LONG).show()

    }

    private inner class BTConnect : AsyncTask<Void,Void,Void>(){
        override fun onProgressUpdate(vararg values: Void?) {
            super.onProgressUpdate(*values)
        }

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
