package com.geanbrandao.gean.reiceitasapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class ControleBD {

    private SQLiteDatabase db;
    private CriarBD banco;

    public ControleBD(Context context){
        banco = new CriarBD(context);
    }

    public long insert(Recipe receita) {
        ContentValues values = new ContentValues();
        long resultado;

        db = banco.getWritableDatabase();
         // seta os valores a serem inseridos
        values.put(CriarBD.ID_RECEITA, receita.getId());
        int aux = 0;
        StringBuilder builder = new StringBuilder();
        for (String s: receita.getIngredients()) {
            builder.append("- ");
            builder.append(s);
            if(aux < receita.getIngredients().size() - 1 ) {
                builder.append("\n");
            }
            ++aux;
        }
        values.put(CriarBD.INGREDIENTES, builder.toString());
        values.put(CriarBD.NOME_RECEITA, receita.getRecipeName());
        values.put(CriarBD.SOURCE_DISPLAY_NAME, receita.getSourceDisplayName());
        values.put(CriarBD.RATING, receita.getRating());
        values.put(CriarBD.TOTAL_TIME_IN_SECONDS, receita.getTotalTimeInSeconds());

        resultado = db.insert(CriarBD.TABELA_RECEITAS,null, values);
        db.close();
        return resultado;
    }

}
