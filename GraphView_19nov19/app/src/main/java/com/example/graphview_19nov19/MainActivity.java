package com.example.graphview_19nov19;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GraphView graph = findViewById(R.id.graph);                // Variable asociada a la gráfica
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(puntos());    // Serie de valores
        graph.addSeries(series);                                    // Agrega los valores a la gráfica.

        // Establece límites en ejes
        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setMinY(0);
        graph.getViewport().setMaxY(12);
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(0);
        graph.getViewport().setMaxX(12);
        // enable scaling and scrolling
        graph.getViewport().setScrollable(true); // enables horizontal scrolling
        graph.getViewport().setScrollableY(true); // enables vertical scrolling
        graph.getViewport().setScalable(true); // enables horizontal zooming and scrolling
        graph.getViewport().setScalableY(true); // enables vertical zooming and scrolling
    }
    private DataPoint[] puntos() {         // Método para generar los valores.
        DataPoint[] dp = new DataPoint[]{
                new DataPoint(0,10),
                new DataPoint(1,8),
                new DataPoint(2,6),
                new DataPoint(3,4),
                new DataPoint(4,2),
                new DataPoint(5,0),
                new DataPoint(6,2),
                new DataPoint(7,4),
                new DataPoint(8,6),
                new DataPoint(9,8),
                new DataPoint(10,10)
        };
        return (dp);
    }
}
