package com.example.clase29oct;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private CheckBox c1;
    private CheckBox c2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        c1 = findViewById(R.id.cb1);
        c2 = findViewById(R.id.cb2);
    }
    public void click(View pantalla){           //-- Listener en la View, en la pantalla.
        if (pantalla.getId()==R.id.btn){        //-- verifica que el origen del evento sea btn.
            validar();                          //-- Ejecuta la función
        }
    }
    public void validar(){                      //--Manda un toast con la opción seleccionada
        String cad="Seleccionado: \n";
        if(c1.isChecked()){
            cad+="opción 1";
        }
        if(c2.isChecked()){
            cad+="opción 2";
        }
        Toast.makeText(getApplicationContext(),cad,Toast.LENGTH_SHORT).show();
    }
}
