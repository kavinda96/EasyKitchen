package com.ksg.easykitchen.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.ksg.easykitchen.R;
import com.ksg.easykitchen.model.Products;

import java.util.ArrayList;

public class SelectAvailableListAdapter extends RecyclerView.Adapter<SelectAvailableListAdapter.ViewHolder> {

    private ArrayList<Products> productsArrayList;
    private ArrayList<String> productNameList;

    public SelectAvailableListAdapter(ArrayList<Products> productsArrayList, ArrayList<String> productNameList) {
        this.productsArrayList = productsArrayList;
        this.productNameList = productNameList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View listItem= layoutInflater.inflate(R.layout.select_available_list_item, viewGroup, false);
        return new SelectAvailableListAdapter.ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        final Products product = productsArrayList.get(i);
        viewHolder.pName.setText(product.getProduct());
        viewHolder.pPrice.setText("Price : Rs."+Double.toString(product.getPrice()));
        viewHolder.pWeight.setText("Weight : "+Double.toString(product.getWeight())+"g");

        viewHolder.pIsAvailable.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(buttonView.isChecked()){
                    productNameList.add(product.getProduct());
                }else{
                    for(int i = 0; i < productNameList.size(); i++){
                        if (productNameList.get(i).equalsIgnoreCase(product.getProduct())) {
                            Log.d("Abcd", productNameList.get(i));
                            productNameList.remove(i);
                        }
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return productsArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView pName, pPrice, pWeight;
        public CheckBox pIsAvailable;

        public ViewHolder(View itemView) {
            super(itemView);
            this.pName = itemView.findViewById(R.id.productName);
            this.pPrice = itemView.findViewById(R.id.productPrice);
            this.pWeight = itemView.findViewById(R.id.productWeight);
            this.pIsAvailable = itemView.findViewById(R.id.checkIsAvailable);
        }
    }
}
