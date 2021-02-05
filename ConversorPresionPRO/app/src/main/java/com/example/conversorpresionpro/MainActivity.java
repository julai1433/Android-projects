package com.example.conversorpresionpro;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText Entrada;
    private TextView Salida;
    private RadioButton RbBar;  // -- Variable asociada a un RadioButton.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Entrada = findViewById(R.id.etEntrada);
        Salida = findViewById(R.id.tvSalida);
        RbBar = findViewById(R.id.rbBar);
    }
    double dato = 0;
    public void Btn(View v) {                                     // -- Función del botón Convertir
        boolean estado = RbBar.isChecked();                       // -- Variable booleana guarda la posición del radio button
        dato = Double.parseDouble(Entrada.getText().toString());
        if (estado == false) {
            dato = dato / 14.223;
        } else {
            dato = dato / 14.504;
        }
        Salida.setText(Double.toString(dato));
    }
}