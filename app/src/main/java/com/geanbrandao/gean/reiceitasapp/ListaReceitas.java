package com.geanbrandao.gean.reiceitasapp;

import android.content.Intent;
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
            for (Recipe receita: result.getMatches()) {
                String url = receita.getSmallImageUrls().get(0);
                receita.getSmallImageUrls().set(0, MelhoraImagem.alteraUrl(url));
                receitas.add(receita);
                //Log.i("RetornoApi",recipe.getRecipeName()+" - "+recipe.getId()+" - "+recipe.getSourceDisplayName());
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
        Log.i("RespostaRecycler", " = "+receitas.get(position).getRecipeName());
        Intent i = new Intent(this, DetalhesActivity.class);
        i.putExtra("smallImageUrls", MelhoraImagem.alteraUrl(receitas.get(position).getSmallImageUrls().get(0)));
        i.putExtra("sourceDisplayName", receitas.get(position).getSourceDisplayName());
        i.putExtra("sourceDisplayName", receitas.get(position).getSourceDisplayName());
        i.putExtra("quantidadeIngredientes", receitas.get(position).getIngredients().size());
        int aux = 0;
        for (String s: receitas.get(position).getIngredients()) {
            i.putExtra("ing"+aux, s); // adiciona um ingrediente
            ++aux;
        }
        i.putExtra("id", receitas.get(position).getId());
        i.putExtra("recipeName", receitas.get(position).getRecipeName());
        i.putExtra("totalTimeInSeconds", receitas.get(position).getTotalTimeInSeconds());
        i.putExtra("rating", receitas.get(position).getRating());
        startActivity(i);
    }
}
