package com.example.clase23oct2019;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText Entrada;
    private TextView Salida;
    private RadioButton RbEdad18;
    private RadioButton RbEdad18_30;
    private RadioButton RbEdad30_60;
    private RadioButton RbEdad60;
    private RadioButton RbSexoM;
    private RadioButton RbSexoF;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Entrada = findViewById(R.id.etNombre);
        Salida = findViewById(R.id.tvSalida);
        RbEdad18 = findViewById(R.id.rb18);
        RbEdad18_30 = findViewById(R.id.rb18_30);
        RbEdad30_60 = findViewById(R.id.rb30_60);
        RbEdad60 = findViewById(R.id.rb60);
        RbSexoM = findViewById(R.id.rbM);
        RbSexoF = findViewById(R.id.rbF);
    }
    public void Btn(View v) {                       //-- La función del botón usa la información...
        String name = Entrada.getText().toString(); //   de los radio buttons, para arrojar una...
        String resultado=name;                      //   cadena con la información dela persona.
        if (RbSexoM.isChecked()) {
            resultado=resultado+" Hombre";
        } else {
            resultado=resultado+" Mujer";
        }
        if(RbEdad18.isChecked()){
            resultado = resultado+" joven menor de edad";
        }else if(RbEdad18_30.isChecked()){
            resultado = resultado+" joven adulto";

        }else if(RbEdad30_60.isChecked()){
            resultado = resultado+" adulto de mediana edad";
        }else{
            resultado = resultado+" adulto mayor";
        }
        Salida.setText(resultado);
    }
}
