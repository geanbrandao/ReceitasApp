
package com.geanbrandao.gean.reiceitasapp;

import android.os.AsyncTask;
import android.util.Log;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.ExecutionException;

import static com.geanbrandao.gean.reiceitasapp.ValoresEstaticos.mapper;


/**
 * Main class for interacting with the Yummly api.
 */
public class Yummly {

    private static final String URL_INICIAL = "http://api.yummly.com/v1/api/";

    private String mAppId;
    private String mAppKey;

    private InputStream in;
    private SearchResult result;
    private ReceitaDetalhes resultDetalhes;

    public Yummly(String appId, String appKey) {
        mAppId = appId;
        mAppKey = appKey;
    }

    public SearchResult search() throws IOException {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("recipes");
        stringBuilder.append("?");
        stringBuilder.append("_app_id=");
        stringBuilder.append(mAppId);
        stringBuilder.append("&");
        stringBuilder.append("_app_key=");
        stringBuilder.append(mAppKey);
        in = performRequest(stringBuilder);

        // Parse json.
        JsonFactory factory = new JsonFactory();
        mapper = new ObjectMapper(factory);


        try {
            result = new LeValoresTask().execute(in).get();
        } catch (ExecutionException e) {
            Log.i("RetornoApi", "LeValoresTask Erro: "+e);
        } catch (InterruptedException e) {
            Log.i("RetornoApi", "LeValoresTask Erro: "+e);
        }
        Log.i("RetornoApi", ""+result.getMatches().get(0).getSourceDisplayName());
        in.close();
        return result;
    }

	public ReceitaDetalhes getReceitaDetalhes(String recipeId) throws IOException, ExecutionException, InterruptedException {

		StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("recipe");
        stringBuilder.append("/");
        stringBuilder.append(recipeId);
        stringBuilder.append("?");
        stringBuilder.append("_app_id=");
        stringBuilder.append(mAppId);
        stringBuilder.append("&");
        stringBuilder.append("_app_key=");
        stringBuilder.append(mAppKey);
		in = performRequest(stringBuilder);
		// Parse json.
		JsonFactory factory = new JsonFactory();
		ObjectMapper mapper = new ObjectMapper(factory);


        try {
            resultDetalhes = new LeValoresTaskDetalhes().execute(in).get();
        } catch (ExecutionException e) {
            Log.i("RetornoApi", "LeValoresTask Erro: "+e);
        } catch (InterruptedException e) {
            Log.i("RetornoApi", "LeValoresTask Erro: "+e);
        }
        //Log.i("RetornoApi", "LeValoresTask: "+resultDetalhes.getName());
		in.close();
		return resultDetalhes;
	}


    private InputStream performRequest(StringBuilder stringBuilder) throws IOException {

        URL endpoint = new URL(URL_INICIAL + stringBuilder);
        URLConnection urlCon = endpoint.openConnection();
        Log.i("RetornoApi", "link? "+endpoint);

        try {
            Log.i("RetornoApi", "UrlCon "+urlCon);
            return new CarregaDadosTask().execute(urlCon).get();
        } catch (ExecutionException e) {
            Log.i("RetornoApi", "CarregaDadosTask Erro: "+e);
        } catch (InterruptedException e) {
            Log.i("RetornoApi", "CarregaDadosTask Erro: "+e);
        }
        return null;
    }

}
