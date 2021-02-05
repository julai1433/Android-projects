package com.example.conversor_presion;

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


    public void Btn (View v) {

        dato=Double.parseDouble(Entrada.getText().toString());
        dato=dato/14.223;
        Salida.setText(Double.toString(dato));
    }



}
