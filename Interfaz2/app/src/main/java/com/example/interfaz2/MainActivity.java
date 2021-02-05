package com.example.interfaz2;


import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private LineChart mChart;
    private LineChart mChart1;
    private LineChart mChart2;

    private Thread thread;
    private boolean plotData = true;

    RadioButton rBtn1;
    RadioButton rBtn2;
    RadioButton rBtn3;
    RadioGroup rGroup;

    //1)
    Button IdDesconectar;
    //-------------------------------------------
    Handler bluetoothIn;
    final int handlerState = 0;
    private BluetoothAdapter btAdapter = null;
    private BluetoothSocket btSocket = null;
    private StringBuilder DataStringIN = new StringBuilder();
    private ConnectedThread MyConexionBT;
    // Identificador unico de servicio - SPP UUID
    private static final UUID BTMODULEUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    // String para la direccion MAC
    private static String address = "84:0D:8E:22:0A:FE";
    private int ChartRequest = 0;
    //-------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //2)
        //Enlaza los controles con sus respectivas vistas
        IdDesconectar = (Button) findViewById(R.id.IdDesconectar);
        rBtn1 = findViewById(R.id.radioButton1);
        rBtn2 = findViewById(R.id.radioButton2);
        rBtn3 = findViewById(R.id.radioButton3);
        rGroup = findViewById(R.id.radioGroup);


        btAdapter = BluetoothAdapter.getDefaultAdapter(); // get Bluetooth adapter

        bluetoothIn = new Handler() {
            public void handleMessage(android.os.Message msg) {
                if (msg.what == handlerState) {
                    String readMessage = (String) msg.obj;
                    DataStringIN.append(readMessage);

                    int endOfLineIndex = DataStringIN.indexOf("#");

                    if (endOfLineIndex > 0) {
                        String dataInPrint = DataStringIN.substring(0, endOfLineIndex);
                        plotear(dataInPrint);
                        //IdBufferIn.setText("Dato: " + dataInPrint);//<-<- PARTE A MODIFICAR >->->
                        DataStringIN.delete(0, DataStringIN.length());
                    }
                }
            }
        };
        VerificarEstadoBT();


        IdDesconectar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (btSocket!=null)
                {
                    try {btSocket.close();}
                    catch (IOException e)
                    { Toast.makeText(getBaseContext(), "Error", Toast.LENGTH_SHORT).show();}
                }
                finish();
            }
        });


        setmChart();
        setmChart1();
        setmChart2();

        rBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setChart();
            }

        });
        rBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setChart();
            }
        });
        rBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setChart();
            }
        });

        //setChart();                                  // Envía el dato de la gráfica requerida.

        feedMultiple();
    }

    private BluetoothSocket createBluetoothSocket(BluetoothDevice device) throws IOException {
        //crea un conexion de salida segura para el dispositivo
        //usando el servicio UUID
        return device.createRfcommSocketToServiceRecord(BTMODULEUUID);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //Setea la direccion MAC
        BluetoothDevice device = btAdapter.getRemoteDevice(address);
        try
        {
            btSocket = createBluetoothSocket(device);
        } catch (IOException e) {
            Toast.makeText(getBaseContext(), "La creacción del Socket fallo", Toast.LENGTH_LONG).show();
        }
        // Establece la conexión con el socket Bluetooth.
        try
        {
            btSocket.connect();
        } catch (IOException e) {
            try {
                btSocket.close();
            } catch (IOException e2) {}
        }
        MyConexionBT = new ConnectedThread(btSocket);
        MyConexionBT.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (thread != null) {
            thread.interrupt();
        }
        try
        { // Cuando se sale de la aplicación esta parte permite
            // que no se deje abierto el socket
            btSocket.close();
        } catch (IOException e2) {}
    }

    //Comprueba que el dispositivo Bluetooth Bluetooth está disponible y solicita que se active si está desactivado
    private void VerificarEstadoBT() {

        if(btAdapter==null) {
            Toast.makeText(getBaseContext(), "El dispositivo no soporta bluetooth", Toast.LENGTH_LONG).show();
        } else {
            if (btAdapter.isEnabled()) {
            } else {
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, 1);
            }
        }
    }

    private void addEntry(double dato) {

        LineData data = mChart.getData();

        if (data != null) {

            ILineDataSet set = data.getDataSetByIndex(0);
            // set.addEntry(...); // can be called as well

            if (set == null) {
                set = createSet();
                data.addDataSet(set);
            }

//            data.addEntry(new Entry(set.getEntryCount(), (float) (Math.random() * 80) + 10f), 0);
            data.addEntry(new Entry(set.getEntryCount(), (float)dato), 0);
            data.notifyDataChanged();

            // let the chart know it's data has changed
            mChart.notifyDataSetChanged();

            // limit the number of visible entries
            mChart.setVisibleXRangeMaximum(150);
            // mChart.setVisibleYRange(30, AxisDependency.LEFT);

            // move to the latest entry
            mChart.moveViewToX(data.getEntryCount());

        }
    }

    private LineDataSet createSet() {

        LineDataSet set = new LineDataSet(null, "Dynamic Data");
        set.setAxisDependency(YAxis.AxisDependency.LEFT);
        set.setLineWidth(2f);
        set.setColor(Color.MAGENTA);
        set.setHighlightEnabled(false);
        set.setDrawValues(false);
        set.setDrawCircles(false);
        set.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        set.setCubicIntensity(0.2f);
        return set;
    }

    private void feedMultiple() {

        if (thread != null){
            thread.interrupt();
        }

        thread = new Thread(new Runnable() {

            @Override
            public void run() {
                while (true){
                    plotData = true;
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        });

        thread.start();
    }

    private void plotear(String data) {
        if(plotData){
            addEntry(Double.parseDouble(data));
            plotData = false;
        }
    }

    //Crea la clase que permite crear el evento de conexion
    private class ConnectedThread extends Thread {
        private final InputStream mmInStream;
        private final OutputStream mmOutStream;

        public ConnectedThread(BluetoothSocket socket)
        {
            InputStream tmpIn = null;
            OutputStream tmpOut = null;
            try
            {
                tmpIn = socket.getInputStream();
                tmpOut = socket.getOutputStream();
            } catch (IOException e) { }
            mmInStream = tmpIn;
            mmOutStream = tmpOut;
        }

        public void run()
        {
            byte[] buffer = new byte[256];
            int bytes;

            // Se mantiene en modo escucha para determinar el ingreso de datos
            while (true) {
                try {
                    bytes = mmInStream.read(buffer);
                    String readMessage = new String(buffer, 0, bytes);
                    // Envia los datos obtenidos hacia el evento via handler
                    bluetoothIn.obtainMessage(handlerState, bytes, -1, readMessage).sendToTarget();
                } catch (IOException e) {
                    break;
                }
            }
        }
        //Envio de trama
        public void write(String input)
        {
            try {
                mmOutStream.write(input.getBytes());
            }
            catch (IOException e)
            {
                //si no es posible enviar datos se cierra la conexión
                Toast.makeText(getBaseContext(), "La Conexión fallo", Toast.LENGTH_LONG).show();
                finish();
            }
        }
    }

    private void setChart(){
        if(rBtn1.isChecked()){
            ChartRequest=0;
            mChart.setVisibility(View.VISIBLE);
            mChart1.setVisibility(View.INVISIBLE);
            mChart2.setVisibility(View.INVISIBLE);
            //MyConexionBT.write("1");
        }else{
            if(rBtn2.isChecked()){
                ChartRequest=1;
                mChart.setVisibility(View.INVISIBLE);
                mChart1.setVisibility(View.VISIBLE);
                mChart2.setVisibility(View.INVISIBLE);
             //   MyConexionBT.write("2");
            }else{
                ChartRequest=2;
                mChart.setVisibility(View.INVISIBLE);
                mChart1.setVisibility(View.INVISIBLE);
                mChart2.setVisibility(View.VISIBLE);
             //   MyConexionBT.write("3");
            }
        }
    }

    private void setmChart(){

        mChart = (LineChart) findViewById(R.id.chart1);
        // enable description text
        mChart.getDescription().setEnabled(true);
        // enable touch gestures
        mChart.setTouchEnabled(true);
        // enable scaling and dragging
        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(true);
        mChart.setDrawGridBackground(true);
        // if disabled, scaling can be done on x- and y-axis separately
        mChart.setPinchZoom(true);
        // set an alternative background color
        mChart.setBackgroundColor(Color.WHITE);
        LineData data = new LineData();
        data.setValueTextColor(Color.WHITE);
        // add empty data
        mChart.setData(data);
        // get the legend (only possible after setting data)
        Legend l = mChart.getLegend();
        // modify the legend ...
        l.setForm(Legend.LegendForm.LINE);
        l.setTextColor(Color.WHITE);

        XAxis xl = mChart.getXAxis();
        xl.setTextColor(Color.WHITE);
        xl.setDrawGridLines(true);
        xl.setAvoidFirstLastClipping(true);
        xl.setEnabled(true);
        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setTextColor(Color.WHITE);
        leftAxis.setDrawGridLines(false);
        leftAxis.setAxisMaximum(100f);
        leftAxis.setAxisMinimum(0f);
        leftAxis.setDrawGridLines(true);
        YAxis rightAxis = mChart.getAxisRight();
        rightAxis.setEnabled(false);
        mChart.getAxisLeft().setDrawGridLines(true);
        mChart.getXAxis().setDrawGridLines(true);
        mChart.setDrawBorders(true);
    }

    private void setmChart1(){

        mChart1 = (LineChart) findViewById(R.id.chart2);
        // enable description text
        mChart1.getDescription().setEnabled(true);
        // enable touch gestures
        mChart1.setTouchEnabled(true);
        // enable scaling and dragging
        mChart1.setDragEnabled(true);
        mChart1.setScaleEnabled(true);
        mChart1.setDrawGridBackground(true);
        // if disabled, scaling can be done on x- and y-axis separately
        mChart1.setPinchZoom(true);
        // set an alternative background color
        mChart1.setBackgroundColor(Color.GRAY);
        LineData data1 = new LineData();
        data1.setValueTextColor(Color.WHITE);
        // add empty data
        mChart1.setData(data1);
        // get the legend (only possible after setting data)
        Legend l1 = mChart1.getLegend();
        // modify the legend ...
        l1.setForm(Legend.LegendForm.LINE);
        l1.setTextColor(Color.WHITE);

        XAxis xl1 = mChart1.getXAxis();
        xl1.setTextColor(Color.WHITE);
        xl1.setDrawGridLines(true);
        xl1.setAvoidFirstLastClipping(true);
        xl1.setEnabled(true);
        YAxis leftAxis1 = mChart1.getAxisLeft();
        leftAxis1.setTextColor(Color.WHITE);
        leftAxis1.setDrawGridLines(false);
        leftAxis1.setAxisMaximum(100f);
        leftAxis1.setAxisMinimum(0f);
        leftAxis1.setDrawGridLines(true);
        YAxis rightAxis1 = mChart1.getAxisRight();
        rightAxis1.setEnabled(false);
        mChart1.getAxisLeft().setDrawGridLines(true);
        mChart1.getXAxis().setDrawGridLines(true);
        mChart1.setDrawBorders(true);

    }

    private void setmChart2(){


        mChart2 = (LineChart) findViewById(R.id.chart3);
        // enable description text
        mChart2.getDescription().setEnabled(true);
        // enable touch gestures
        mChart2.setTouchEnabled(true);
        // enable scaling and dragging
        mChart2.setDragEnabled(true);
        mChart2.setScaleEnabled(true);
        mChart2.setDrawGridBackground(true);
        // if disabled, scaling can be done on x- and y-axis separately
        mChart2.setPinchZoom(true);
        // set an alternative background color
        mChart2.setBackgroundColor(Color.DKGRAY);
        LineData data2 = new LineData();
        data2.setValueTextColor(Color.WHITE);
        // add empty data
        mChart2.setData(data2);
        // get the legend (only possible after setting data)
        Legend l2 = mChart2.getLegend();
        // modify the legend ...
        l2.setForm(Legend.LegendForm.LINE);
        l2.setTextColor(Color.WHITE);

        XAxis xl2 = mChart2.getXAxis();
        xl2.setTextColor(Color.WHITE);
        xl2.setDrawGridLines(true);
        xl2.setAvoidFirstLastClipping(true);
        xl2.setEnabled(true);
        YAxis leftAxis2 = mChart2.getAxisLeft();
        leftAxis2.setTextColor(Color.WHITE);
        leftAxis2.setDrawGridLines(false);
        leftAxis2.setAxisMaximum(100f);
        leftAxis2.setAxisMinimum(0f);
        leftAxis2.setDrawGridLines(true);
        YAxis rightAxis2 = mChart2.getAxisRight();
        rightAxis2.setEnabled(false);
        mChart2.getAxisLeft().setDrawGridLines(true);
        mChart2.getXAxis().setDrawGridLines(true);
        mChart2.setDrawBorders(true);
    }





}
