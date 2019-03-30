package com.ksg.easykitchen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.ksg.easykitchen.adapters.ListAdapter;
import com.ksg.easykitchen.model.ListData;

public class EditActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        getSupportActionBar().setTitle("Edit Data");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        ListData[] myListData = new ListData[]{
                new ListData("Email"),
                new ListData("Info"),
                new ListData("Delete"),
                new ListData("Dialer"),
                new ListData("Alert"),
                new ListData("Map"),
                new ListData("Email"),
                new ListData("Info"),
                new ListData("Delete"),
                new ListData("Dialer"),
                new ListData("Alert"),
                new ListData("Map"),
        };

        recyclerView = findViewById(R.id.recyclerView);
        ListAdapter adapter = new ListAdapter(myListData);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
