package com.geanbrandao.gean.reiceitasapp.bdOffline;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class CriarBD extends SQLiteOpenHelper {

    protected static final String NOME_BANCO = "bd.favoritos";
    // TABELA RECEITAS
    protected static final String TABELA_RECEITAS = "receitas";
    protected static final String ID = "id";
    protected static final String ID_RECEITA = "id_receita";
    protected static final String INGREDIENTES = "ingredients";
    protected static final String PREPARO = "preparo";
    protected static final String TEMPO_STRING = "tempoString";
    protected static final String PORCAO = "porcao";
    protected static final String SOURCE_DISPLAY_NAME = "sourceDisplayName";
    protected static final String NOME_RECEITA = "recipeName";
    protected static final String RATING = "rating";
    protected static final String FOTO = "foto";
    protected static final String SITE = "site";

    protected static final int VERSAO = 1;

    public CriarBD(Context context){
        super(context, NOME_BANCO,null,VERSAO);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        StringBuilder sql = new StringBuilder();
        sql.append("CREATE TABLE ");
        sql.append(TABELA_RECEITAS);
        sql.append(" (");
        sql.append(ID);
        sql.append(" integer primary key autoincrement,");
        sql.append(ID_RECEITA);
        sql.append(" text,");
        sql.append(INGREDIENTES);
        sql.append(" text,");
        sql.append(PREPARO);
        sql.append(" text,");
        sql.append(TEMPO_STRING);
        sql.append(" text,");
        sql.append(PORCAO);
        sql.append(" text,");
        sql.append(SOURCE_DISPLAY_NAME);
        sql.append(" text,");
        sql.append(NOME_RECEITA);
        sql.append(" text,");
        sql.append(RATING);
        sql.append(" integer,");
        sql.append(FOTO);
        sql.append(" blob,");
        sql.append(SITE);
        sql.append(" text)");

//        String sql = "CREATE TABLE "+TABELA_RECEITAS+" ("
//                + ID + " integer primary key autoincrement,"
//                + ID_RECEITA + " text,"
//                + INGREDIENTES + " text,"
//                + SOURCE_DISPLAY_NAME + " text,"
//                + NOME_RECEITA + " text,"
//                + TOTAL_TIME_IN_SECONDS + " integer,"
//                + RATING + " integer"
//                + ")";
        try {
            db.execSQL(sql.toString()); // cria a tabela
        } catch (Exception e) {
            Log.i("Database", "Falha ao criar o banco de dados"+e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        StringBuilder sql = new StringBuilder();
        sql.append("DROP TABLE IF EXISTS");
        sql.append(TABELA_RECEITAS);

        db.execSQL(sql.toString());
        onCreate(db);
    }
}
