package com.example.mustafa.ebusmugla;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class Second_Page extends AppCompatActivity {
    String destin_from, destin_to;
    TextView dest, weekday, saturday, sunday, prices;
    ImageButton revert;
    int flag = 0;
    myDbAdapter myDbAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second__page);
        Intent myIntent = getIntent();
        myDbAdapter = new myDbAdapter(this);
        destin_from = myIntent.getStringExtra("destin_from");
        destin_to = myIntent.getStringExtra("destin_to");


        weekday = findViewById(R.id.textView14);
        saturday = findViewById(R.id.textView13);
        sunday = findViewById(R.id.textView12);
        prices = findViewById(R.id.textView6);
        dest = findViewById(R.id.textView3);

        setText(destin_from, destin_to);


        revert = findViewById(R.id.revert);
        revert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(flag == 0){
                    setText(destin_to, destin_from);
                    flag = 1;
                }else{
                    setText(destin_from, destin_to);
                    flag = 0;
                }
            }
        });
    }

    public void setText(String first, String second){
        dest.setText(first+" - "+second);
        weekday.setText(myDbAdapter.getWeek(first+second));
        saturday.setText(myDbAdapter.getSaturday(first+second));
        sunday.setText(myDbAdapter.getSunday(first+second));
        prices.setText(myDbAdapter.getPrice(first+second));
    }
}
