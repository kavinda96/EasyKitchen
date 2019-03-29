package com.ksg.easykitchen.dbConnection;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.provider.BaseColumns._ID;
import static com.ksg.easykitchen.dbConnection.DBConstants.DESCRPTION;
import static com.ksg.easykitchen.dbConnection.DBConstants.ISAVAILABLE;
import static com.ksg.easykitchen.dbConnection.DBConstants.PRICE;
import static com.ksg.easykitchen.dbConnection.DBConstants.PRODUCT;
import static com.ksg.easykitchen.dbConnection.DBConstants.TABLE_NAME;
import static com.ksg.easykitchen.dbConnection.DBConstants.WEIGHT;

public class DatabaseHelper extends SQLiteOpenHelper {


    private static final String DATABASE_NAME="kitchen_products.db";
    private static final int DATABASE_VERSION=1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME,null , DATABASE_VERSION);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + TABLE_NAME + "("
                + _ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT , "
                + PRODUCT + " TEXT NOT NULL , "
                + DESCRPTION + " TEXT NOT NULL , "
                + WEIGHT + " INTEGER , "
                + PRICE + " INTEGER , "
                + ISAVAILABLE + " TEXT NOT NULL ) ; " );
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);


    }
}
