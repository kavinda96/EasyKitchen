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

import com.ksg.easykitchen.adapters.ViewListAdapter;
import com.ksg.easykitchen.dbConnection.DatabaseHelper;
import com.ksg.easykitchen.model.Products;

import java.util.ArrayList;

public class ViewAvailable extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
    private ArrayList<Products> productsArrayList;
    private RecyclerView recyclerViewAvailable;
    private Button saveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_available);
        databaseHelper = new DatabaseHelper(this);
        getSupportActionBar().setTitle("Available Products");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        productsArrayList = new ArrayList<>();
        getAllDataList();

        recyclerViewAvailable = findViewById(R.id.recyclerViewAvailable);
        ViewListAdapter adapter = new ViewListAdapter(productsArrayList);
        recyclerViewAvailable.setHasFixedSize(true);
        recyclerViewAvailable.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewAvailable.setAdapter(adapter);
        saveBtn = findViewById(R.id.saveBtn);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(Products product : productsArrayList ){
                    databaseHelper.updateProduct(product);
                    Toast.makeText(ViewAvailable.this, "Updated Successfully!", Toast.LENGTH_SHORT).show();
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


    /*Get All the product from the sqlite database  and intialize cursor object*/
    private void getAllDataList(){
        Cursor cursor = databaseHelper.getAvailableProducts();

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
        if(productsArrayList.size()==0){
            AlertDialog alertDialog = new AlertDialog.Builder(ViewAvailable.this).create();
            alertDialog.setTitle("No Products !");
            alertDialog.setMessage("Please add some products to kitchen. ");
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
