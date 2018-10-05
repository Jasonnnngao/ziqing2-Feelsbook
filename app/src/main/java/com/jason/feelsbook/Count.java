package com.jason.feelsbook;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class Count extends AppCompatActivity{
    int joy_count;
    int love_count;
    int fear_count;
    int anger_count;
    int sadness_count;
    int surprise_count;
    protected TextView emotLove;
    protected TextView emotJoy;
    protected TextView emotFear;
    protected TextView emotAnger;
    protected TextView emotSurprise;
    protected TextView emotSadness;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count);

        
    }
}


