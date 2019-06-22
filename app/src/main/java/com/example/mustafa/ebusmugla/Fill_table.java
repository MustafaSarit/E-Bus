package com.example.mustafa.ebusmugla;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import java.text.DecimalFormat;

public class Fill_table extends AppCompatActivity {
    myDbAdapter myDbAdapter;
    int flag = 0;
    public Fill_table(myDbAdapter myDbAdapter, Context context){
        this.myDbAdapter = myDbAdapter;
        fill();
    };

    public void fill(){
        fill_loop("MerkezKötekli", "07","00", 15, 68);
        fill_loop("MerkezYeniköy", "07", "00", 15, 68);
        fill_loop("MerkezToki", "08", "00", 30, 26);
        fill_loop("MerkezHastane", "06", "30", 10, 87);
        fill_loop("KötekliMerkez", "07", "45", 15, 57);
        fill_loop("KötekliHastane", "06", "15", 45, 21);
        fill_loop("KötekliYeniköy", "07", "15", 15, 68);
        fill_loop("YeniköyMerkez", "07", "30", 15, 57);
        fill_loop("YeniköyHastane", "06", "00", 45, 21);
        fill_loop("YeniköyKötekli", "07", "30", 15, 57);
        fill_loop("TokiMerkez", "07", "05", 20, 38);
        fill_loop("TokiHastane", "06", "30", 60, 15);
        fill_loop("HastaneToki", "08", "30", 60, 12);
        fill_loop("HastaneMerkez", "08", "00", 10, 78);
        fill_loop("HastaneKötekli", "09", "00", 45, 15);
        fill_loop("HastaneYeniköy", "09", "00", 45, 15);
    }

    public void fill_loop(String table_name, String initialH, String initialM, Integer increment, Integer count){
        for(int i = 0; i < count; i++){
            if((i <= count*3/5)){
                myDbAdapter.insertData(initialH+":"+initialM, tool(initialH, 1)+":"+initialM,
                        tool(initialH, 2)+":"+initialM, "Person: 2.55\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tStudent: 1.95\n\nRetired: 2.15\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tDisabled: Free", table_name);
                initialM = minuteChange(initialM, increment);
                if(flag == 1){
                    initialH = hourChange(initialH);
                }
            }else if((i > count*3/5) && (i <= count*4/5)){
                myDbAdapter.insertData(initialH+":"+initialM, tool(initialH, 1)+":"+initialM,
                        "", "", table_name);
                initialM = minuteChange(initialM, increment);
                if(flag == 1){
                    initialH = hourChange(initialH);
                }
            }
            else{
                myDbAdapter.insertData(initialH+":"+initialM, "",
                        "", "", table_name);
                initialM = minuteChange(initialM, increment);
                if(flag == 1){
                    initialH = hourChange(initialH);
                }
            }

        }
    }

    public String tool(String hour, int i){
        return	new DecimalFormat("00").format((Integer.parseInt(hour) + i));
    }

    public String minuteChange(String minute, int increment){
        if((Integer.parseInt(minute) + increment) >= 60){
            flag = 1;
        };
        return new DecimalFormat("00").format((Integer.parseInt(minute) + increment)%60);
    }
    public String hourChange(String hour){
        flag = 0;
        return new DecimalFormat("00").format(Integer.parseInt(hour) + 1);
    }
}
