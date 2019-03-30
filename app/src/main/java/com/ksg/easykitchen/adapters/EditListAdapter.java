package com.ksg.easykitchen.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ksg.easykitchen.EditActivity;
import com.ksg.easykitchen.EditData;
import com.ksg.easykitchen.R;
import com.ksg.easykitchen.model.Products;

import java.util.ArrayList;

public class EditListAdapter extends RecyclerView.Adapter<EditListAdapter.ViewHolder> {

    private ArrayList<Products> productsArrayList;

    public EditListAdapter(ArrayList<Products> productsArrayList) {
        this.productsArrayList = productsArrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View listItem= layoutInflater.inflate(R.layout.list_item, viewGroup, false);
        return new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int i) {
        final Products product = productsArrayList.get(i);
        viewHolder.productName.setText(productsArrayList.get(i).getProduct());
        viewHolder.productPrice.setText("Price : Rs."+Double.toString(product.getPrice()));
        viewHolder.productWeight.setText("Weight : "+Double.toString(product.getWeight())+"g");
        viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                final Intent x =new Intent(context, EditData.class);
                x.putExtra("PRODUCT",product);
                context.startActivity(x);
                ((EditActivity)context).finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return productsArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView productName, productPrice, productWeight;
        public LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            this.productName =  itemView.findViewById(R.id.productName);
            this.productPrice = itemView.findViewById(R.id.productPrice);
            this.productWeight = itemView.findViewById(R.id.productWeight);
            linearLayout = itemView.findViewById(R.id.linearLayout);
        }
    }
}
