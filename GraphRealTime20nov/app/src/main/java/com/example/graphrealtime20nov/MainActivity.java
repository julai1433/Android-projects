package com.example.graphrealtime20nov;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.provider.ContactsContract;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class MainActivity extends AppCompatActivity {

    private final Handler mHandler = new Handler();
    private Runnable mTimer1;
    private Runnable mTimer2;
    private LineGraphSeries<DataPoint> mSeries1;
    private LineGraphSeries<> mSeries2;
    private double graph2LastXValue = 5d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GraphView graphView = findViewById(R.id.graphView);

        mSeries1 = new LineGraphSeries<>(generateData());
        graphView.addSeries(mSeries1);


        DataPoint[] points = new DataPoint[100];
        for (int i = 0; i < points.length; i++) {
            points[i] = new DataPoint(i, Math.sin(i*0.5) * 20*(Math.random()*10+1));
        }
        LineGraphSeries<DataPoint> series=new LineGraphSeries<>(puntos());
        graphView.addSeries(series);
        graphView.getViewport().setXAxisBoundsManual(true);
        graphView.getViewport().setMinX(5);
        graphView.getViewport().setMaxX(10);
        graphView.getViewport().setYAxisBoundsManual(true);
        graphView.getViewport().setMinY(0);
        graphView.getViewport().setMaxY(10);
        graphView.getViewport().setScrollable(true);
        graphView.getViewport().setScalableY(true);
    }
    private DataPoint[] puntos() {
        DataPoint[] dp = new DataPoint[]{
                new DataPoint(0, 6),
                new DataPoint(1, 0),
                new DataPoint(2, 0),
                new DataPoint(3, 6),
                new DataPoint(4, 6),
                new DataPoint(5, 0),
                new DataPoint(6, 0),
                new DataPoint(7, 6),
                new DataPoint(8, 6),
                new DataPoint(9, 0),
                new DataPoint(10, 0),
                new DataPoint(11, 6)
        };
        return (dp);
    }
}


