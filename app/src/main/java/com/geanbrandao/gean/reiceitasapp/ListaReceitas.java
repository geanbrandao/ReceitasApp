package com.geanbrandao.gean.reiceitasapp;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class ListaReceitas extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, ReceitasAdapater.ReceitaAdapaterListener {

    private List<Recipe> receitas = new ArrayList<>();
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ReceitasAdapater mAdapater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_receitas);

        recyclerView = findViewById(R.id.recycler_view_receitas);
        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(this);

        Yummly y = new Yummly(getResources().getString(R.string.appId),
                getResources().getString(R.string.appKey));
        SearchResult result;
        Log.i("RetornoApi", "antes do try ");
        try {
            result = y.search();
            //Log.i("RetornoApi", "entrou no try "+result.getMatches().size());
            for (Recipe recipe: result.getMatches()) {
                receitas.add(recipe);
                Log.i("RetornoApi",recipe.getRecipeName()+" - "+recipe.getId()+" - "+recipe.getSourceDisplayName());
            }

        } catch (Exception e) {
            Log.i("RetornoApi", "Erro "+e);
            e.printStackTrace();
        }


        mAdapater = new ReceitasAdapater(receitas, this, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapater);

        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                getReceitas();
            }
        });



    }

    private void getReceitas() {
        swipeRefreshLayout.setRefreshing(true);
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onImageClicked(int position) {

    }
}
