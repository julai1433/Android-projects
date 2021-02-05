package com.example.calculadora31oct2019;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button btsuma;
    private Button btresta;
    private Button btmulti;
    private Button btdiv;
    private EditText et1;
    private EditText et2;
    private TextView tvres;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btsuma  = (Button) findViewById(R.id.btsuma);
        btresta = (Button) findViewById(R.id.btresta);
        btmulti = (Button) findViewById(R.id.btmulti);
        btdiv   = (Button) findViewById(R.id.btdiv);
        et1     = (EditText) findViewById(R.id.et1);
        et2     = (EditText) findViewById(R.id.et2);
        tvres   = (TextView) findViewById(R.id.tvres);
        btsuma.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {               //-- Listener para botón suma.
                double res;
                res=(Double.parseDouble(et1.getText().toString()))+(Double.parseDouble(et2.getText().toString()));
                tvres.setText(Double.toString(res));
            }
        });
        btresta.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {               //-- Listener para botón resta.
                double res;
                res=(Double.parseDouble(et1.getText().toString()))-(Double.parseDouble(et2.getText().toString()));
                tvres.setText(Double.toString(res));
            }
        });
        btmulti.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {               //-- Listener para botón multiplicación.
                double res;
                res=(Double.parseDouble(et1.getText().toString()))*(Double.parseDouble(et2.getText().toString()));
                tvres.setText(Double.toString(res));
            }
        });
        btdiv.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {               //-- Listener para botón división.
                double res;
                res=(Double.parseDouble(et1.getText().toString()))/(Double.parseDouble(et2.getText().toString()));
                tvres.setText(Double.toString(res));
            }
        });
    }
}
