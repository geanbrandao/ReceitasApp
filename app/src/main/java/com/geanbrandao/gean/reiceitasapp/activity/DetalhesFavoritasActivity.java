package com.geanbrandao.gean.reiceitasapp.activity;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.geanbrandao.gean.reiceitasapp.R;
import com.geanbrandao.gean.reiceitasapp.bdOffline.ControleBD;
import com.geanbrandao.gean.reiceitasapp.helper.ReceitasFavoritas;

public class DetalhesFavoritasActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView mDetNome, mDetCategoria, mDetIngredientes, mModoPreparo, mPorcao, mTempoTotal;
    private Button mVoltar, mFavorito, mSite;
    private ReceitasFavoritas favoritas;
    private Bundle bundle;
    private String idReceitaFavorita;
    private ControleBD crud;
    private boolean ehFavorito = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_favoritas);
        favoritas = new ReceitasFavoritas();
        imageView = findViewById(R.id.iv_det_receita_off);
        mDetCategoria = findViewById(R.id.tv_det_categorias_off);
        mDetNome = findViewById(R.id.tv_det_nome_off);
        mDetIngredientes = findViewById(R.id.tv_ingredientes_off);
        mModoPreparo = findViewById(R.id.tv_modo_preparo_off);
        mPorcao = findViewById(R.id.tv_porcoes_off);
        mTempoTotal = findViewById(R.id.tv_tempo_total_off);
        mVoltar = findViewById(R.id.b_voltar_fav_off);
        mSite = findViewById(R.id.b_acessar_site_off);
        mFavorito = findViewById(R.id.ib_favorito_off);

        // pega as informacoes da activity anterior
        bundle = getIntent().getExtras();
        if (bundle != null) {
           idReceitaFavorita = bundle.getString("id");
        }
        // carrega dados
        crud = new ControleBD(getBaseContext());
        Cursor cursor = crud.read(idReceitaFavorita);
        cursor.moveToFirst();
        favoritas.setId(cursor.getString(0));
        favoritas.setNomeFonte(cursor.getString(1));
        favoritas.setNome(cursor.getString(2));
        favoritas.setIngredientes(cursor.getString(3));
        favoritas.setFoto(cursor.getBlob(4));
        favoritas.setPorcao(cursor.getString(5));
        favoritas.setPreparo(cursor.getString(6));
        favoritas.setRating(cursor.getInt(7));
        favoritas.setTempo(cursor.getString(8));

        mDetCategoria.setText(favoritas.getNomeFonte());
        mDetNome.setText(favoritas.getNome());
        mDetIngredientes.setText(favoritas.getIngredientes());
        mPorcao.setText(favoritas.getPorcao());
        mTempoTotal.setText(favoritas.getTempo());
        mModoPreparo.setText(favoritas.getPreparo());
        pegarImgReceita(favoritas.getFoto());

        mVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mFavorito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ehFavorito) {
                    mFavorito.setBackground(getDrawable(R.drawable.ic_action_star_border_black));
                    ehFavorito = false;
                } else {
                    mFavorito.setBackground(getDrawable(R.drawable.ic_action_star_yellow));
                    ehFavorito = true;
                }
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
        if(!ehFavorito){
            crud.deletaReceita(idReceitaFavorita);
        }
        super.finish();

    }
}
