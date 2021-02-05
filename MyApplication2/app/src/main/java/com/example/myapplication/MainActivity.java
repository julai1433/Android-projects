package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText Entrada;              //-- Variable de entrada a asociar con el EditText.
    private TextView Salida;               //-- Variable de salida a asociar con el TextView

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Entrada =   (EditText)findViewById(R.id.etEntrada);
        Salida  =   (TextView)findViewById(R.id.tvSalida);
    }

    public void Btn (View v) {                               //-- Función a ejecutar por el botón
        //var data1S:String = etxtv.text.toString()
        //String data1 = etEntrada.text.toString()
        String entrada  =   Entrada.getText().toString();   //-- Guarda el valor de entrada del EditText.
        Salida.setText(entrada);                            //-- Manda el mismo valor al TextView.
    }
}

