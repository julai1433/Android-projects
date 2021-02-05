package com.example.a12_nov_bt;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    Button bton, btoff;
    BluetoothAdapter bluetoothAdapter;
    Intent btComponente;
    int micodigo = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bton = findViewById(R.id.bton);
        btoff = findViewById(R.id.btoff);
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        bluetoothOnMetodo();
        bluetoothOffMetodo();
        btComponente = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        @Override
        protected void onActivityResult ( int requestCode, int resultCode, @Nullable Intent data){
            //super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == micodigo) {
                if (resultCode == RESULT_OK) {
                    Toast.makeText(getApplicationContext(), "Bt encendido", Toast.LENGTH_SHORT).show();
                } else if (resultCode == RESULT_CANCELED) {
                    Toast.makeText(getApplicationContext(), "Hubo un problema al encender Bt", Toast.LENGTH_SHORT).show();
                }
            }
        }

        private void bluetoothOnMetodo () {
            bton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //if(bluetoothAdapter == null){
                    //    Toast.makeText(getApplicationContext(),"BT no soportado en este dispositivo", Toast.LENGTH_SHORT);
                    //} else {
                    //    Toast.makeText(getApplicationContext(), "BT soportado en este dispositivo", Toast.LENGTH_SHORT);
                    //}
                    if (bluetoothAdapter.isEnabled()) {
                        Toast.makeText(getApplicationContext(), "Bt actualmente activado", Toast.LENGTH_SHORT).show();
                    } else {
                        //bluetoothAdapter.enable();
                        Toast.makeText(getApplicationContext(), "Activando Bt", Toast.LENGTH_SHORT).show();
                        startActivityForResult(btComponente, micodigo);
                    }
                }

            });
        }
        private void bluetoothOffMetodo () {
            btoff.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (bluetoothAdapter.isEnabled()) {
                        bluetoothAdapter.disable();
                        Toast.makeText(getApplicationContext(), "Desactivando Bt", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Bt actualmente desactivado", Toast.LENGTH_SHORT).show();
                    }
                }

            });

        }

    }
}
