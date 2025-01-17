package com.ksg.easykitchen.dbConnection;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ksg.easykitchen.model.Products;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.ksg.easykitchen.dbConnection.DBConstants.PRODUCT_NAME;
import static com.ksg.easykitchen.dbConnection.DBConstants.DESCRIPTION;
import static com.ksg.easykitchen.dbConnection.DBConstants.IS_AVAILABLE;
import static com.ksg.easykitchen.dbConnection.DBConstants.PRICE;
import static com.ksg.easykitchen.dbConnection.DBConstants.TABLE_NAME;
import static com.ksg.easykitchen.dbConnection.DBConstants.WEIGHT;

public class DatabaseHelper extends SQLiteOpenHelper {


    private static final String DATABASE_NAME="kitchen_products.db";
    private static final int DATABASE_VERSION=1;

    private static final String RECIPE_TABLE_NAME="recipe";
    public static final String RECIPE_NAME="RECIPE_NAME";
    public static final String RECIPE_URL="RECIPE_URL";
    public static final String RECIPE_IMAGE_URL="RECIPE_IMAGE_URL";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME,null , DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //create the db table "products"
        db.execSQL("CREATE TABLE " + TABLE_NAME + "("
                + _ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT , "
                + PRODUCT_NAME + " TEXT NOT NULL , "
                + DESCRIPTION + " TEXT NOT NULL , "
                + WEIGHT + " INTEGER , "
                + PRICE + " INTEGER , "
                + IS_AVAILABLE + " INTEGER NOT NULL ) ; " );

        db.execSQL("CREATE TABLE " + RECIPE_TABLE_NAME + "("
                + _ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT , "
                + RECIPE_NAME + " TEXT NOT NULL , "
                + RECIPE_URL + " TEXT NOT NULL , "
                + RECIPE_IMAGE_URL + " TEXT NOT NULL ) ;" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + RECIPE_TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String all_product_name,String description,double weight, double price, int is_available) {
        //insert data into the table
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PRODUCT_NAME,all_product_name);
        contentValues.put(DESCRIPTION,description);
        contentValues.put(WEIGHT,weight);
        contentValues.put(PRICE,price);
        contentValues.put(IS_AVAILABLE,is_available);
        long result = db.insert(TABLE_NAME,null ,contentValues);
        /*
         * if having an error when inserting data
         * */
        if(result == -1)
            return false;
        else
            return true;
    }

    public boolean insertDataToRecipe(String recipe_name,String recipe_url,String recipe_img_url) { //insert data into the table
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(RECIPE_NAME,recipe_name);
        contentValues.put(RECIPE_URL,recipe_url);
        contentValues.put(RECIPE_IMAGE_URL,recipe_img_url);
        long result = db.insert(RECIPE_TABLE_NAME,null ,contentValues);
        /*
         * if having an error when inserting data
         * */
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllRecipes(){
        String qryString = "Select * From "+RECIPE_TABLE_NAME+" Order By "+RECIPE_NAME;
        SQLiteDatabase sqlDb = this.getWritableDatabase();
        return sqlDb.rawQuery(qryString,null);
    }

    public Cursor getAllProducts(){
        //get all the products from the data base table
        String qryString = "Select * From "+TABLE_NAME+" Order By "+PRODUCT_NAME;
        SQLiteDatabase sqlDb = this.getWritableDatabase();
        return sqlDb.rawQuery(qryString,null);
    }

    public Cursor getAvailableProducts(){
        //retrieve only the available product by checking is available is o or 0(here take only 1 values)
        String qryString = "Select * From "+TABLE_NAME+" Where "+IS_AVAILABLE+"=1";
        SQLiteDatabase sqlDb = this.getWritableDatabase();
        return sqlDb.rawQuery(qryString,null);
    }

    public ArrayList<Products> getSearchedItems(String searchString){
        //retrieve searched products according to the search string , and here checking both name an description
        ArrayList<Products> productsArrayList = new ArrayList<>();

        String qryString = "SELECT * FROM " +TABLE_NAME+ " WHERE " +PRODUCT_NAME+ " like \"%" + searchString + "%\" UNION " +
                "SELECT * FROM " +TABLE_NAME+ " WHERE " + DESCRIPTION + " like \"%" + searchString + "%\"";
        SQLiteDatabase sqlDb = this.getWritableDatabase();

        Cursor cursor = sqlDb.rawQuery(qryString, null);
        while (cursor.moveToNext()) {
            Products products = new Products();
            products.setId(cursor.getInt(0));
            products.setProduct(cursor.getString(1));
            products.setDescription(cursor.getString(2));
            products.setWeight(cursor.getDouble(3));
            products.setPrice(cursor.getDouble(4));
            products.setIsAvailable(cursor.getInt(5));
            productsArrayList.add(products);
        }
        return productsArrayList;
    }

    public int updateProduct(Products products) {
        //update the products on a edit action is performed by user
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(PRODUCT_NAME, products.getProduct());
        values.put(WEIGHT, products.getWeight());
        values.put(PRICE, products.getPrice());
        values.put(DESCRIPTION, products.getDescription());
        values.put(IS_AVAILABLE, products.getIsAvailable());

        return db.update(TABLE_NAME, values, _ID + " = ?",
                new String[] { String.valueOf(products.getId()) });
    }
}
