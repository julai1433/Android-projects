package com.example.a13novbt_emparejados;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.Set;

public class MainActivity extends AppCompatActivity {
    Button btn;
    ListView lv;                                // Objeto del tipo ListView
    BluetoothAdapter bluetoothAdapter;          // Adaptador bluetooth
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.btn);
        lv = findViewById(R.id.lv);
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        listar();                       //Función para enlistar los dispositivos Bluetooth gardados.
    }
    private void listar(){
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Set<BluetoothDevice> bt = bluetoothAdapter.getBondedDevices();//Obtiene dispositivos enlazados en un Set
                String[] strings = new String[bt.size()];
                int indice=0;
                if (bt.size()>0){
                    for (BluetoothDevice device:bt){        // Ciclo para obtener los nombres de los dispositivos.
                        strings[indice] = device.getName();
                        indice++;
                    }
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_expandable_list_item_1,strings);
                    lv.setAdapter(arrayAdapter);            // Muestra los dispositivos en el SetList
                }
            }
        });
    }
}
