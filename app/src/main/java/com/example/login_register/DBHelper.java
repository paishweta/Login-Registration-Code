package com.example.login_register;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "FIT_IT.db";
    private static final String users = "users";

    private String TAG;

    public DBHelper(@Nullable Context context) {
        super(context, "FIT_IT.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        String users = "create Table users(firstname TEXT, lastname TEXT,email TEXT primary key,password TEXT)";
        String plants = "create Table plants(ID INTEGER primary key AUTOINCREMENT NOT NULL,Name TEXT, ScientificName TEXT, Family TEXT, Sunlight TEXT, Water TEXT, Fertilizer TEXT, Benefits TEXT,Images BLOB)";
        String saved_plants = "create Table saved_plants(user_email TEXT, Scientific_Name TEXT, CONSTRAINT fk_users FOREIGN KEY (user_email) REFERENCES users (email), CONSTRAINT fk_plants FOREIGN KEY (Scientific_Name) REFERENCES plants (ScientificName))";
        MyDB.execSQL(users);
        MyDB.execSQL(plants);
        MyDB.execSQL(saved_plants);
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists " + users);
    }

    public Boolean insertData(String firstName, String lastName, String email, String password) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("firstname", firstName);
        contentValues.put("lastname", lastName);
        contentValues.put("email", email);
        contentValues.put("password", password);
        long result = MyDB.insert("users", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Boolean checkUser(String email) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where email = ?", new String[]{email});
        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean checkUserPass(String email, String password) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where email = ? and password= ?", new String[]{email, password});
        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

}