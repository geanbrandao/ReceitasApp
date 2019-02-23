package com.geanbrandao.gean.reiceitasapp.bdOffline;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

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

//        int aux = 0;
//        StringBuilder builder = new StringBuilder();
//        for (String s: receita.getIngredients()) {
//            builder.append("- ");
//            builder.append(s);
//            if(aux < receita.getIngredients().size() - 1 ) {
//                builder.append("\n");
//            }
//            ++aux;
//        }

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

    public long deletaReceita(String id){
        StringBuilder builder = new StringBuilder();
        builder.append(CriarBD.ID_RECEITA);
        builder.append("=");
        builder.append("'"+id+"'");
        //String where = CriarBD.ID_RECEITA + "=" ;
        db = banco.getReadableDatabase();
        long result = db.delete(CriarBD.TABELA_RECEITAS,builder.toString(),null);
        db.close();
        return result;
    }

}
