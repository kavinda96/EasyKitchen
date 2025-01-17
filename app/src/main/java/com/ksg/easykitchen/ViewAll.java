package com.ksg.easykitchen;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ksg.easykitchen.adapters.EditListAdapter;
import com.ksg.easykitchen.adapters.ViewListAdapter;
import com.ksg.easykitchen.dbConnection.DatabaseHelper;
import com.ksg.easykitchen.model.Products;

import java.util.ArrayList;

public class ViewAll extends AppCompatActivity {
    //declaring variables

    private DatabaseHelper databaseHelper;
    private ArrayList<Products> productsArrayList;
    private RecyclerView recyclerViewAll;
    private Button save_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all);
        databaseHelper = new DatabaseHelper(this);
        getSupportActionBar().setTitle("All Products");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        productsArrayList = new ArrayList<>();
        getAllDataList();

        recyclerViewAll = findViewById(R.id.recyclerViewAll);

        ViewListAdapter adapter = new ViewListAdapter(productsArrayList);
        recyclerViewAll.setHasFixedSize(true);
        recyclerViewAll.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewAll.setAdapter(adapter);
        save_btn = findViewById(R.id.save_btn);

        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(Products product : productsArrayList ){
                    databaseHelper.updateProduct(product);
                    Toast.makeText(ViewAll.this, "Updated Successfully!", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void getAllDataList(){
        /*Get All the product from the sqlite database  and intialize cursor object*/
        Cursor cursor = databaseHelper.getAllProducts();

        while (cursor.moveToNext()) {
            //creating products object
            Products products = new Products();
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
            AlertDialog alertDialog = new AlertDialog.Builder(ViewAll.this).create();
            alertDialog.setTitle("No Products !");
            alertDialog.setMessage("Please add some products to display. ");
            alertDialog.setCanceledOnTouchOutside(false);
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    });
            alertDialog.show();
            alertDialog.getButton(AlertDialog.BUTTON_NEUTRAL).setTextColor(getResources().getColor(R.color.colorAccent1));
        }
    }


}
