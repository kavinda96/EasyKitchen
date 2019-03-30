package com.ksg.easykitchen.dbConnection;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.provider.BaseColumns._ID;
import static com.ksg.easykitchen.dbConnection.DBConstants.ALL_PRODUCTS;
import static com.ksg.easykitchen.dbConnection.DBConstants.DESCRIPTION;
import static com.ksg.easykitchen.dbConnection.DBConstants.AVAILABLE_PRODUCTS;
import static com.ksg.easykitchen.dbConnection.DBConstants.PRICE;
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
                + ALL_PRODUCTS + " TEXT NOT NULL , "
                + DESCRIPTION + " TEXT NOT NULL , "
                + WEIGHT + " INTEGER , "
                + PRICE + " INTEGER , "
                + AVAILABLE_PRODUCTS + " TEXT NOT NULL ) ; " );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String all_product_name,String description,double weight, double price,String available_product_names) { //insert data into the table
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ALL_PRODUCTS,all_product_name);
        contentValues.put(DESCRIPTION,description);
        contentValues.put(WEIGHT,weight);
        contentValues.put(PRICE,price);
        contentValues.put(AVAILABLE_PRODUCTS,available_product_names);
        long result = db.insert(TABLE_NAME,null ,contentValues);
        /*
         * if having an error when inserting data
         * */
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllProducts(){
        String qryString = "Select * From "+TABLE_NAME;
        SQLiteDatabase sqlDb = this.getWritableDatabase();
        return sqlDb.rawQuery(qryString,null);
    }

    public Cursor getAvailableProducts(){
        String qryString = "Select * From "+TABLE_NAME+"Where ";
        SQLiteDatabase sqlDb = this.getWritableDatabase();
        return sqlDb.rawQuery(qryString,null);
    }
}
