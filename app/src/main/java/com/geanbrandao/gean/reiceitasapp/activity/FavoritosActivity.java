package com.geanbrandao.gean.reiceitasapp.activity;

import android.content.Intent;
import android.database.Cursor;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
        receitas.clear();
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
            receitas.add(fav);
        }
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onImageClicked(int position) {
        Intent i = new Intent(this, DetalhesFavoritasActivity.class);
        i.putExtra("id", receitas.get(position).getId());
        startActivity(i);
    }

    @Override
    protected void onResume() {
        Log.i("Ciclo", "onResume");
        super.onResume();
        separaDados();
        adapter.notifyDataSetChanged();
    }
}
