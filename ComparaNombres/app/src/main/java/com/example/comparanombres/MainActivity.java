package com.example.comparanombres;

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
        Entrada =   (EditText)findViewById(R.id.etEntrada);
        Salida  =   (TextView)findViewById(R.id.tvSalida);
    }
    String[] nombres = {"Eduardo","Pedro"};     // -- Arreglo con los nombres a comparar.
    int i = 0;
    boolean c=false;

    public void Btn (View v) {
        //var data1S:String = etxtv.text.toString()
        //String data1 = etEntrada.text.toString()
        String entrada  =   Entrada.getText().toString();
        for(i=0;i<2;i++) {                      //-- Ciclo para comparar la cadena de entrada con las cadenas del arreglo 'nombres'
            if(nombres[i].equals(entrada)) {
                c=true;                         //-- Bandera indica coincidencia
            }
        }
        if(c==false){
            Salida.setText("Diferente");
        }else{
            Salida.setText("Igual");
            c=false;
        }
    }
}
