package com.geanbrandao.gean.reiceitasapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class DetalhesActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView mDetNome, mDetCategoria, mDetIngredientes;
    private ImageButton mVoltar;
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
}
