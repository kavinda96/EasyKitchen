package com.ksg.easykitchen.apiConnection;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import com.ksg.easykitchen.adapters.RecipeListAdapter;
import com.ksg.easykitchen.model.Recipe;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class FetchRecipes extends AsyncTask<Void, Void, ArrayList<Recipe>> {

    private ProgressDialog dialog ;
    private ArrayList<Recipe> recipeArrayList;
    private Context context;
    private RecyclerView recyclerView;
    private RecipeListAdapter recipeListAdapter;
    private String searchText;
  //  private int itemCount;

    public FetchRecipes(Context context, RecyclerView recyclerView, String searchText) {
        this.recyclerView = recyclerView;
        this.context = context;
        this.searchText = searchText;
    }

    @Override
    protected ArrayList<Recipe> doInBackground(Void... voids) {
        return this.fetchData();
    }

    private ArrayList<Recipe> fetchData() {
        recipeArrayList = new ArrayList<>();
        String jsonObj = NetworkUtils.getRecipeInfo(searchText);
        try {

            int itemCount;
            String title;
            String image_url;
            String source_url;

            JSONObject jsonObject = new JSONObject(jsonObj);
            itemCount = jsonObject.getInt("count");
            JSONArray itemsArray = jsonObject.getJSONArray("recipes");

            if (itemCount > 0) {
                for(int i = 0; i < itemCount; i++){
                    JSONObject book = itemsArray.getJSONObject(i);
                    String recipeTitle = book.getString("title");
                    String recipeUrl = book.getString("f2f_url");
                    String recipeImage = book.getString("image_url");
                    Log.d("Fetch", recipeTitle);

                    try {
                        Recipe recipe = new Recipe();
                        title = recipeTitle;
                        source_url = recipeUrl;
                        image_url = recipeImage;
                        recipe.setName(title);
                        recipe.setRedirectUrl(source_url);
                        recipe.setImgUrl(image_url);
                        recipeArrayList.add(recipe);
                        Log.d("Fetch", title);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return recipeArrayList;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        this.dialog = new ProgressDialog(context);
        this.dialog.setMessage("Please Wait");
        this.dialog.show();
    }

    @Override
    protected void onPostExecute(ArrayList<Recipe> recipes) {
        super.onPostExecute(recipes);
        recipeListAdapter = new RecipeListAdapter(context, recipes);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(recipeListAdapter);
        if (dialog.isShowing()) {
            dialog.dismiss();
        }
    }
}
