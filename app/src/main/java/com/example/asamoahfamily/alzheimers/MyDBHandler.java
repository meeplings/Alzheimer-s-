package com.example.asamoahfamily.alzheimers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MyDBHandler extends SQLiteOpenHelper{

    private static final String TAG = "asamoahDebug";

//    private static final int IS_DONE = 1;
//    private static final int IS_NOT_DONE = 0;

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "patient.db";

    public static final String PAT_BUTS = "info";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_BUT_NAME = "butname";
    public static final String COLUMN_COLOR = "color";
    public static final String COLUMN_DONE = "done";
    public static final String COLUMN_PRIO = "prio";

    public MyDBHandler(Context context,SQLiteDatabase.CursorFactory factory) {
        super(context, DB_NAME, factory, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        Log.d(TAG,"onCreate called");
        String query = "CREATE TABLE " +
                PAT_BUTS + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_BUT_NAME + " TEXT, " +
                COLUMN_DONE + " INTEGER, " +
                COLUMN_COLOR + " TEXT " +
                ");";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP_TABLE_IF_EXISTS " + PAT_BUTS );
        onCreate(db);
    }

    public void addInfo(Tasks t){
        ContentValues vals = new ContentValues();

        vals.put(COLUMN_BUT_NAME, t.getName());
        vals.put(COLUMN_ID, t.getId());
        vals.put(COLUMN_COLOR, Integer.toString(t.getColor()));
        vals.put(COLUMN_DONE, Boolean.toString(t.isAlert()));
        vals.put(COLUMN_PRIO, Integer.toString(t.getPrio()));
        SQLiteDatabase db = getWritableDatabase();
        db.insert(PAT_BUTS, null, vals);
        db.close();
    }

    public void delInfo(String butName){

        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(" DELETE FROM " + PAT_BUTS +
                " WHERE " + COLUMN_BUT_NAME + " =\"" + butName + "\";");
    }

    public void delAll(){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(PAT_BUTS,null,null);
        dbToString();
    }

    public String dbToString(){
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();

        String query = " SELECT * FROM " + PAT_BUTS + " WHERE 1";
        //Cursor --> points to a location in results

        Cursor c = db.rawQuery(query,null);

        c.moveToFirst();
        Log.d(TAG, Integer.toString(c.getPosition()));
        while(c.moveToNext()){
            if(c.getString(c.getColumnIndex("butname"))!=null){
                dbString += c.getString(c.getColumnIndex("butname"));
                dbString += "\n";
            }
            else{
                Log.d(TAG,"Something flerped!");
            }

        }
        db.close();
        c.close();
        Log.d(TAG,dbString);
        return  dbString;
    }
}
