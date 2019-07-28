package bowling.zander.com.cricketbowling;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyDBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "bowlers.db";

    public static final String TABLE_PRODUCTS = "bowlers";

    public static final String COLUMN_ID = "_id";

    public static final String COLUMN_BOWLERNAME = "bowler_name";

    public static final String COLUMN_OOP = "oop";
    public static final String COLUMN_OGL = "ogl";
    public static final String COLUMN_OSP = "osp";
    public static final String COLUMN_MOP = "mop";
    public static final String COLUMN_MGL = "mgl";
    public static final String COLUMN_MSP= "msp";
    public static final String COLUMN_LOP = "lop";
    public static final String COLUMN_LGL = "lgl";
    public static final String COLUMN_LSP = "lsp";
    public static final String COLUMN_WIDE = "wide";

    public static List<String> deliveries = new ArrayList<>();



    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
        deliveries.add("wide");
        deliveries.add("lsp");
        deliveries.add("lgl");
        deliveries.add("lop");
        deliveries.add("msp");
        deliveries.add("mgl");
        deliveries.add("mop");
        deliveries.add("osp");
        deliveries.add("ogl");
        deliveries.add("oop");

    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE " + TABLE_PRODUCTS + " ( " + COLUMN_ID  + " INTEGER PRIMARY KEY AUTOINCREMENT , " + COLUMN_BOWLERNAME +" TEXT, " + COLUMN_OOP +" INTEGER , "
                + COLUMN_MOP  +" INTEGER , "+ COLUMN_LOP +" INTEGER , " + COLUMN_OGL +" INTEGER , "
                + COLUMN_MGL +" INTEGER , " + COLUMN_LGL +" INTEGER , " + COLUMN_OSP +" INTEGER , "
                + COLUMN_MSP +" INTEGER , "+ COLUMN_LSP +" INTEGER , " + COLUMN_WIDE +" INTEGER "
                 + " );";
        db.execSQL(query);
        Log.i("Database create ", "Database created successfully");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        onCreate(db);
    }

    public void insertDelivery(String bowlerName, String columnName, int count){
        SQLiteDatabase db = getWritableDatabase();

        String query = "SELECT "+ columnName +" FROM " + TABLE_PRODUCTS + " WHERE bowler_name = \""+ bowlerName + "\" ;";
        Log.i("Query to insert", query);
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        int num =count;

        if(c != null && c.getCount()>0){
            num += c.getInt(c.getColumnIndex(columnName));
        }
        else{
            ContentValues values = new ContentValues();
            values.put(COLUMN_BOWLERNAME, bowlerName);
            for(String s : deliveries){
                if(s.equals(columnName)){
                    values.put(columnName, num);
                }
                else{
                    values.put(columnName, 0);
                }
            }
            db.insert(TABLE_PRODUCTS,null,values);
        }
        Log.i("Value of num", " " +num);
        String updateQuery = "update " + TABLE_PRODUCTS + " SET " + columnName +  " = " + num + " WHERE bowler_name = \"" + bowlerName + "\" ;";
        db.execSQL(updateQuery);
//        SQLiteDatabase db1 = getWritableDatabase();
        db.close();
    }

    public Map<String,Integer> viewRecord(String bowlerName){
        Map<String, Integer> result = new HashMap<>();
        Log.i(" bowler name " , bowlerName);
        SQLiteDatabase db = getWritableDatabase();

        String query = "SELECT * FROM " + TABLE_PRODUCTS + " WHERE bowler_name = \""+ bowlerName + "\" ;";
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();

        while(!c.isAfterLast()){
            int num = 0;
            for (String s : deliveries) {
                num = c.getInt(c.getColumnIndex(s));
                result.put(s, num);
            }
            c.moveToNext();
        }
        db.close();



        return  result;
    }

    public List<String> getAllBowlers(){
        List<String> names = new ArrayList<>();
        SQLiteDatabase db = getWritableDatabase();

        String query = "SELECT bowler_name FROM " + TABLE_PRODUCTS + " WHERE 1 ;";
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();

        while(!c.isAfterLast()){
            String bowler = c.getString(c.getColumnIndex("bowler_name"));
            if( bowler != null) {
                names.add(bowler);
            }
            c.moveToNext();
        }
        db.close();
        return names;
    }



}
