package com.example.clase5_nov_19;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    SensorManager sensorManager;                    //-- Se declara un manager de sensores
    Sensor sensor;                                  //-- Se declara un sensor
    SensorEventListener sensorEventListener;        //-- y un listener para el sensor.
    TextView tvx,tvy,tvz,tvg,tvgmax;
    double gmax=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sensorManager=(SensorManager)getSystemService(SENSOR_SERVICE);      //Establece el servicio del sensor
        sensor=sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);   //Se usará el acelerómetro
        tvx=(TextView)findViewById(R.id.tvx);
        tvy=(TextView)findViewById(R.id.tvy);
        tvz=(TextView)findViewById(R.id.tvz);
        tvg=(TextView)findViewById(R.id.tvg);
        tvgmax=(TextView)findViewById(R.id.tvgmax);
        sensorEventListener = new SensorEventListener() {
                @Override
                public void onSensorChanged(SensorEvent event) {   //Método ejecutado a cada cambio del sensor
                    double x=event.values[0];               //Los valores del sensor están en el arreglo 'values'
                    double y=event.values[1];
                    double z=event.values[2];
                    double g=Math.sqrt(Math.pow(x,2)+Math.pow(y,2)+Math.pow(z,2));//Obtenemos el vector resultante
                    if(gmax<g){gmax=g;}                     //Variable que almacena la máxima aceleración alcanzada.
                    tvx.setText(String.valueOf(x));
                    tvy.setText(String.valueOf(y));
                    tvz.setText(String.valueOf(z));
                    tvg.setText(String.valueOf(g));
                    tvgmax.setText(String.valueOf(gmax));
                }
                @Override
                public void onAccuracyChanged(Sensor sensor, int accuracy) {}
        };
        start();
    }
    private void start(){       //Método que se ejecuta al inicio
        sensorManager.registerListener(sensorEventListener,sensor,SensorManager.SENSOR_DELAY_NORMAL);
        //Se registra el Listener del sensor
    }
}
