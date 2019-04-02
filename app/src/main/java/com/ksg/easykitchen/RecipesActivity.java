package com.ksg.easykitchen;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ksg.easykitchen.adapters.SelectAvailableListAdapter;
import com.ksg.easykitchen.adapters.ViewListAdapter;
import com.ksg.easykitchen.apiConnection.FetchRecipes;
import com.ksg.easykitchen.dbConnection.DatabaseHelper;
import com.ksg.easykitchen.model.Products;

import java.util.ArrayList;

public class RecipesActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
    private ArrayList<Products> productsArrayList;
    private ArrayList<String> productNameList;
    private RecyclerView recyclerViewAvailableProducts;
    private Button searchRecipes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);
        databaseHelper = new DatabaseHelper(this);
        getSupportActionBar().setTitle("View Recipes");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        productsArrayList = new ArrayList<>();
        productNameList = new ArrayList<>();
        getAllDataList();

        recyclerViewAvailableProducts = findViewById(R.id.recyclerViewAvailableProductsRecipe);
        SelectAvailableListAdapter adapter = new SelectAvailableListAdapter(productsArrayList, productNameList);
        recyclerViewAvailableProducts.setHasFixedSize(true);
        recyclerViewAvailableProducts.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewAvailableProducts.setAdapter(adapter);
        searchRecipes = findViewById(R.id.searchRecipes);

        //Check network connection
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;
        if (connMgr != null) {
            networkInfo = connMgr.getActiveNetworkInfo();
        }

        if (networkInfo != null && networkInfo.isConnected()) {
            searchRecipes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(productNameList.size() != 0){
                        final Intent x =new Intent(RecipesActivity.this, ShowRecipes.class);
                        x.putExtra("PRODUCT_LIST", productNameList);
                        startActivity(x);
                        productNameList.clear();
                        finish();
                    }else {
                        Toast.makeText(RecipesActivity.this, "Please select a product to continue!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else {
            AlertDialog alertDialog = new AlertDialog.Builder(RecipesActivity.this).create();
            alertDialog.setTitle("No Internet!");
            alertDialog.setMessage("Please check your Internet Connection ");
            alertDialog.setCanceledOnTouchOutside(false);
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            final Intent x = new Intent(RecipesActivity.this, Home.class);
                            startActivity(x);
                            finish();
                        }
                    });
            alertDialog.show();
            alertDialog.getButton(AlertDialog.BUTTON_NEUTRAL).setTextColor(getResources().getColor(R.color.colorAccent1));

        }


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            final Intent x =new Intent(RecipesActivity.this, Home.class);
            startActivity(x);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        final Intent x =new Intent(RecipesActivity.this, Home.class);
        startActivity(x);
        finish();
    }

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
    }
}
