package com.ksg.easykitchen;

import android.content.Intent;
import android.database.Cursor;
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

        searchRecipes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(productNameList.size() != 0){
                    final Intent x =new Intent(RecipesActivity.this, ShowRecipes.class);
                    x.putExtra("PRODUCT_LIST", productNameList);
                    startActivity(x);
                    productNameList.clear();
                }else {
                    Toast.makeText(RecipesActivity.this, "Please select a product to continue!", Toast.LENGTH_SHORT).show();
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
