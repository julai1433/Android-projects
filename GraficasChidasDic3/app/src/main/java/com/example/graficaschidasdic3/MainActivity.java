package com.example.graficaschidasdic3;


import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.BubbleChart;
import com.github.mikephil.charting.charts.CandleStickChart;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.charts.ScatterChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.BubbleData;
import com.github.mikephil.charting.data.BubbleDataSet;
import com.github.mikephil.charting.data.BubbleEntry;
import com.github.mikephil.charting.data.CandleData;
import com.github.mikephil.charting.data.CandleDataSet;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.data.ScatterData;
import com.github.mikephil.charting.data.ScatterDataSet;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.util.ArrayList;
import java.util.Arrays;


public class MainActivity extends AppCompatActivity {
    private LineChart lineChart;
    private RadarChart radarChart;
    private ScatterChart scatterChart;
    //Eje X Gráfica de Dispersión
    private String[]months=new String[]{"Jan","Feb","Mar","April","May","Jun","Jul","Ago","Sep","Oct","Nov","Dec"};
    //Eje Y Gráfica de Dispersión
    private double[]dolar=new double[]{19.6566,19.0388,19.2607,19.3779,19.0120,19.0683,19.2087,18.9929,20.0988,19.7345,19.1948,19.6113};
    //Eje X Gráfica Lineal
    private String[]time=new String[]{"0.083","0.166","0.25","0.333","0.416","0.5","0.583","0.666","0.75","0.833","0.916","1"};
    //Eje Y Gráfica Lineal
    private double[]sin=new double[]{0.5,0.866,1,0.866,0.5,0,-0.5,-0.866,-1,-0.866,-0.5,0};
    //Grafica de Radar(Radar)
    //Los criterios que se evaluaran el la grafica
    private String[]variable=new String[]{"PAC","SHO","PAS","DRI","DEF","PHY"};
    //Valor para los criterios en Cristiano Ronaldo
    private int[]valueCristiano=new int[]{90,93,82,89,35,78};
    //Valor para los criterios en Lionel Messi
    private int[]valueMessi=new int[]{87,92,92,96,39,66};
    //Valor para los criterios en Chicharito
    private int[]valueChicha=new int[]{70,77,64,75,31,59};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lineChart = (LineChart) findViewById(R.id.lineChart);
        radarChart = (RadarChart) findViewById(R.id.radarChart);
        scatterChart = (ScatterChart) findViewById(R.id.scatterChart);
        createCharts();
    }
    //Carasteristicas comunes en las graficas
    private Chart getSameChart(Chart chart,String description,int textColor,int background,int animateY,boolean leyenda){
        chart.getDescription().setText(description);
        chart.getDescription().setTextColor(textColor);
        chart.getDescription().setTextSize(15);
        chart.setBackgroundColor(background);
        chart.animateY(animateY);

        //Validar porque la grafica de radar y dispersion tiene dos datos especificos y la leyenda se crea de acuerdo a esos datos.
        if(leyenda)
            legend(chart);
        return chart;
    }
    private void legend(Chart chart) {
        Legend legend = chart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);

        ArrayList<LegendEntry> entries = new ArrayList<>();
        for (int i = 0; i < months.length; i++) {
            LegendEntry entry = new LegendEntry();
            //entry.formColor = colors[i];
            entry.label = months[i];
            entries.add(entry);
        }
        legend.setCustom(entries);
    }
    //Método para agregar valores a la gráfica lineal
    private ArrayList<Entry> getLineEntries() {
        ArrayList<Entry> entries = new ArrayList<>();
        for (int i = 0; i < sin.length; i++)
            entries.add(new Entry(i, (float)sin[i]));
        return entries;
    }
    //Método para agregar valores de Cristiano a la gráfica radial
    private ArrayList<RadarEntry> getRadarEntriesCristiano() {
        ArrayList<RadarEntry> entries = new ArrayList<>();
        for (int i = 0; i < valueCristiano.length; i++)
            entries.add(new RadarEntry(valueCristiano[i]));
        return entries;
    }
    //Método para agregar valores de Messi a la gráfica radial
    private ArrayList<RadarEntry> getRadarEntriesMessi() {
        ArrayList<RadarEntry> entries = new ArrayList<>();
        for (int i = 0; i < valueMessi.length; i++)
            entries.add(new RadarEntry(valueMessi[i]));
        return entries;
    }
    //Método para agregar valores de Chicharito a la gráfica radial
    private ArrayList<RadarEntry> getRadarEntriesChicha() {
        ArrayList<RadarEntry> entries = new ArrayList<>();
        for (int i = 0; i < valueChicha.length; i++)
            entries.add(new RadarEntry(valueChicha[i]));
        return entries;
    }
    //Asigna los valores a evaluar a la gráfica Radial
    private ArrayList<String> getVariable() {
        ArrayList<String> entries = new ArrayList<>();
        entries.addAll(Arrays.asList(variable));
        return entries;
    }
    //Método para agregar valores a la gráfica de dispersión
    private ArrayList<Entry> getScatterEntries() {
        ArrayList<Entry> entries = new ArrayList<>();
        for (int i = 0; i < dolar.length; i++)
            entries.add(new Entry(i, (float)dolar[i]));
        return entries;
    }
    //Eje horizontal o eje X de la gráfica Lineal
    private void axisX(XAxis axis){
        axis.setGranularityEnabled(true);
        axis.setPosition(XAxis.XAxisPosition.BOTTOM);
        axis.setValueFormatter(new IndexAxisValueFormatter(months));
        axis.setEnabled(true);
    }
    //Eje X de la gráfica Radial
    private void axisXRadar(XAxis axis){
        axis.setGranularityEnabled(true);
        axis.setPosition(XAxis.XAxisPosition.BOTTOM);
        axis.setValueFormatter(new IndexAxisValueFormatter(variable));
        axis.setEnabled(true);
    }
    //Eje horizontal o eje X de la gráfica lineal
    private void axisXLine(XAxis axis){
        axis.setGranularityEnabled(true);
        axis.setPosition(XAxis.XAxisPosition.BOTTOM);
        axis.setValueFormatter(new IndexAxisValueFormatter(time));
        axis.setEnabled(true);
    }
    //Eje Vertical o eje Y lado izquierdo
    private void axisLeftLine(YAxis axis){
        axis.setSpaceTop(30);
        axis.setAxisMinimum((float) -1.5);
        axis.setAxisMaximum((float) 1.5);
        axis.setGranularity((float) 0.01);
    }
    //Eje Vertical o eje Y lado izquierdo
    private void axisLeftScatter(YAxis axis){
        axis.setSpaceTop(30);
        axis.setAxisMinimum((float) 18.8);
        axis.setAxisMaximum((float) 20.2);
        axis.setGranularity((float) 0.01);
    }
    //Eje Vertical o eje Y lado Derecho
    private void axisRight(YAxis axis){
        axis.setEnabled(false);
    }
    //Crear graficas
    public void createCharts(){
        //LineChart
        lineChart = (LineChart) getSameChart(lineChart, "Gráfica senoidal", Color.BLUE, Color.LTGRAY, 500,false);
        lineChart.setData(getLineData());
        lineChart.invalidate();
        axisXLine(lineChart.getXAxis());
        axisLeftLine(lineChart.getAxisLeft());
        axisRight(lineChart.getAxisRight());

        //ScatterChart
        scatterChart = (ScatterChart) getSameChart(scatterChart, "Conversión Dólar a MXN", Color.BLUE, Color.LTGRAY, 1500,false);
        scatterChart.setData(getScatterData());
        axisX(scatterChart.getXAxis());
        axisLeftScatter(scatterChart.getAxisLeft());
        axisRight(scatterChart.getAxisRight());
        //Para que el eje X inicie de -0.5 y termine en 5 con esto los puntos no se veran cortada(Borrar y ejecutar para ver la diferencia)
        scatterChart.getXAxis().setAxisMinimum(-1f);
        scatterChart.getXAxis().setAxisMaximum(months.length);

        //RadarChart
        //En radar se valido la leyenda porque no podemos perzonalizarlo la leyenda se crea de acuerdo a los datos que se tienen dentro de la grafica.
        radarChart = (RadarChart) getSameChart(radarChart, "FIFA 20 Estadísticas", Color.BLUE, Color.GRAY, 3000,false);
        radarChart.setData(getRadarData());
        radarChart.invalidate();
        axisXRadar(radarChart.getXAxis());

    }
    //Carasteristicas comunes en dataset
    private DataSet getDataSame(DataSet dataSet){
        //dataSet.setColors(colors);
        dataSet.setValueTextColor(Color.YELLOW);
        dataSet.setValueTextSize(10);
        return dataSet;
    }
    private LineData getLineData() {
        LineDataSet lineDataSet = (LineDataSet) getDataSame(new LineDataSet(getLineEntries(), "Área bajo la curva"));
        lineDataSet.setLineWidth(2.1f);
        //Color de los circulos de la grafica
        lineDataSet.setCircleColor(Color.RED);
        //Tamaño de los circulos de la grafica
        lineDataSet.setCircleRadius(2f);
        //Sombra grafica
        lineDataSet.setDrawFilled(true);
        //Estilo de la linea picos(linear) o curveada(cubic) cuadrada(Stepped)
        lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        return new LineData(lineDataSet);
    }
    private RadarData getRadarData() {

        RadarDataSet cristiano = (RadarDataSet) getDataSame(new RadarDataSet(getRadarEntriesCristiano(), "CR7"));
        //Definimos un color especial para Cristiano para no cargar el arreglo de colores
        cristiano.setColor(Color.WHITE);

        //Definimos un color especial para Messi para no cargar el arreglo de colores
        RadarDataSet messi = (RadarDataSet) getDataSame(new RadarDataSet(getRadarEntriesMessi(), "Messi"));
        messi.setColor(Color.BLUE);

        //Definimos un color especial para Messi para no cargar el arreglo de colores
        RadarDataSet chicha = (RadarDataSet) getDataSame(new RadarDataSet(getRadarEntriesChicha(), "Chicharito"));
        chicha.setColor(Color.RED);


        ArrayList<IRadarDataSet> dataSets = new ArrayList<IRadarDataSet>();
        dataSets.add(cristiano);
        dataSets.add(messi);
        dataSets.add(chicha);

        RadarData data = new RadarData(dataSets);
        data.setLabels(getVariable());
        return data;
    }
    private ScatterData getScatterData() {
        ScatterDataSet scatterDataSet = (ScatterDataSet) getDataSame(new ScatterDataSet(getScatterEntries(), "Dólar"));
        scatterDataSet.setScatterShape(ScatterChart.ScatterShape.CIRCLE);
        scatterDataSet.setColor(Color.MAGENTA);
        return new ScatterData(scatterDataSet);
    }
}
