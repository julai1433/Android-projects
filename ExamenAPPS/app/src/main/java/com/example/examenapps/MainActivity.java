package com.example.examenapps;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText Entrada;
    private TextView Salida;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Entrada = findViewById(R.id.etEntrada);
        Salida  = findViewById(R.id.tvSalida);
    }
    double dato=0;
    public void BtnSen (View v) {                               //-- Función recibe el valor del ...
        dato=Double.parseDouble(Entrada.getText().toString());  //   EditText, calcula el seno y ...
        dato=Math.sin(dato);                                    //   lo muestra en el TextView.
        Salida.setText(Double.toString(dato));
    }
    public void BtnCos (View v) {                               //-- Función que regresa el coseno.
        dato=Double.parseDouble(Entrada.getText().toString());
        dato=Math.cos(dato);
        Salida.setText(Double.toString(dato));
    }
}
