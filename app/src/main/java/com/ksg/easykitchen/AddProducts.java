package com.ksg.easykitchen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ksg.easykitchen.dbConnection.DatabaseHelper;

public class AddProducts extends AppCompatActivity {

/*Declaring instance variables*/
    private EditText product_name,product_weight,product_price,product_description;
    private Button saveButton;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_products);
        getSupportActionBar().setTitle("Add Data");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
// initializing variables
        databaseHelper = new DatabaseHelper(this);
        product_name = findViewById(R.id.product_name);
        product_weight = findViewById(R.id.product_weight);
        product_price = findViewById(R.id.product_price);
        product_description = findViewById(R.id.product_description);
        saveButton = findViewById(R.id.saveButton);

        saveButton.setOnClickListener(new View.OnClickListener() {
            //set on click listner to Save button
            @Override
            public void onClick(View v) {
                saveProductsToDb();
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

    public void saveProductsToDb(){
        String pName = null, pDescription = null; // declaring and initializing method variables
        double pWeight = 0, pPrice = 0;

        /*Validating product name, description,weight and price from user input,cant be empty*/
        if(!product_name.getText().toString().equals("")){
            //reassigning values from user input to p variables(method variables)
            pName = product_name.getText().toString().toUpperCase();
        }else{
            if(TextUtils.isEmpty(product_name.getText().toString())) {
                product_name.setError("Name can not be empty");
                return;
            }
        }

        if(product_weight.getText().length() != 0){
            pWeight = Double.parseDouble(product_weight.getText().toString());
        }else{
            if(TextUtils.isEmpty(product_weight.getText().toString())) {
                product_weight.setError("Weight can not be empty");
                return;
            }
        }

        if(product_price.getText().length() != 0){
            pPrice = Double.parseDouble(product_price.getText().toString());
        }else{
            if(TextUtils.isEmpty(product_price.getText().toString())) {
                product_price.setError("Price can not be empty");
                return;
            }
        }

        if(!product_description.getText().toString().equals("")){
            pDescription = product_description.getText().toString();
        }else{
            if(TextUtils.isEmpty(product_description.getText().toString())) {
                product_description.setError("Description can not be empty");
                return;
            }
        }

        if(!pName.equals("") && !pDescription.equals("") && pWeight != 0 && pPrice != 0){
            /*When the user click save button all the input fields become empty and the values user entered will save to
            * sqlite DB*/
            product_name.setText("");
            product_description.setText("");
            product_weight.setText("");
            product_price.setText("");

            /*Invoking insert data method from databasehelper class and passing method variables as arguments and
            * availability is set to Zero by default*/
            databaseHelper.insertData(pName,pDescription,pWeight,pPrice,0);
            Toast.makeText(this, "Data added successfully!", Toast.LENGTH_SHORT).show();
        }

    }
}
