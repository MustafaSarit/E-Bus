package com.example.mustafa.ebusmugla;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

// moovit

public class myDbAdapter {
    myDbHelper myhelper;

    public myDbAdapter(Context context)
    {
        myhelper = new myDbHelper(context);
    }


    public long insertData(String weekdays, String saturday, String sunday, String prices, String table_name)
    {
        SQLiteDatabase dbb = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.Weekdays, weekdays);
        contentValues.put(myDbHelper.Saturday, saturday);
        contentValues.put(myDbHelper.Sunday, sunday);
        contentValues.put(myDbHelper.Prices, prices);
        long id = dbb.insert(table_name, null , contentValues);
        return id;
    }

    public String getWeek(String table_name)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {myDbHelper.UID,myDbHelper.Weekdays,myDbHelper.Saturday,myDbHelper.Sunday,myDbHelper.Prices};
        Cursor cursor = db.query(table_name,columns,null,null,null,null,null);
        StringBuffer buffer= new StringBuffer();
        while (cursor.moveToNext())
        {
            int cid =cursor.getInt(cursor.getColumnIndex(myDbHelper.UID));
            String weekdays =cursor.getString(cursor.getColumnIndex(myDbHelper.Weekdays));
            buffer.append(weekdays + "\n");
        }
        return buffer.toString();
    }

    public String getSaturday(String table_name)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {myDbHelper.UID,myDbHelper.Weekdays,myDbHelper.Saturday,myDbHelper.Sunday,myDbHelper.Prices};
        Cursor cursor =db.query(table_name,columns,null,null,null,null,null);
        StringBuffer buffer= new StringBuffer();
        while (cursor.moveToNext())
        {
            int cid =cursor.getInt(cursor.getColumnIndex(myDbHelper.UID));
            String day =cursor.getString(cursor.getColumnIndex(myDbHelper.Saturday));
            buffer.append(day + "\n");
        }
        return buffer.toString();
    }

    public String getSunday(String table_name)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {myDbHelper.UID,myDbHelper.Weekdays,myDbHelper.Saturday,myDbHelper.Sunday,myDbHelper.Prices};
        Cursor cursor =db.query(table_name,columns,null,null,null,null,null);
        StringBuffer buffer= new StringBuffer();
        while (cursor.moveToNext())
        {
            int cid =cursor.getInt(cursor.getColumnIndex(myDbHelper.UID));
            String day =cursor.getString(cursor.getColumnIndex(myDbHelper.Sunday));
            buffer.append(day + "\n");
        }
        return buffer.toString();
    }

    public String getPrice(String table_name)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {myDbHelper.UID,myDbHelper.Weekdays,myDbHelper.Saturday,myDbHelper.Sunday,myDbHelper.Prices};
        Cursor cursor =db.query(table_name,columns,null,null,null,null,null);
        cursor.moveToNext();
        int cid =cursor.getInt(cursor.getColumnIndex(myDbHelper.UID));
        String price =cursor.getString(cursor.getColumnIndex(myDbHelper.Prices));
        return price;
    }

    public boolean isTableExists(String tableName) {
        SQLiteDatabase mDatabase = myhelper.getWritableDatabase();
        Cursor cursor = mDatabase.rawQuery("select DISTINCT tbl_name from sqlite_master where tbl_name = '"+tableName+"'", null);
        if(cursor!=null) {
            if(cursor.getCount()>0) {
                cursor.close();
                return true;
            }
            cursor.close();
        }
        return false;
    }


    public long insertDevData(Integer condition)
    {
        SQLiteDatabase dbb = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.Condition, condition);
        long id = dbb.insert(myDbHelper.TABLEDEV, null , contentValues);
        return id;
    }

    public Integer getDevData()
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {myDbHelper.UID,myDbHelper.Condition};
        Cursor cursor =db.query(myDbHelper.TABLEDEV,columns,null,null,null,null,null);
        int cond = 0;
        while (cursor.moveToNext()){
            int cid = cursor.getInt(cursor.getColumnIndex(myDbHelper.UID));
            cond += cursor.getInt(cursor.getColumnIndex(myDbHelper.Condition));
        }
        return cond;
    }

    static class myDbHelper extends SQLiteOpenHelper
    {
        //Common variables
        public static final String DATABASE_NAME = "myDatabase";    // Database Name
        public static final int DATABASE_Version = 1;    // Database Version
        public static final String UID="_id";     // Column I (Primary Key)
        private Context context;

        //Table names
        public static final String TABLEDEV = "Initial";            // Condition table
        public static final String TABLE1 = "MerkezKötekli";
        public static final String TABLE2 = "MerkezYeniköy";
        public static final String TABLE3 = "MerkezHastane";
        public static final String TABLE4 = "MerkezToki";
        public static final String TABLE5 = "KötekliMerkez";
        public static final String TABLE6 = "KötekliHastane";
        public static final String TABLE7 = "KötekliYeniköy";
        public static final String TABLE8 = "HastaneKötekli";
        public static final String TABLE9 = "HastaneMerkez";
        public static final String TABLE10 = "HastaneYeniköy";
        public static final String TABLE11 = "HastaneToki";
        public static final String TABLE12 = "TokiMerkez";
        public static final String TABLE13 = "TokiHastane";
        public static final String TABLE14 = "YeniköyMerkez";
        public static final String TABLE15 = "YeniköyHastane";
        public static final String TABLE16 = "YeniköyKötekli";


        //Table columns
        public static final String Condition = "cond";          // Only for TABLEDEV
        public static final String Weekdays = "Weekdays";
        public static final String Saturday = "Saturday";
        public static final String Sunday = "Sunday";
        public static final String Prices = "Prices";

        //Create table command
        public static final String CREATE_TABLEDEV = "CREATE TABLE "+TABLEDEV+
                " ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+Condition+" INTEGER);";
        public static  String CREATE_TABLE1 = "CREATE TABLE "+TABLE1+
                " ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+Weekdays+" VARCHAR(255) ,"+Saturday+" VARCHAR(255), "+Sunday+" VARCHAR(255), "+Prices+" VARCHAR(255));";
        public static  String CREATE_TABLE2 = "CREATE TABLE "+TABLE2+
                " ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+Weekdays+" VARCHAR(255) ,"+Saturday+" VARCHAR(255), "+Sunday+" VARCHAR(255), "+Prices+" VARCHAR(255));";
        public static  String CREATE_TABLE3 = "CREATE TABLE "+TABLE3+
                " ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+Weekdays+" VARCHAR(255) ,"+Saturday+" VARCHAR(255), "+Sunday+" VARCHAR(255), "+Prices+" VARCHAR(255));";
        public static  String CREATE_TABLE4 = "CREATE TABLE "+TABLE4+
                " ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+Weekdays+" VARCHAR(255) ,"+Saturday+" VARCHAR(255), "+Sunday+" VARCHAR(255), "+Prices+" VARCHAR(255));";
        public static  String CREATE_TABLE5 = "CREATE TABLE "+TABLE5+
                " ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+Weekdays+" VARCHAR(255) ,"+Saturday+" VARCHAR(255), "+Sunday+" VARCHAR(255), "+Prices+" VARCHAR(255));";
        public static  String CREATE_TABLE6 = "CREATE TABLE "+TABLE6+
                " ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+Weekdays+" VARCHAR(255) ,"+Saturday+" VARCHAR(255), "+Sunday+" VARCHAR(255), "+Prices+" VARCHAR(255));";
        public static  String CREATE_TABLE7 = "CREATE TABLE "+TABLE7+
                " ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+Weekdays+" VARCHAR(255) ,"+Saturday+" VARCHAR(255), "+Sunday+" VARCHAR(255), "+Prices+" VARCHAR(255));";
        public static  String CREATE_TABLE8 = "CREATE TABLE "+TABLE8+
                " ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+Weekdays+" VARCHAR(255) ,"+Saturday+" VARCHAR(255), "+Sunday+" VARCHAR(255), "+Prices+" VARCHAR(255));";
        public static  String CREATE_TABLE9 = "CREATE TABLE "+TABLE9+
                " ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+Weekdays+" VARCHAR(255) ,"+Saturday+" VARCHAR(255), "+Sunday+" VARCHAR(255), "+Prices+" VARCHAR(255));";
        public static  String CREATE_TABLE10 = "CREATE TABLE "+TABLE10+
                " ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+Weekdays+" VARCHAR(255) ,"+Saturday+" VARCHAR(255), "+Sunday+" VARCHAR(255), "+Prices+" VARCHAR(255));";
        public static  String CREATE_TABLE11 = "CREATE TABLE "+TABLE11+
                " ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+Weekdays+" VARCHAR(255) ,"+Saturday+" VARCHAR(255), "+Sunday+" VARCHAR(255), "+Prices+" VARCHAR(255));";
        public static  String CREATE_TABLE12 = "CREATE TABLE "+TABLE12+
                " ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+Weekdays+" VARCHAR(255) ,"+Saturday+" VARCHAR(255), "+Sunday+" VARCHAR(255), "+Prices+" VARCHAR(255));";
        public static  String CREATE_TABLE13 = "CREATE TABLE "+TABLE13+
                " ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+Weekdays+" VARCHAR(255) ,"+Saturday+" VARCHAR(255), "+Sunday+" VARCHAR(255), "+Prices+" VARCHAR(255));";
        public static  String CREATE_TABLE14 = "CREATE TABLE "+TABLE14+
                " ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+Weekdays+" VARCHAR(255) ,"+Saturday+" VARCHAR(255), "+Sunday+" VARCHAR(255), "+Prices+" VARCHAR(255));";
        public static  String CREATE_TABLE15 = "CREATE TABLE "+TABLE15+
                " ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+Weekdays+" VARCHAR(255) ,"+Saturday+" VARCHAR(255), "+Sunday+" VARCHAR(255), "+Prices+" VARCHAR(255));";
        public static  String CREATE_TABLE16 = "CREATE TABLE "+TABLE16+
                " ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+Weekdays+" VARCHAR(255) ,"+Saturday+" VARCHAR(255), "+Sunday+" VARCHAR(255), "+Prices+" VARCHAR(255));";

        //Drop table command
        private static final String DROP_TABLEDEV = "DROP TABLE IF EXISTS "+TABLEDEV;
        private static final String DROP_TABLE1   = "DROP TABLE IF EXISTS "+TABLE1;
        private static final String DROP_TABLE2   = "DROP TABLE IF EXISTS "+TABLE2;
        private static final String DROP_TABLE3   = "DROP TABLE IF EXISTS "+TABLE3;
        private static final String DROP_TABLE4   = "DROP TABLE IF EXISTS "+TABLE4;
        private static final String DROP_TABLE5   = "DROP TABLE IF EXISTS "+TABLE5;
        private static final String DROP_TABLE6   = "DROP TABLE IF EXISTS "+TABLE6;
        private static final String DROP_TABLE7   = "DROP TABLE IF EXISTS "+TABLE7;
        private static final String DROP_TABLE8   = "DROP TABLE IF EXISTS "+TABLE8;
        private static final String DROP_TABLE9   = "DROP TABLE IF EXISTS "+TABLE9;
        private static final String DROP_TABLE10  = "DROP TABLE IF EXISTS "+TABLE10;
        private static final String DROP_TABLE11  = "DROP TABLE IF EXISTS "+TABLE11;
        private static final String DROP_TABLE12  = "DROP TABLE IF EXISTS "+TABLE12;
        private static final String DROP_TABLE13  = "DROP TABLE IF EXISTS "+TABLE13;
        private static final String DROP_TABLE14  = "DROP TABLE IF EXISTS "+TABLE14;
        private static final String DROP_TABLE15  = "DROP TABLE IF EXISTS "+TABLE15;
        private static final String DROP_TABLE16  = "DROP TABLE IF EXISTS "+TABLE16;


        public myDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_Version);
            this.context=context;
        }

        public void onCreate(SQLiteDatabase db) {

            db.execSQL(CREATE_TABLEDEV);
            db.execSQL(CREATE_TABLE1);
            db.execSQL(CREATE_TABLE2);
            db.execSQL(CREATE_TABLE3);
            db.execSQL(CREATE_TABLE4);
            db.execSQL(CREATE_TABLE5);
            db.execSQL(CREATE_TABLE6);
            db.execSQL(CREATE_TABLE7);
            db.execSQL(CREATE_TABLE8);
            db.execSQL(CREATE_TABLE9);
            db.execSQL(CREATE_TABLE10);
            db.execSQL(CREATE_TABLE11);
            db.execSQL(CREATE_TABLE12);
            db.execSQL(CREATE_TABLE13);
            db.execSQL(CREATE_TABLE14);
            db.execSQL(CREATE_TABLE15);
            db.execSQL(CREATE_TABLE16);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                db.execSQL(DROP_TABLEDEV);
                db.execSQL(DROP_TABLE1);
                db.execSQL(DROP_TABLE2);
                db.execSQL(DROP_TABLE3);
                db.execSQL(DROP_TABLE4);
                db.execSQL(DROP_TABLE5);
                db.execSQL(DROP_TABLE6);
                db.execSQL(DROP_TABLE7);
                db.execSQL(DROP_TABLE8);
                db.execSQL(DROP_TABLE9);
                db.execSQL(DROP_TABLE10);
                db.execSQL(DROP_TABLE11);
                db.execSQL(DROP_TABLE12);
                db.execSQL(DROP_TABLE13);
                db.execSQL(DROP_TABLE14);
                db.execSQL(DROP_TABLE15);
                db.execSQL(DROP_TABLE16);
                onCreate(db);
        }
    }
}