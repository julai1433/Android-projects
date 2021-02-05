package com.example.interfazcomunicacion;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class VentanaEncender extends AppCompatActivity {

    Button IdBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventana_encender);

        IdBack = (Button) findViewById(R.id.IdBack);

        IdBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                Intent i3 = new Intent(VentanaEncender.this, UserInterfaz.class);//<-<- PARTE A MODIFICAR >->->
                startActivity(i3);
            }
        });



    }
}
