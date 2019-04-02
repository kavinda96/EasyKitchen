package com.ksg.easykitchen;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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

            //Check network connection
            ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = null;
            if (connMgr != null) {
                networkInfo = connMgr.getActiveNetworkInfo();
            }

            if (networkInfo != null && networkInfo.isConnected() && searchQuery.length() != 0) {
                new FetchRecipes(this, recyclerView, searchQuery).execute();
            } else {
                if (searchQuery.length() != 0) {
                    AlertDialog alertDialog = new AlertDialog.Builder(ShowRecipes.this).create();
                    alertDialog.setTitle("No Internet!");
                    alertDialog.setMessage("Please check your Internet Connection ");
                    alertDialog.setCanceledOnTouchOutside(false);
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    final Intent x =new Intent(ShowRecipes.this, RecipesActivity.class);
                                    startActivity(x);
                                    finish();
                                }
                            });
                    alertDialog.show();
                    alertDialog.getButton(AlertDialog.BUTTON_NEUTRAL).setTextColor(getResources().getColor(R.color.colorAccent1));
                }
            }


        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            final Intent x =new Intent(ShowRecipes.this, RecipesActivity.class);
            startActivity(x);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        final Intent x =new Intent(ShowRecipes.this, RecipesActivity.class);
        startActivity(x);
        finish();
    }
}
