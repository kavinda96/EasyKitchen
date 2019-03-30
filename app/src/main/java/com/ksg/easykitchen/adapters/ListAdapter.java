package com.ksg.easykitchen.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ksg.easykitchen.EditActivity;
import com.ksg.easykitchen.EditData;
import com.ksg.easykitchen.Home;
import com.ksg.easykitchen.R;
import com.ksg.easykitchen.model.ListData;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private ListData[] listData;

    public ListAdapter(ListData[] listData) {
        this.listData = listData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View listItem= layoutInflater.inflate(R.layout.list_item, viewGroup, false);
        return new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int i) {
        final ListData myListData = listData[i];
        final String itemName = listData[i].getDescription();
        viewHolder.textView.setText(listData[i].getDescription());
        viewHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                final Intent x =new Intent(context, EditData.class);
                x.putExtra("ITEM_NAME",itemName);
                context.startActivity(x);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listData.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public RelativeLayout relativeLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            this.textView =  itemView.findViewById(R.id.textView);
            relativeLayout = itemView.findViewById(R.id.relativeLayout);
        }
    }
}
