package com.geanbrandao.gean.reiceitasapp;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;

public class LeValoresTask extends AsyncTask<InputStream, Void, SearchResult> {
    @Override
    protected SearchResult doInBackground(InputStream... inputStreams) {
        try {
            return ValoresEstaticos.mapper.readValue(inputStreams[0], SearchResult.class);
        } catch (IOException e) {
            Log.i("RetornoApi", "LeValoresTask não foi concluido "+e);
        }
        return null;
    }

}
