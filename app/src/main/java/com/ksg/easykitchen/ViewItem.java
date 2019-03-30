package com.ksg.easykitchen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.ksg.easykitchen.model.Products;

public class ViewItem extends AppCompatActivity {

    private TextView prodName, prodWeight, prodPrice, prodDescription;
    private CheckBox prodIsAvailable;
    private Products product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_item);

        product= (Products) getIntent().getSerializableExtra("PRODUCT");
        getSupportActionBar().setTitle(product.getProduct());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        prodName = findViewById(R.id.prodName);
        prodWeight = findViewById(R.id.prodWeight);
        prodPrice = findViewById(R.id.prodPrice);
        prodDescription = findViewById(R.id.prodDescription);
        prodIsAvailable = findViewById(R.id.prodIsAvailable);

        if(product.getIsAvailable() == 1){
            prodIsAvailable.setChecked(true);
            prodIsAvailable.setEnabled(false);
        }else{
            prodIsAvailable.setChecked(false);
            prodIsAvailable.setEnabled(false);
        }

        prodName.setText(product.getProduct());
        prodDescription.setText(product.getDescription());
        prodWeight.setText(Double.toString(product.getWeight()));
        prodPrice.setText(Double.toString(product.getPrice()));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
