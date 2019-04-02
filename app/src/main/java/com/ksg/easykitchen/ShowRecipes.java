package com.ksg.easykitchen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.ksg.easykitchen.apiConnection.FetchRecipes;
import com.ksg.easykitchen.dbConnection.DatabaseHelper;

import java.util.ArrayList;

public class ShowRecipes extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    private ArrayList<String> productNameList;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_recipes);
        databaseHelper = new DatabaseHelper(this);
        getSupportActionBar().setTitle("Recipes");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        recyclerView = findViewById(R.id.recyclerViewRecipe);

        productNameList  = getIntent().getStringArrayListExtra("PRODUCT_LIST");

        StringBuilder stringBuilder = new StringBuilder();

        if(productNameList.size() != 0){
            if(productNameList.size() == 1){
                stringBuilder.append(productNameList.get(0).toLowerCase());
            }
            if(productNameList.size() > 1){
                for(String item : productNameList){
                    stringBuilder.append(item+",");
                }
            }

            String searchQuery = stringBuilder.toString();

            new FetchRecipes(this, recyclerView, searchQuery).execute();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
