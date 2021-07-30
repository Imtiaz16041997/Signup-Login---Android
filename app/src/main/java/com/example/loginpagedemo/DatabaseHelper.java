package com.example.loginpagedemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;



public class DatabaseHelper extends SQLiteOpenHelper {

    //Initialize The Database and Details

    private static final String DATABASE_NAME = "Userdetails.db";
    private static final String TABLE_NAME = "user_details";

    private static final String ID = "ID";
    private static final String NAME = "Name";
    private static final String EMAIL = "Email";
    private static final String USERNAME = "Username";
    private static final String PASSWORD = "Password";

    private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+"("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+NAME+" VARCHAR(255) NOT NULL,"+EMAIL+" NOT NULL, "+USERNAME+" TEXT NOT NULL, "+PASSWORD+" TEXT NOT NULL)";

    private static final String DROP_TABLE = "DROP TABLE IF EXISTS "+TABLE_NAME;

    private static final String  SELECT_ALL = "SELECT * FROM "+TABLE_NAME;

    private static final int VERSION_NUMBER = 2;


    private Context context;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION_NUMBER);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try{
            Toast.makeText(context,"onCreate Is Calling",Toast.LENGTH_LONG).show();
            sqLiteDatabase.execSQL(CREATE_TABLE);
        }catch(Exception e){
            Toast.makeText(context,"Exception"+e,Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        try{
            Toast.makeText(context,"onUpgrade Is Calling",Toast.LENGTH_LONG).show();
            sqLiteDatabase.execSQL(DROP_TABLE);
            onCreate(sqLiteDatabase);

        }catch(Exception ex){
            Toast.makeText(context,"Exception"+ex,Toast.LENGTH_LONG).show();
        }
    }

    //Inserting the Data

    public long insertData(UserDetails userDetails){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME,userDetails.getName());
        contentValues.put(EMAIL,userDetails.getEmail());
        contentValues.put(USERNAME,userDetails.getUsername());
        contentValues.put(PASSWORD,userDetails.getPassword());

        long rowId = sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        return rowId ;

    }

                    //Similar with Read Data
    public boolean findPassword(String username, String password){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(SELECT_ALL,null);

        Boolean result = false;

        if(cursor.getCount() == 0){
            Toast.makeText(context,"No Data is Found",Toast.LENGTH_LONG).show();
        }else{
            while(cursor.moveToNext()){
                String userName = cursor.getString(3);
                String passWord = cursor.getString(4);

                if(userName.equals(username) && passWord.equals(password)){
                    result = true;
                    break;
                }

            }

        }

        return result;


    }

}
