package com.ksg.easykitchen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ksg.easykitchen.adapters.SearchListAdapter;
import com.ksg.easykitchen.dbConnection.DatabaseHelper;
import com.ksg.easykitchen.model.Products;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
    private ArrayList<Products> productsArrayList;
    private RecyclerView recyclerViewSearch;
    private Button searchButton;
    private EditText searchText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        databaseHelper = new DatabaseHelper(this);
        getSupportActionBar().setTitle("Search Products");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        productsArrayList = new ArrayList<>();
        searchText = findViewById(R.id.searchText);
        recyclerViewSearch = findViewById(R.id.recyclerViewSearch);
        searchButton = findViewById(R.id.searchButton);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!searchText.getText().toString().equals("")){
                    productsArrayList = databaseHelper.getSearchedItems(searchText.getText().toString());

                    SearchListAdapter adapter = new SearchListAdapter(productsArrayList);
                    recyclerViewSearch.setHasFixedSize(true);
                    recyclerViewSearch.setLayoutManager(new LinearLayoutManager(v.getContext()));
                    recyclerViewSearch.setAdapter(adapter);

                }else{
                    if(TextUtils.isEmpty(searchText.getText().toString())) {
                        searchText.setError("Search text can not be empty");
                    }
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

}
