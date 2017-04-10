package com.example.copsbehindme.sipplanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sipcalc(View view){
        Intent intent = new Intent(this,Calculator.class);
        startActivity(intent);
    }

    public void plancalc(View view){
        Intent intent = new Intent(this,PlanCalculator.class);
        startActivity(intent);
    }

}
