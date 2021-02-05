package com.example.temperatura

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import java.io.InputStream
import java.io.OutputStream
import java.util.*
import android.os.Handler
import android.os.Message
import com.androidplot.xy.SimpleXYSeries
import com.androidplot.xy.XYPlot

import android.content.Context
import android.graphics.Color
import android.os.Handler
import android.util.Log
import com.androidplot.util.PixelUtils
import com.androidplot.xy.*
import java.text.FieldPosition
import java.text.Format
import java.text.ParsePosition
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {


    private lateinit var btAdapter: BluetoothAdapter
    private lateinit var btDevice: BluetoothDevice
    private lateinit var btSocket: BluetoothSocket

    private lateinit var mConnectBT:ConnectBT
    private lateinit var mConnectedBT:ConnectedBT
    private lateinit var mHandler:BTHandler
    var recDataString= StringBuilder() //Concatena/crea un string

    val message_read:Int=0
    val TAG="HC06"  //para poder monitorear hasta donde llega la aplicacion y detectar errores


    private val address="20:15:12:22:01:31"  //la direccion MAC de mi celular, que me aparece con la app anterior que creamos (Bluetooth Example)
    private val myUUID= UUID.fromString("00001101-0000-1000-8000-00805f9b34FB")


    lateinit var series: SimpleXYSeries

    //class XYPlot : AppCompatActivity(){
    //}


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        mHandler=BTHandler() //Se inicializÛ

        btAdapter= BluetoothAdapter.getDefaultAdapter()
        btDevice=btAdapter.getRemoteDevice(address)

        Log.d(TAG,"Bluetooth message create")
        mConnectBT=ConnectBT(btDevice)
        mConnectBT.start() //se ejecuta en segundo plano el codigo relacionado a connectBT


        chBLED.setOnClickListener {
            if (chBLED.isChecked){ //pregunta si esta palomeado el check box, regresa un true o false
                mConnectedBT.write("1".toByteArray())   //send 1 to turn LED. Write es una variable que pertenece a la clasee de abajo
            }else {
                mConnectedBT.write("0".toByteArray())   //send 0 to turn off LED
            }//end of else
        }


    } //end of OnCreate

    //3 HILOS:
    private inner class ConnectBT(val device:BluetoothDevice):Thread(){  //Crea un hilo para hacer la coneccion bluetooh, con inner esta clase puede acceder a las variables externas
        val btD:BluetoothDevice=device

        override fun run() {
            super.run()

            /* try {  //aqui dentro ponemos las lineas de codigo q pueden generar una excepcion
             } catch (e:Exception){ //aqui la atrapamos y la manejamos
                e.printStackTrace()} */

            btSocket=btD.createInsecureRfcommSocketToServiceRecord(myUUID)
            btSocket.connect()

            Log.d(TAG,"Socket Connected")  //Log es un registro, no generan codigo maquina, no afecta nada
            mConnectedBT=ConnectedBT(btSocket)
            mConnectedBT.start() //se ejecuta el codigo de run en un tercer hilo
        }
    }//end of ConnectBT class

    private inner class ConnectedBT (val mSocket:BluetoothSocket):Thread(){ //Una vez q ya esta conectado, hace la comunicacion
        val mBTSocket: BluetoothSocket=mSocket
        val mOutputStream: OutputStream =mBTSocket.outputStream //ESCRIBE.. a traves de esta variable enviaremos datos
        val mInputStream: InputStream =mBTSocket.inputStream  //LEE
        val mBuffer: ByteArray=ByteArray(256)
        lateinit var mString:String  //late init: se inicializa mas tarde

        override fun run() {
            super.run()

            while (true){  //lazo infinito. Se queda leyendo los datos que le mandemos de vuelta
                val numBytes = mInputStream.read(mBuffer)  //lee un byte array y nos regresa un entero (el nro. de bytes que se leyÛ)
                mString = String(mBuffer,0,numBytes)   //desde 0 hasta el entero de arriba

                Log.d(TAG,mString) //nos muestra lo que se ve en el text view
                mHandler.obtainMessage(message_read,numBytes,-1,mString).sendToTarget()
            }

        }
        fun write(oBytes:ByteArray){
            mOutputStream.write(oBytes)
        }
    }//end of ConnectedBT class

    private inner class BTHandler():Handler(){  //comunica un hilo secundario con uno principal
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)

            if (msg?.what==message_read){ //si el campo de msg corresponde al q usamos al mandar el mensaje en el otro hilo:
                val rsData = msg.obj as String //object es el mString (as en un cast: tomalo como string)
                recDataString.append(rsData)   //recDataS = 21\r\n
                val endofindex = recDataString.indexOf("\r\n")  //requiere un string y nos regresa un entero. Busca si estan esos caracteres y nos regresa su posicion(para separar los caracteres)
                if (endofindex>0){
                    var dataS = recDataString.substring(0,endofindex)  //solo obtiene el numero
                    tvData.text=dataS  //lo muestra en el textView

                    var data = dataS.toInt()

                    recDataString.delete(0,recDataString.length)
                    dataS=""

                }
            }
        }
    }//end of BTHandler
}