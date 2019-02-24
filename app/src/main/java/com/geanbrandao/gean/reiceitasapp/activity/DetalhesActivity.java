package com.geanbrandao.gean.reiceitasapp.activity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.geanbrandao.gean.reiceitasapp.R;
import com.geanbrandao.gean.reiceitasapp.bdOffline.ControleBD;
import com.geanbrandao.gean.reiceitasapp.conexao.Yummly;
import com.geanbrandao.gean.reiceitasapp.helper.MelhoraImagem;
import com.geanbrandao.gean.reiceitasapp.json.ReceitaDetalhes;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class DetalhesActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView mDetNome, mDetCategoria, mDetIngredientes, mDetModoPreparo, mTempoTotal, mPorcoes ;
    private Button mVoltar, mFavorito;
    private Button mVisitarSite;
    private ReceitaDetalhes detalhes;
    private ControleBD crud;
    private Cursor cursor;
    private String idReceitaAtual;
    private String listaIngredientesFormatada;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);

        imageView = findViewById(R.id.iv_det_receita_off);
        mDetNome = findViewById(R.id.tv_det_nome_off);
        mDetCategoria = findViewById(R.id.tv_det_categorias_off);
        mDetIngredientes = findViewById(R.id.tv_ingredientes);
        mVoltar = findViewById(R.id.b_voltar_fav_off);
        mVisitarSite = findViewById(R.id.b_acessar_site);
        mFavorito = findViewById(R.id.ib_favorito);
        mDetModoPreparo = findViewById(R.id.tv_modo_preparo);
        mPorcoes = findViewById(R.id.tv_porcoes);
        mTempoTotal = findViewById(R.id.tv_tempo_total);


        Yummly y = new Yummly(getResources().getString(R.string.appId),
                getResources().getString(R.string.appKey));

        detalhes = new ReceitaDetalhes();


        // pega as informacoes da activity anterior
        bundle = getIntent().getExtras();
        if (bundle != null) {

            idReceitaAtual = bundle.getString("id");
            mDetNome.setText(bundle.getString("recipeName"));
            mDetCategoria.setText(bundle.getString("sourceDisplayName"));
            StringBuilder builder = new StringBuilder();
            for (int aux = 0; aux < bundle.getInt("quantidadeIngredientes"); aux++) {
                builder.append("- ");
                builder.append(bundle.getString("ing" + aux));
                if (aux < bundle.getInt("quantidadeIngredientes") - 1) {
                    builder.append("\n");
                }
            }
            mDetIngredientes.setText(builder);
            listaIngredientesFormatada = builder.toString();
            Glide.with(this).load(bundle.getString("smallImageUrls"))
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageView);
        }
//        Log.i("bundle", "limpou o bundle detalhes");
//        bundle.clear();


        try {
            detalhes = y.getReceitaDetalhes(bundle.getString("id"));
            mTempoTotal.setText(detalhes.getTotalTime());
            mPorcoes.setText(detalhes.getYield());
            mDetModoPreparo.setText(formataTextoModoPreparo(detalhes.getIngredientLines()));

        } catch (Exception e) {
            Log.i("RetornoApi", "Erro Detalhes " + e);
            e.printStackTrace();
        }

        // verifica se ja eh favorito
        crud = new ControleBD(getBaseContext());
        cursor = crud.read(idReceitaAtual);
        if (cursor.getCount() > 0) {
            // vai marcar a receita como favorita
            Log.i("Database", "Achou o id no database");
            mFavorito.setBackground(getDrawable(R.drawable.ic_action_star_yellow));
        }

        mVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mVisitarSite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (detalhes.getSource().getSourceSiteUrl() != null) {
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(detalhes.getSource().getSourceRecipeUrl()));
                    startActivity(i);
                }
            }
        });

        mFavorito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //mFavorito.setBackground(getDrawable(R.drawable.ic_action_star_yellow));
                cursor = crud.read(idReceitaAtual);
                if (cursor.getCount() < 1) { // se não estiver no bd marca como favorito
                    // insere no bd
                    Drawable drawable = imageView.getDrawable();
                    Bitmap bitmap =  MelhoraImagem.drawableToBitmap(drawable);
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    byte[] byteArray = stream.toByteArray();
                    long resultado = crud.insert(idReceitaAtual,
                            listaIngredientesFormatada,
                            bundle.getString("recipeName"),
                            bundle.getString("sourceDisplayName"),
                            bundle.getInt("rating"),
                            bundle.getInt("totalTimeInSeconds"),
                            byteArray);
                    if (resultado == -1) {
                        Log.i("Database", "Falha ao inserir favorito");
                        Toast.makeText(DetalhesActivity.this, "Não foi possivel marcar como favorito", Toast.LENGTH_SHORT).show();
                        mFavorito.setBackground(getDrawable(R.drawable.ic_action_star_border_black));
                    } else {
                        Log.i("Database", "favorito marcado");
                        mFavorito.setBackground(getDrawable(R.drawable.ic_action_star_yellow));
                    }
                } else {
                    mFavorito.setBackground(getDrawable(R.drawable.ic_action_star_border_black));
                    Log.i("Database", "Recipe deixou de ser favorita");

                    long result = crud.deletaReceita(idReceitaAtual);
                    if(result == -1) {
                        Log.i("Database", "Não conseguiu deletar");
                    } else {
                        Log.i("Database", "dado deletado");
                    }
                    // deleta
                }

            }
        });
    }

    public String formataTextoModoPreparo(List<String> list) {

        StringBuilder builder = new StringBuilder();
        int i = 0;
        for(String s: list) {
            builder.append("- ");
            builder.append(s);
            if (i < list.size()-1) {
                builder.append("\n");
            }
            ++i;
        }

        return builder.toString();
    }

    @Override
    public void finish() {
        super.finish();
    }
}
