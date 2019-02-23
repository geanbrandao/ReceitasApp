
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

    public Yummly(String appId, String appKey) {
        mAppId = appId;
        mAppKey = appKey;
    }

    public SearchResult search() throws IOException {

        // Perform search request.
        in = performRequest();
        // Parse json.
        //Log.i("RetornoApi", "Erro7");
        JsonFactory factory = new JsonFactory();
        //Log.i("RetornoApi", "Erro8");
        mapper = new ObjectMapper(factory);
        //Log.i("RetornoApi", "Erro9");

        try {
            result = new LeValoresTask().execute(in).get();
        } catch (ExecutionException e) {
            Log.i("RetornoApi", "LeValoresTask Erro: "+e);
        } catch (InterruptedException e) {
            Log.i("RetornoApi", "LeValoresTask Erro: "+e);
        }
//        mapper.readValue(in, SearchResult.class);
        Log.i("RetornoApi", ""+result.getMatches().get(0).getSourceDisplayName());
        in.close();
        return result;
    }

//	public Recipe getRecipe(String recipeId) throws IOException {
//
//		// Perform recipe request.
//		InputStream in = performRequest(
//				String.format("recipe/%s", URLEncoder.encode(recipeId, "utf8")),
//				null);
//		// Parse json.
//		JsonFactory factory = new JsonFactory();
//		ObjectMapper mapper = new ObjectMapper(factory);
//
//		Recipe result = mapper.readValue(in, Recipe.class);
//
//		in.close();
//
//		return result;
//	}


    private InputStream performRequest() throws IOException {
        //Log.i("RetornoApi", "Erro1 ");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("recipes");
        stringBuilder.append("?");
        stringBuilder.append("_app_id=");
        stringBuilder.append(mAppId);
        stringBuilder.append("&");
        stringBuilder.append("_app_key=");
        stringBuilder.append(mAppKey);


        URL endpoint = new URL(URL_INICIAL + stringBuilder);
        //Log.i("RetornoApi", "Erro2"+endpoint.toString());
        URLConnection urlCon = endpoint.openConnection();
        Log.i("RetornoApi", "link? "+endpoint);
        //Log.i("RetornoApi", "Erro3");
        // Add header fields.
        //urlCon.setRequestProperty("X-Yummly-App-ID", mAppId);
        //Log.i("RetornoApi", "Erro4");
        //urlCon.setRequestProperty("X-Yummly-App-Key", mAppKey);
        //Log.i("RetornoApi", "Erro5");
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
