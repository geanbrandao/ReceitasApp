package com.geanbrandao.gean.reiceitasapp.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.geanbrandao.gean.reiceitasapp.R;
import com.geanbrandao.gean.reiceitasapp.adapter.FavoritasAdapter;
import com.geanbrandao.gean.reiceitasapp.helper.ReceitasFavoritas;

public class DetalhesFavoritasActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView mDetNome, mDetCategoria, mDetIngredientes;
    private Button mVoltar, mFavorito;
    private ReceitasFavoritas favoritas;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_offline);

        imageView = findViewById(R.id.iv_det_receita_off);
        mDetCategoria = findViewById(R.id.tv_det_categorias_off);
        mDetNome = findViewById(R.id.tv_det_nome_off);
        mDetIngredientes = findViewById(R.id.tv_ingredientes_off);
        mVoltar = findViewById(R.id.b_voltar_fav_off);

        // pega as informacoes da activity anterior
        bundle = getIntent().getExtras();
        if (bundle != null) {
            mDetNome.setText(bundle.getString("nome"));
            mDetCategoria.setText(bundle.getString("nomeFonte"));
            mDetIngredientes.setText(bundle.getString("ingredientes"));
        }



        mVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void pegarImgReceita(byte[] foto) {
        if(foto!=null){
            Bitmap raw  = BitmapFactory.decodeByteArray(foto,0,foto.length);
            imageView.setImageBitmap(raw);
        }
    }

    @Override
    public void finish() {
        super.finish();
    }
}
