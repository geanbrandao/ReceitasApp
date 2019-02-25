package com.geanbrandao.gean.reiceitasapp.activity;

import android.content.Intent;
import android.database.Cursor;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.geanbrandao.gean.reiceitasapp.R;
import com.geanbrandao.gean.reiceitasapp.adapter.FavoritasAdapter;
import com.geanbrandao.gean.reiceitasapp.bdOffline.ControleBD;
import com.geanbrandao.gean.reiceitasapp.helper.ReceitasFavoritas;

import java.util.ArrayList;
import java.util.List;

public class FavoritosActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, FavoritasAdapter.FavoritasAdapaterListener {

    private RecyclerView recyclerView;
    private List<ReceitasFavoritas> receitas = new ArrayList<>();
    private ControleBD crud;
    private Cursor cursor;
    private Button mVoltar;
    private FavoritasAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoritos);

        mVoltar = findViewById(R.id.b_voltar_fav_off);

        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout_favoritos);
        swipeRefreshLayout.setOnRefreshListener(this);

        recyclerView = findViewById(R.id.recycler_view_favoritos);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        separaDados();
        adapter = new FavoritasAdapter(this, receitas, this);
        recyclerView.setAdapter(adapter);

        mVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void finish() {
        super.finish();
    }

    public void separaDados(){
         /*
                CriarBD.ID_RECEITA,
                CriarBD.TOTAL_TIME_IN_SECONDS,
                CriarBD.SOURCE_DISPLAY_NAME,
                CriarBD.NOME_RECEITA,
                CriarBD.INGREDIENTES,
                CriarBD.FOTO,
                CriarBD.PORCAO,
                CriarBD.PREPARO,
                CriarBD.RATING,
                CriarBD.TEMPO_STRING
        */
        crud = new ControleBD(getBaseContext());
        List<String> ids;
        ids = crud.readIDs();
        for(String s: ids) {
            Cursor cursor = crud.read(s);
            ReceitasFavoritas fav = new ReceitasFavoritas();
            fav.setId(cursor.getString(0));
            fav.setNomeFonte(cursor.getString(1));
            fav.setNome(cursor.getString(2));
            fav.setIngredientes(cursor.getString(3));
            fav.setFoto(cursor.getBlob(4));
            fav.setPorcao(cursor.getString(5));
            fav.setPreparo(cursor.getString(6));
            fav.setRating(cursor.getInt(7));
            fav.setTempo(cursor.getString(8));
        }
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        receitas.clear();
        separaDados();
        adapter.notifyDataSetChanged();
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onImageClicked(int position) {
        //Log.i("RespostaRecycler", " = "+ receitas.get(position).getRecipeName());
        Intent i = new Intent(this, DetalhesFavoritasActivity.class);
        i.putExtra("foto", receitas.get(position).getFoto());
        i.putExtra("nomeFonte", receitas.get(position).getNomeFonte());
        i.putExtra("ingredientes", receitas.get(position).getIngredientes());
        i.putExtra("id", receitas.get(position).getId());
        i.putExtra("nome", receitas.get(position).getNome());
        //i.putExtra("totalTimeSeg", receitas.get(position).getTotalTimeSeg());
        i.putExtra("rating", receitas.get(position).getRating());
        startActivity(i);
    }

    @Override
    protected void onResume() {
        super.onResume();
        separaDados();
        adapter.notifyDataSetChanged();
    }
}
