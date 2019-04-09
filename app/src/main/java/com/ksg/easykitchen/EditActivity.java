package com.ksg.easykitchen;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.ksg.easykitchen.adapters.EditListAdapter;
import com.ksg.easykitchen.dbConnection.DatabaseHelper;
import com.ksg.easykitchen.model.Products;

import java.util.ArrayList;

public class EditActivity extends AppCompatActivity {
    /*Declaring instance variables*/
    private RecyclerView recyclerView;
    private DatabaseHelper databaseHelper;
    private ArrayList<Products> productsArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        databaseHelper = new DatabaseHelper(this);
        getSupportActionBar().setTitle("Edit Data");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        productsArrayList = new ArrayList<>();

        getAllDataList();
// initializing variables
        recyclerView = findViewById(R.id.recyclerView);
        EditListAdapter adapter = new EditListAdapter(productsArrayList);//passing populated arraylist as argument to the adapter
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void getAllDataList(){
        /*Get All the product from the sqlite database  and intialize cursor object*/
        Cursor cursor = databaseHelper.getAllProducts();
        /*Iterate the cursor object until it is null*/
        while (cursor.moveToNext()) {
            //creating products object
            Products products = new Products();

            //Setting values through setters to the products object
            products.setId(cursor.getInt(0));
            products.setProduct(cursor.getString(1));
            products.setDescription(cursor.getString(2));
            products.setWeight(cursor.getDouble(3));
            products.setPrice(cursor.getDouble(4));
            products.setIsAvailable(cursor.getInt(5));

            /* Assign the iterated cursor values that are assigned to the products
            object to the arraylist*/
            productsArrayList.add(products);


        }

        if(productsArrayList.size()==0){
            AlertDialog alertDialog = new AlertDialog.Builder(EditActivity.this).create();
            alertDialog.setTitle("No Products to Edit!");
            alertDialog.setMessage("Please add some products to edit. ");
            alertDialog.setCanceledOnTouchOutside(false);
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            /*final Intent x =new Intent(EditActivity.this, Home.class);
                            startActivity(x);*/
                            finish();
                        }
                    });
            alertDialog.show();
            alertDialog.getButton(AlertDialog.BUTTON_NEUTRAL).setTextColor(getResources().getColor(R.color.colorAccent1));
        }
    }


   /* @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            super.onBackPressed();
            final Intent x =new Intent(EditActivity.this, Home.class);
            startActivity(x);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }*/

/*User navigate to the home when he press the top back button*/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

   /* @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }*/



/*    @Override
    public void onBackPressed() {
       *//* //super.onBackPressed();
        final Intent x =new Intent(EditActivity.this, Home.class);
        startActivity(x);
        finish();*//*
    }*/
}
