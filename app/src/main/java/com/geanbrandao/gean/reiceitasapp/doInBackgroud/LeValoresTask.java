package com.geanbrandao.gean.reiceitasapp.doInBackgroud;

import android.os.AsyncTask;
import android.util.Log;

import com.geanbrandao.gean.reiceitasapp.json.ResultadoFeed;
import com.geanbrandao.gean.reiceitasapp.helper.ValoresEstaticos;

import java.io.IOException;
import java.io.InputStream;

public class LeValoresTask extends AsyncTask<InputStream, Void, ResultadoFeed> {
    @Override
    protected ResultadoFeed doInBackground(InputStream... inputStreams) {
        try {
            return ValoresEstaticos.mapper.readValue(inputStreams[0], ResultadoFeed.class);
        } catch (IOException e) {
            Log.i("RetornoApi", "LeValoresTask não foi concluido "+e);
        }
        return null;
    }

}
