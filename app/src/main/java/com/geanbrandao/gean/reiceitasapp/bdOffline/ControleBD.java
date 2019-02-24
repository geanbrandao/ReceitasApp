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

    public long insert(String idReceita, String ingredientes, String nomeReceita, String sourceDN, int rating, int totalTimeSeg, byte[] foto) {
        ContentValues values = new ContentValues();
        long resultado;

        db = banco.getWritableDatabase();
         // seta os valores a serem inseridos
        values.put(CriarBD.ID_RECEITA, idReceita);
        values.put(CriarBD.INGREDIENTES, ingredientes);
        values.put(CriarBD.NOME_RECEITA, nomeReceita);
        values.put(CriarBD.SOURCE_DISPLAY_NAME, sourceDN);
        values.put(CriarBD.RATING, rating);
        values.put(CriarBD.TOTAL_TIME_IN_SECONDS, totalTimeSeg);
        values.put(CriarBD.FOTO, foto);
        resultado = db.insert(CriarBD.TABELA_RECEITAS,null, values);
        db.close();
        return resultado;
    }

    public Cursor read(String id) {
        Cursor cursor;
        db = banco.getReadableDatabase();
        cursor = db.query(CriarBD.TABELA_RECEITAS, new String[]{""+CriarBD.ID_RECEITA}, ""+CriarBD.ID_RECEITA+"= ?", new String[]{id}, null, null, null, null );
        if(cursor != null) {
            cursor.moveToFirst();
            Log.i("Database", "Retorno read, cursor nao nulo");
        }
        db.close();
        return cursor;
    }


    public List<ReceitasFavoritas> readAll() {
        Cursor cursor;
        db = banco.getReadableDatabase();
        List<ReceitasFavoritas> favoritas = new ArrayList<>();
        String[] colunas = new String[]{
                ""+CriarBD.ID_RECEITA,
                ""+CriarBD.FOTO,
                ""+CriarBD.INGREDIENTES,
                ""+CriarBD.RATING,
                ""+CriarBD.NOME_RECEITA,
                ""+CriarBD.SOURCE_DISPLAY_NAME,
                ""+CriarBD.TOTAL_TIME_IN_SECONDS
        };

        cursor = db.query(CriarBD.TABELA_RECEITAS, colunas, null, null, null, null, null, null );
        if(cursor.getCount() > 0) {
            cursor.moveToFirst();
            Log.i("Database", "Retorno read, linhas maior que zero "+cursor.getCount());
        }
        int i =0;
        while (i < cursor.getCount()) {
            ReceitasFavoritas fav = new ReceitasFavoritas();
            fav.setId(cursor.getString(0));
            Log.i("Favoritas", "linha "+cursor.getString(0));
            fav.setFoto(cursor.getBlob(1));
            Log.i("Favoritas", "linha foto" );
            fav.setIngredientes(cursor.getString(2));
            Log.i("Favoritas", "linha "+cursor.getString(2));
            fav.setRating(cursor.getInt(3));
            Log.i("Favoritas", "linha "+cursor.getInt(3));
            fav.setNome(cursor.getString(i+4));
            Log.i("Favoritas", "linha "+cursor.getString(4));
            fav.setNomeFonte(cursor.getString(5));
            Log.i("Favoritas", "linha "+cursor.getString(5));
            fav.setTotalTimeSeg(cursor.getInt(6));
            Log.i("Favoritas", "linha "+cursor.getInt(6));
            i++;
            Log.i("Favoritas", "Fim da receita ");
            cursor.moveToNext();
            favoritas.add(fav);
        }
        db.close();
        return favoritas;
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
