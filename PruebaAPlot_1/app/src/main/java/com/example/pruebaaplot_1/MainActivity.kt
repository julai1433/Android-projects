package com.example.pruebaaplot_1

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import com.androidplot.util.PixelUtils;
import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.xy.XYSeries;
import com.androidplot.xy.*;
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*


import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;
import java.util.*;
import java.util.Arrays.asList




class MainActivity : AppCompatActivity() {

    private lateinit var graph : XYPlot


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val series1Numbers  = mutableListOf<Number>(1.25, 4, 2.125, 8, 4.0, 16.25, 8.3, 32.0, 16.1, 64.5)
        var series1 = SimpleXYSeries(series1Numbers,SimpleXYSeries.ArrayFormat.Y_VALS_ONLY,"Temp")
        val series1Format = LineAndPointFormatter(Color.RED, Color.BLUE, Color.TRANSPARENT, null)


        graph = XYPlot.findViewById(R.id.XYPlot)

        graph.setRangeLabel("Temp. in Grad")
        graph.setDomainLabel("Segundos")
        graph.setDomainStep(StepMode.INCREMENT_BY_VAL, 1.0)
        graph.setRangeStep(StepMode.INCREMENT_BY_VAL, 10.0)
        graph.setRangeBoundaries(0.0, BoundaryMode.FIXED,null,BoundaryMode.AUTO)
        graph.setDomainBoundaries(0.0, BoundaryMode.FIXED,null,BoundaryMode.AUTO)

        graph.outerLimits.set(0,null,-15.0,80)
        PanZoom.attach(graph,PanZoom.Pan.HORIZONTAL, PanZoom.Zoom.STRETCH_HORIZONTAL)

        graph.title.labelPaint.textSize = 80f
        graph.rangeTitle.labelPaint.textSize = 30f
        graph.domainTitle.labelPaint.textSize = 30f
        graph.setBackgroundColor(Color.TRANSPARENT)

        graph.addSeries(series1, series1Format)

        button.setOnClickListener {
            series1Numbers += 10
            graph.removeSeries(series1)
            series1 = SimpleXYSeries(series1Numbers,SimpleXYSeries.ArrayFormat.Y_VALS_ONLY,"Temp")
            graph.addSeries(series1, series1Format)
            graph.redraw()

        }
    }
}
