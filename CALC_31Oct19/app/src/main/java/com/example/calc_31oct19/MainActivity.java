package com.example.calc_31oct19;

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
    }
    public void click(View pantalla) {              //-- Listener a toda la pantalla
        if (pantalla.getId() == R.id.btsuma) {      //-- Encuentra el origen del evento,
            suma();                                 //-- manda al método correspondiente.
        }
        else if (pantalla.getId() == R.id.btresta) {
            resta();
        }
        else if (pantalla.getId() == R.id.btmulti) {
            multi();
        }
        else if (pantalla.getId() == R.id.btdiv) {
            div();
        }
    }
    public void suma(){
        double res;
        res=(Double.parseDouble(et1.getText().toString()))+(Double.parseDouble(et2.getText().toString()));
        tvres.setText(Double.toString(res));
        Toast.makeText(getApplicationContext(),Double.toString(res),Toast.LENGTH_SHORT).show();
    }
    public void resta(){
        double res;
        res=(Double.parseDouble(et1.getText().toString()))-(Double.parseDouble(et2.getText().toString()));
        tvres.setText(Double.toString(res));
        Toast.makeText(getApplicationContext(),Double.toString(res),Toast.LENGTH_SHORT).show();
    }
    public void multi(){
        double res;
        res=(Double.parseDouble(et1.getText().toString()))*(Double.parseDouble(et2.getText().toString()));
        tvres.setText(Double.toString(res));
        Toast.makeText(getApplicationContext(),Double.toString(res),Toast.LENGTH_SHORT).show();
    }
    public void div(){
        double res;
        res=(Double.parseDouble(et1.getText().toString()))/(Double.parseDouble(et2.getText().toString()));
        tvres.setText(Double.toString(res));
        Toast.makeText(getApplicationContext(),Double.toString(res),Toast.LENGTH_SHORT).show();
    }
}
