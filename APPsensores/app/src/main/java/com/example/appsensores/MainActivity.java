package com.example.appsensores;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    SensorManager sensorManager;        //-- Manager de sensores
    Sensor sensor,sensor2;              //-- Los dos sensores a usar
    SensorEventListener sensorEventListener, sensorEventListener2;  //-- Dos listeners,
    TextView tvg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sensorManager=(SensorManager)getSystemService(SENSOR_SERVICE);
        sensor=sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);           //Sensor de iluminación
        sensor2=sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);  //Sensor de aceleración.
        tvg=(TextView)findViewById(R.id.tvg);

        sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {     //-- Cuando cambia la ilumiación
                double luz=event.values[0];
                tvg.setText(String.valueOf(luz));       //-- Imprime el valor de iluminación detectado
            }
            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {}
        };

        sensorEventListener2 = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                double x=event.values[0];
                double y=event.values[1];
                double z=event.values[2];

                double x1=0;
                double y1=0;
                double z1=0;
                //Se usan 3 variables para cada eje para poder hacer un promediado con al menos 3 muestras.
                double x2=0;
                double y2=0;
                double z2=0;
                //Se recorren de variable las muestras previas.
                x2=x1;
                y2=y1;
                z2=z1;
                x1=x;
                y1=y;
                z1=z;
                if((((x+x1+x2)/3)-x)>3){    //Si la aceleración promediada en eje X es mayor a 3
                    Toast.makeText(getApplicationContext(),"Sacudiendo en X",Toast.LENGTH_SHORT).show();
                }                           //Toast que lo indica
                if((((y+y1+y2)/3)-y)>3){    //Si la aceleración promediada en eje Y es mayor a 3
                    Toast.makeText(getApplicationContext(),"Sacudiendo en Y",Toast.LENGTH_SHORT).show();
                }
                if((((z+z1+z2)/3)-z)>3){    //Si la aceleración promediada en eje Z es mayor a 3
                    Toast.makeText(getApplicationContext(),"Sacudiendo en Z",Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
            }
        };
        start();
    }
    private void start(){
        sensorManager.registerListener(sensorEventListener,sensor,SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(sensorEventListener2,sensor2,SensorManager.SENSOR_DELAY_NORMAL);
    }
}
