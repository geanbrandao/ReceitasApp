package com.geanbrandao.gean.reiceitasapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class DetalhesActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView mDetNome, mDetCategoria, mDetIngredientes;
    private Button mVoltar, mFavorito;
    private Button mVisitarSite;
    private ReceitaDetalhes detalhes;
    /*
    lembrar de modificar a url da imagem para alterar o tamanho dela
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);

        imageView = findViewById(R.id.iv_det_receita);
        mDetNome = findViewById(R.id.tv_det_nome);
        mDetCategoria = findViewById(R.id.tv_det_categorias);
        mDetIngredientes = findViewById(R.id.tv_ingredientes);
        mVoltar = findViewById(R.id.ib_voltar);
        mVisitarSite = findViewById(R.id.b_acessar_site);
        mFavorito = findViewById(R.id.ib_favorito);

        Yummly y = new Yummly(getResources().getString(R.string.appId),
                getResources().getString(R.string.appKey));

        detalhes = new ReceitaDetalhes();



        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            mDetNome.setText(bundle.getString("recipeName"));
            mDetCategoria.setText(bundle.getString("sourceDisplayName"));
            StringBuilder builder = new StringBuilder();
            for (int aux = 0; aux < bundle.getInt("quantidadeIngredientes"); aux++) {
                builder.append("- ");
                builder.append(bundle.getString("ing"+aux));
                if(aux < bundle.getInt("quantidadeIngredientes") - 1 ) {
                    builder.append("\n");
                }

            }
            mDetIngredientes.setText(builder);
            Glide.with(this).load(bundle.getString("smallImageUrls"))
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageView);
        }
        try {
            detalhes = y.getReceitaDetalhes(bundle.getString("id"));

        } catch (Exception e) {
            Log.i("RetornoApi", "Erro Detalhes "+e);
            e.printStackTrace();
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
                if(detalhes.getSource().getSourceSiteUrl() != null) {
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(detalhes.getSource().getSourceRecipeUrl()));
                    startActivity(i);
                }
            }
        });

        mFavorito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mFavorito.getBackground() == getDrawable(R.drawable.ic_action_star_border_black)) {
                    Log.i("Botao", "Add como favorito");
                    //mFavorito.setImageDrawable(getDrawable(R.drawable.ic_action_star_yellow));
                } else {
                    Log.i("Botao", "Não é mais favorito");

                    //mFavorito.setImageDrawable(getDrawable(R.drawable.ic_action_star_border_black));
                }
            }
        });
    }

    @Override
    public void finish() {
        super.finish();
    }
}
