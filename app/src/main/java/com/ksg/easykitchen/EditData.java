package com.ksg.easykitchen;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.ksg.easykitchen.dbConnection.DatabaseHelper;
import com.ksg.easykitchen.model.Products;

import static com.ksg.easykitchen.dbConnection.DBConstants.TABLE_NAME;

public class EditData extends AppCompatActivity {

    private EditText editName, editWeight, editPrice, editDescription;
    private CheckBox checkIsAvailable;
    private Products product;
    private Button updateBtn,deleteBtn;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data);
        databaseHelper = new DatabaseHelper(this);

        product= (Products) getIntent().getSerializableExtra("PRODUCT");
        getSupportActionBar().setTitle(product.getProduct());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        editName = findViewById(R.id.editName);
        editWeight = findViewById(R.id.editWeight);
        editPrice = findViewById(R.id.editPrice);
        editDescription = findViewById(R.id.editDescription);
        checkIsAvailable = findViewById(R.id.checkIsAvailable);
        updateBtn = findViewById(R.id.updateBtn);
        deleteBtn = findViewById(R.id.deleteBtn);

        editName.setText(product.getProduct());
        editDescription.setText(product.getDescription());
        editWeight.setText(Double.toString(product.getWeight()));
        editPrice.setText(Double.toString(product.getPrice()));
        if(product.getIsAvailable() == 1){
            checkIsAvailable.setChecked(true);
        }else{
            checkIsAvailable.setChecked(false);
        }

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateProductDetails();
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
            AlertDialog alertDialog = new AlertDialog.Builder(EditData.this).create();
            alertDialog.setTitle("Delete Product!");
            alertDialog.setMessage("Are you sure to delete this product? ");
            alertDialog.setCanceledOnTouchOutside(false);
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    delete();
                   /* final Intent x = new Intent(EditData.this, EditActivity.class);
                    startActivity(x);*/
                    finish();
                }
            });
                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "CANCEL",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                             //   delete();
                                //final Intent x = new Intent(EditData.this, EditActivity.class);
                                //startActivity(x);
                               // finish();
                            }
                        });

            alertDialog.show();
            alertDialog.getButton(AlertDialog.BUTTON_NEUTRAL).setTextColor(getResources().getColor(R.color.green));
                alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.colorAccent1));


            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        final Intent x =new Intent(EditData.this, EditActivity.class);
        startActivity(x);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            super.onBackPressed();
            final Intent x =new Intent(EditData.this, EditActivity.class);
            startActivity(x);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void updateProductDetails(){
        String pName = null, pDescription = null;
        double pWeight = 0, pPrice = 0;

        if(!editName.getText().toString().equals("")){
            pName = editName.getText().toString().toUpperCase();
        }else{
            if(TextUtils.isEmpty(editName.getText().toString())) {
                editName.setError("Name can not be empty");
                return;
            }
        }

        if(editWeight.getText().length() != 0){
            pWeight = Double.parseDouble(editWeight.getText().toString());
        }else{
            if(TextUtils.isEmpty(editWeight.getText().toString())) {
                editWeight.setError("Weight can not be empty");
                return;
            }
        }

        if(editPrice.getText().length() != 0){
            pPrice = Double.parseDouble(editPrice.getText().toString());
        }else{
            if(TextUtils.isEmpty(editPrice.getText().toString())) {
                editPrice.setError("Price can not be empty");
                return;
            }
        }

        if(!editDescription.getText().toString().equals("")){
            pDescription = editDescription.getText().toString();
        }else{
            if(TextUtils.isEmpty(editDescription.getText().toString())) {
                editDescription.setError("Description can not be empty");
                return;
            }
        }

        if(checkIsAvailable.isChecked()){
            product.setIsAvailable(1);
        }else{
            product.setIsAvailable(0);
        }

        if(!pName.equals("") && !pDescription.equals("") && pWeight != 0 && pPrice != 0){
            product.setProduct(pName);
            product.setDescription(pDescription);
            product.setPrice(pPrice);
            product.setWeight(pWeight);
            databaseHelper.updateProduct(product);
            Toast.makeText(this, "Data updated successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    public void delete() {//delete a product item

        SQLiteDatabase database=databaseHelper.getWritableDatabase();
        database.delete(TABLE_NAME,"_id="+String.valueOf(product.getId()),null);
        Intent intent = new Intent(this, EditActivity.class);
        //intent.putExtra("go back","Reload main class");
        startActivity(intent);
        Toast toast=Toast.makeText(this,"Deleted",Toast.LENGTH_SHORT);
        toast.show();

    }
}
