package com.techmax.journeydate;

import android.app.DatePickerDialog;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    EditText et,et2;
    DatePickerDialog dpd;
    int selected_date,selected_year,selected_month;

    DatePickerDialog dpd2;
    Calendar cal,newcal,cal_start,min_cal,cal_end;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et=findViewById(R.id.start_journey);
        et2=findViewById(R.id.return_journey);



        et2.setInputType(InputType.TYPE_NULL);
        et.setInputType(InputType.TYPE_NULL);
        cal=Calendar.getInstance();
        newcal=Calendar.getInstance();
        cal_start=Calendar.getInstance();
        min_cal=Calendar.getInstance();
        cal_end=Calendar.getInstance();



        et.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(MotionEvent.ACTION_UP==event.getAction())
                {
                    dpd=new DatePickerDialog(MainActivity.this,new DatePickerDialog.OnDateSetListener() {


                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            selected_date=dayOfMonth;
                            selected_month=month;
                            selected_year=year;
                            et.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                            //for Updation of latest view in calender selectot
                            cal.set(selected_year,selected_month,selected_date);
                           //for return journey
                            cal_start.set(selected_year,selected_month,selected_date+1);

                        }
                    },cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DAY_OF_MONTH));

                    if(!et.getText().toString().trim().isEmpty())
                    dpd.getDatePicker().setMinDate(min_cal.getTimeInMillis());
                    else{
                        dpd.getDatePicker().setMinDate(cal.getTimeInMillis());}

                  if(!et2.getText().toString().trim().isEmpty()) {
                      dpd.getDatePicker().setMaxDate(cal_end.getTimeInMillis());
                     }
                 dpd.show();
                }
                return false;
            }
        });

        et2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if(MotionEvent.ACTION_UP==event.getAction())
                {
                    dpd2=new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            selected_date=dayOfMonth;
                            selected_month=month;
                            selected_year=year;
                            et2.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                            newcal.set(selected_year,selected_month,selected_date);
                            cal_end.set(selected_year,selected_month,selected_date-1);
                           // cal.set(year,month,dayOfMonth);
                        }
                    },newcal.get(Calendar.YEAR),newcal.get(Calendar.MONTH),newcal.get(Calendar.DAY_OF_MONTH));

                    if(et.getText().toString().trim().isEmpty()==true){
                        int year=cal_start.get(Calendar.YEAR);
                        int month=cal_start.get(Calendar.MONTH);
                        int dayofmonth=cal_start.get(Calendar.DAY_OF_MONTH);
                        dayofmonth++;
                        cal_start.set(year,month,dayofmonth);
                        dpd2.getDatePicker().setMinDate(cal_start.getTimeInMillis());
                        dayofmonth--;
                        cal_start.set(year,month,dayofmonth);
                    }
                    else
                        dpd2.getDatePicker().setMinDate(cal_start.getTimeInMillis());


                    dpd2.show();

                }
                return false;
            }
        });




}            }

