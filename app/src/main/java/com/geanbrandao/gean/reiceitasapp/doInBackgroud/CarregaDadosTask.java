package com.geanbrandao.gean.reiceitasapp.doInBackgroud;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;

public class CarregaDadosTask extends AsyncTask<URLConnection, Void, InputStream> {
    @Override
    protected InputStream doInBackground(URLConnection... urlConnections) {
        try {
            return urlConnections[0].getInputStream();
        } catch (IOException e) {
            Log.i("RetornoApi", "CarregaDadosTask não foi concluido "+e);
        }
        return null;
    }
}
