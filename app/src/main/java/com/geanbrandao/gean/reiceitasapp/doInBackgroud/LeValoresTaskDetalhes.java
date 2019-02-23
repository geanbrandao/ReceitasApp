package com.geanbrandao.gean.reiceitasapp.doInBackgroud;

import android.os.AsyncTask;
import android.util.Log;

import com.geanbrandao.gean.reiceitasapp.json.ReceitaDetalhes;
import com.geanbrandao.gean.reiceitasapp.helper.ValoresEstaticos;

import java.io.IOException;
import java.io.InputStream;

public class LeValoresTaskDetalhes extends AsyncTask<InputStream, Void, ReceitaDetalhes> {
    @Override
    protected ReceitaDetalhes doInBackground(InputStream... inputStreams) {
        try {
            return ValoresEstaticos.mapper.readValue(inputStreams[0], ReceitaDetalhes.class);
        } catch (IOException e) {
            Log.i("RetornoApi", "LeValoresTaskDetalhes não foi concluido "+e);
        }
        return null;
    }
}
