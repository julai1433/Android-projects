package com.example.clase29oct2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private CheckBox cb1;
    private CheckBox cb2;
    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cb1 = (CheckBox) findViewById(R.id.cb1);
        cb2 = (CheckBox) findViewById(R.id.cb2);
        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener(){
        @Override
            public void onClick(View v) {                   //--    Listener al botón, no a la Vista o...
                    String cad = "Seleccionado: \n";     //      pantalla como en el caso anterior
                    if (cb1.isChecked() == true) {
                        cad += "opción 1";
                    }
                    if (cb2.isChecked() == true) {
                        cad += "opción 2";
                    }
                    Toast.makeText(getApplicationContext(), cad, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
