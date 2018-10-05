package com.jason.feelsbook;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class FeelingsActivity extends AppCompatActivity {
    private String purpose;
    protected Button emotLove;
    protected Button emotJoy;
    protected Button emotFear;
    protected Button emotAnger;
    protected Button emotSurprise;
    protected Button emotSadness;

    private EditText date;
    private EditText comment;
    private final SimpleDateFormat format = new SimpleDateFormat("yyyy-MMM-dd");

    private Gson gson;
    private String ed_feeling;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feelings);

        Intent intent = getIntent();
        purpose = intent.getStringExtra("purpose");

        gson = new Gson();
        Button delete = findViewById(R.id.btn_delete);
        date = findViewById(R.id.et_date);
        comment = findViewById(R.id.ed_comment);
        emotLove = findViewById(R.id.btn_love);
        emotJoy = findViewById(R.id.btn_joy);
        emotFear = findViewById(R.id.btn_fear);
        emotAnger = findViewById(R.id.btn_anger);
        emotSurprise = findViewById(R.id.btn_surprise);
        emotSadness = findViewById(R.id.btn_sadness);

        final Calendar calendar = Calendar.getInstance();

        if (purpose.equals("edit")){
            Feelings feelings = gson.fromJson(intent.getStringExtra("content"),Feelings.class);
            date.setText(feelings.getDateString());
            comment.setText(feelings.getComment());
            calendar.setTime(feelings.getDate());
        }

        final Activity that = this;
        emotJoy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ed_feeling="joy";
                }

        });
        emotLove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ed_feeling="love";

            }

        });
        emotFear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ed_feeling="fear";

            }


        });
        emotAnger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ed_feeling="anger";

            }

        });
        emotSadness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ed_feeling="sadness";
            }

        });
        emotSurprise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ed_feeling="surprise";
            }

        });

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog mDatePickerDialog =  new DatePickerDialog(
                        that,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day_of_month) {
                                calendar.set(Calendar.YEAR,year);
                                calendar.set(Calendar.MONTH,month);
                                calendar.set(Calendar.DAY_OF_MONTH,day_of_month);
                                date.setText(format.format(calendar.getTime()));
                            }
                        }
                        , calendar.get(Calendar.YEAR)
                        , calendar.get(Calendar.MONTH)
                        , calendar.get(Calendar.DAY_OF_MONTH)
                );
                mDatePickerDialog.show();
            }
        });


        if (purpose.equals("add")){
            delete.setVisibility(View.GONE);
        }


    }

    private boolean checkFields(){

        if (date.getText().toString().trim().isEmpty()){
            Toast.makeText(this, "Date cannot be empty", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;

    }

    public void onConfirmClicked(View view){
        if (checkFields()) {
            Date datetime = new Date();
            try {
                datetime = format.parse(date.getText().toString());
            } catch (ParseException e){
                e.printStackTrace();
            }
            Feelings feelings = new Feelings(
                    ed_feeling,
                    datetime,
                    comment.getText().toString()
            );
            String subscription_str = gson.toJson(feelings);
            Intent intent = new Intent();
            intent.putExtra("result",subscription_str);
            setResult(RESULT_OK,intent);
            finish();
        }
    }

    public void onDeleteClicked(View view){
        Intent intent = new Intent();
        intent.putExtra("is_deleted",true);
        setResult(RESULT_OK,intent);
        finish();
    }
}

