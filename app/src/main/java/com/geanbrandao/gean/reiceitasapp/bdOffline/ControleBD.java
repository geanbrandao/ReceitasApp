package com.geanbrandao.gean.reiceitasapp.bdOffline;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.geanbrandao.gean.reiceitasapp.helper.ReceitasFavoritas;

import java.util.ArrayList;
import java.util.List;

public class ControleBD {

    private SQLiteDatabase db;
    private CriarBD banco;

    public ControleBD(Context context){
        banco = new CriarBD(context);
    }

    public long insert(String idReceita, String ingredientes, String nomeReceita, String sourceDN, int rating, byte[] foto, String porcao, String preparo, String tempo, String site) {
        ContentValues values = new ContentValues();
        long resultado;

        db = banco.getWritableDatabase();
         // seta os valores a serem inseridos
        values.put(CriarBD.ID_RECEITA, idReceita);
        values.put(CriarBD.INGREDIENTES, ingredientes);
        values.put(CriarBD.NOME_RECEITA, nomeReceita);
        values.put(CriarBD.SOURCE_DISPLAY_NAME, sourceDN);
        values.put(CriarBD.RATING, rating);
        values.put(CriarBD.FOTO, foto);
        values.put(CriarBD.PORCAO, porcao);
        values.put(CriarBD.PREPARO, preparo);
        values.put(CriarBD.TEMPO_STRING, tempo);
        values.put(CriarBD.SITE, site);
        resultado = db.insert(CriarBD.TABELA_RECEITAS,null, values);
        db.close();
        return resultado;
    }

    public Cursor read(String id) {
        Cursor cursor;
        db = banco.getReadableDatabase();
        String[] colunas = new String[]{
                CriarBD.ID_RECEITA,
                CriarBD.SOURCE_DISPLAY_NAME,
                CriarBD.NOME_RECEITA,
                CriarBD.INGREDIENTES,
                CriarBD.FOTO,
                CriarBD.PORCAO,
                CriarBD.PREPARO,
                CriarBD.RATING,
                CriarBD.TEMPO_STRING,
                CriarBD.SITE};
        cursor = db.query(CriarBD.TABELA_RECEITAS, colunas, ""+CriarBD.ID_RECEITA+"= ?", new String[]{id}, null, null, null, null );
        if(cursor != null) {
            cursor.moveToFirst();
            Log.i("Database", "Retorno read, cursor nao nulo");
        }
        db.close();
        return cursor;
    }

    public List<String> readIDs(){
        Cursor cursor;
        db = banco.getReadableDatabase();
        cursor = db.query(CriarBD.TABELA_RECEITAS, new String[]{""+CriarBD.ID_RECEITA}, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            Log.i("Database", "Retorno read, cursor nao nulo");
        }
        List<String> ids = new ArrayList<>();
        for (int i = 0; i < cursor.getCount(); ++i) {
            ids.add(cursor.getString(0));
            cursor.moveToNext();
        }
        db.close();
        return ids;
    }

    public long deletaReceita(String id){
        StringBuilder builder = new StringBuilder();
        builder.append(CriarBD.ID_RECEITA);
        builder.append("=");
        builder.append("'"+id+"'");
        db = banco.getReadableDatabase();
        long result = db.delete(CriarBD.TABELA_RECEITAS,builder.toString(),null);
        db.close();
        return result;
    }

}
