package com.example.mustafa.ebusmugla;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {
    myDbAdapter myDbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Runnable r = new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i<100; i++){
                    System.out.println(i);
                    try {
                        sleep(1000);
                    } catch (InterruptedException e){}

                }
            }
        };
        new Thread(r).start();

        myDbAdapter = new myDbAdapter(this);
        if (myDbAdapter.getDevData() == 0){
            myDbAdapter.insertDevData(1);
            Fill_table fill_table = new Fill_table(myDbAdapter, this);
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    Intent intent = new Intent(MainActivity.this, First_Page.class);
                    startActivity(intent);
                }
            }, 3000);
        }else{
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    Intent intent = new Intent(MainActivity.this, First_Page.class);
                    startActivity(intent);
                }
            }, 3000);
        }
    }
}
