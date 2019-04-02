package com.ksg.easykitchen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class Home extends AppCompatActivity{

    CardView add, edit, viewAllItemCard, viewAvailableItemCard, searchItemCard, recipeCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        add = findViewById(R.id.addItemCard);
        edit = findViewById(R.id.editItemCard);
        viewAllItemCard = findViewById(R.id.viewAllItemCard);
        viewAvailableItemCard = findViewById(R.id.viewAvailableItemCard);
        searchItemCard = findViewById(R.id.searchItemCard);
        recipeCard = findViewById(R.id.recipeCard);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent x =new Intent(Home.this, AddProducts.class);
                startActivity(x);
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent x =new Intent(Home.this, EditActivity.class);
                startActivity(x);
            }
        });

        viewAllItemCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent x =new Intent(Home.this, ViewAll.class);
                startActivity(x);
            }
        });

        viewAvailableItemCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent x =new Intent(Home.this, ViewAvailable.class);
                startActivity(x);
            }
        });

        searchItemCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent x =new Intent(Home.this, SearchActivity.class);
                startActivity(x);
            }
        });

        recipeCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent x =new Intent(Home.this, RecipesActivity.class);
                startActivity(x);
            }
        });

    }

}
