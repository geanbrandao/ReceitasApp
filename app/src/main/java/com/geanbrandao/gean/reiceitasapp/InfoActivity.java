package com.geanbrandao.gean.reiceitasapp;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.geanbrandao.gean.reiceitasapp.activity.DetalhesFavoritasActivity;
import com.geanbrandao.gean.reiceitasapp.helper.ValoresEstaticos;

public class InfoActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView mText;
    private Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        imageView = findViewById(R.id.iv_logo_yummly);
        mText = findViewById(R.id.tv_atribuicao);
        button = findViewById(R.id.b_acessar_site2);

        mText.setText(ValoresEstaticos.attribution.getText());
        Glide.with(this)
                .load(Uri.parse(ValoresEstaticos.attribution.getUrl()))
                .diskCacheStrategy(DiskCacheStrategy.ALL).
                into(imageView);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConnectivityManager conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo netInfo = conMgr.getActiveNetworkInfo();
                if (netInfo == null) {

                    new AlertDialog.Builder(InfoActivity.this)
                            .setTitle(getResources().getString(R.string.app_name))
                            .setMessage(getResources().getString(R.string.s_sem_conexao))
                            .setPositiveButton("OK", null).show();
                } else {
                    if (ValoresEstaticos.attribution.getUrl() != null) {
                        Log.i("Site", " site "+ValoresEstaticos.attribution.getUrl());
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(ValoresEstaticos.attribution.getUrl()));
                        startActivity(i);
                    } else {
                        Log.i("Site", " site "+ValoresEstaticos.attribution.getUrl());
                    }
                }
            }
        });


    }
}
