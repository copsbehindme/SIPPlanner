package com.example.copsbehindme.sipplanner;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;

public class Calculator extends AppCompatActivity {
    PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
    }
    EditText principle,time,rate;
    TextView investedValue,totalReturns;

    public void calculateTotal(View view){
        principle = (EditText)findViewById(R.id.principle);
        time = (EditText)findViewById(R.id.time);
        rate = (EditText)findViewById(R.id.rate);

        double p = Double.parseDouble(principle.getText().toString());
        int t = Integer.parseInt(time.getText().toString());
        int r = Integer.parseInt(rate.getText().toString());

        double sum = 0;
        double totalInvestedMoney = p * 12 * t;
        for(int i = t*12; i>=1; i--){
            sum = sum + compoundedValue(p,r,i);
        }

        investedValue = (TextView)findViewById(R.id.investedValue);
        investedValue.setText("Invested : " + String.valueOf(totalInvestedMoney));

        totalReturns = (TextView)findViewById(R.id.totalReturns);
        totalReturns.setText("Returns : " + String.valueOf(String.format("%.2f",sum)));
        makePieChart((float) totalInvestedMoney, (float) sum);
    }

    public static double compoundedValue(double p,int r,int t){
        return  p * Math.pow((1 + (0.01 * r / 12)),t);
    }

    public void makePieChart(float investedValue , float totalReturns){
        pieChart = (PieChart)findViewById(R.id.pieChart);
        Description description = new Description(); //Description of pieChart
        description.setText("Invested vs Returns");
        pieChart.setDescription(description);
        pieChart.setCenterText(":)");
        pieChart.setCenterTextSize(10f);
        addDataToChart(investedValue,totalReturns);


    }

    public void addDataToChart(float investedValue , float totalReturns){
        ArrayList<PieEntry> yEntries = new ArrayList<>();
        ArrayList<String> xEntries =  new ArrayList<>();
        yEntries.add(new PieEntry(investedValue));
        yEntries.add(new PieEntry(totalReturns - investedValue));

        xEntries.add("Invested Value");
        xEntries.add("Total Returns");

        //creating dataset
        PieDataSet pieDataSet = new PieDataSet(yEntries,"Invested vs return Value");
        pieDataSet.setSliceSpace(2f);
        pieDataSet.setValueTextSize(15f);

        // adding colors to dataSet
        ArrayList<Integer> colors = new ArrayList<Integer>();
        colors.add(Color.GREEN);
        colors.add(Color.YELLOW);
        pieDataSet.setColors(colors);

        // add legend to the chart
        Legend legend = pieChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setPosition(Legend.LegendPosition.BELOW_CHART_RIGHT);

        //creating pie dataObject
        PieData pieData = new PieData(pieDataSet);

        //setting data to piechart
        pieChart.setData(pieData);
        pieChart.invalidate();
    }
}
