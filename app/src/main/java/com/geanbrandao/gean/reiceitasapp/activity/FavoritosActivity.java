package com.geanbrandao.gean.reiceitasapp.activity;

import android.database.Cursor;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.geanbrandao.gean.reiceitasapp.R;
import com.geanbrandao.gean.reiceitasapp.adapter.FavoritasAdapter;
import com.geanbrandao.gean.reiceitasapp.adapter.ReceitasAdapter;
import com.geanbrandao.gean.reiceitasapp.bdOffline.ControleBD;
import com.geanbrandao.gean.reiceitasapp.bdOffline.CriarBD;
import com.geanbrandao.gean.reiceitasapp.helper.ReceitasFavoritas;
import com.geanbrandao.gean.reiceitasapp.json.Recipe;

import java.util.ArrayList;
import java.util.List;

public class FavoritosActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, ReceitasAdapter.ReceitaAdapaterListener {

    private RecyclerView recyclerView;
    private List<ReceitasFavoritas> receitas = new ArrayList<>();
    private ControleBD crud;
    private Cursor cursor;
    private Button mVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoritos);

        mVoltar = findViewById(R.id.b_voltar_fav);

        recyclerView = findViewById(R.id.recycler_view_favoritos);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        separaDados();
        FavoritasAdapter adapter = new FavoritasAdapter(this, receitas);
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

    /*
            ""+CriarBD.ID_RECEITA,
            ""+CriarBD.FOTO,
            ""+CriarBD.INGREDIENTES,
            ""+CriarBD.RATING,
            ""+CriarBD.NOME_RECEITA,
            ""+CriarBD.SOURCE_DISPLAY_NAME,
            ""+CriarBD.TOTAL_TIME_IN_SECONDS
    */

    public void separaDados(){
        crud = new ControleBD(getBaseContext());
        receitas = crud.readAll();
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onImageClicked(int position) {

    }
}
