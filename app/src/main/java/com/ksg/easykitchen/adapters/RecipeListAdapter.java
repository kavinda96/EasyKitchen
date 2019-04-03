package com.ksg.easykitchen.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ksg.easykitchen.R;
import com.ksg.easykitchen.model.Recipe;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

public class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.ViewHolder> {

    private ArrayList<Recipe> recipeArrayList;
    private Context context;

    public RecipeListAdapter(Context context, ArrayList<Recipe> recipeArrayList) {
        this.recipeArrayList = recipeArrayList;
        this.context = context;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecipeListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View listItem= layoutInflater.inflate(R.layout.recipe_list, viewGroup, false);
        return new RecipeListAdapter.ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeListAdapter.ViewHolder viewHolder, int i) {
        final Recipe product = recipeArrayList.get(i);
        viewHolder.productName.setText(product.getName());
//        new DownloadImageTask(viewHolder.imageView).execute(product.getImgUrl());

        Glide.with(viewHolder.imageView.getContext())
                .load(product.getImgUrl())
                .into(viewHolder.imageView);

        viewHolder.clickLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewIntent = new Intent("android.intent.action.VIEW", Uri.parse(product.getRedirectUrl()));
                context.startActivity(viewIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return recipeArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView productName;
        public ImageView imageView;
        public LinearLayout clickLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.clickLayout = itemView.findViewById(R.id.clickLayout);
            this.productName =  itemView.findViewById(R.id.recipeTitle);
            this.imageView = itemView.findViewById(R.id.recipeImage);
        }
    }

//    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
//        ImageView bmImage;
//
//        public DownloadImageTask(ImageView bmImage) {
//            this.bmImage = bmImage;
//        }
//
//        protected Bitmap doInBackground(String... urls) {
//            String urldisplay = urls[0];
//            Log.e("Error", urldisplay);
//            Bitmap mIcon11 = null;
//            URL url = null;
//            try {
//                url = new URL(urldisplay);
//                mIcon11 = BitmapFactory.decodeStream(url.openConnection().getInputStream());
//            } catch (Exception e) {
//                Log.e("Error", e.getMessage());
//                e.printStackTrace();
//            }
//            return mIcon11;
//        }
//
//        protected void onPostExecute(Bitmap result) {
//            bmImage.setImageBitmap(result);
//        }
//    }
}


