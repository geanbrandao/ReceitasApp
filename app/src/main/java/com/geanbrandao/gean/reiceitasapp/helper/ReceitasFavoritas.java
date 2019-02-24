package com.geanbrandao.gean.reiceitasapp.helper;

public class ReceitasFavoritas {
    /*
            ""+CriarBD.ID_RECEITA,
            ""+CriarBD.FOTO,
            ""+CriarBD.INGREDIENTES,
            ""+CriarBD.RATING,
            ""+CriarBD.NOME_RECEITA,
            ""+CriarBD.SOURCE_DISPLAY_NAME,
            ""+CriarBD.TOTAL_TIME_IN_SECONDS
    */
    private String id;
    private byte[] foto;
    private String ingredientes;
    private int rating;
    private String nome;
    private String nomeFonte;
    private int TotalTimeSeg;

    public ReceitasFavoritas() {

    }

    public ReceitasFavoritas(String id, byte[] foto, String ingredientes, int rating, String nome, String nomeFonte, int totalTimeSeg) {
        this.id = id;
        this.foto = foto;
        this.ingredientes = ingredientes;
        this.rating = rating;
        this.nome = nome;
        this.nomeFonte = nomeFonte;
        TotalTimeSeg = totalTimeSeg;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public String getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeFonte() {
        return nomeFonte;
    }

    public void setNomeFonte(String nomeFonte) {
        this.nomeFonte = nomeFonte;
    }

    public int getTotalTimeSeg() {
        return TotalTimeSeg;
    }

    public void setTotalTimeSeg(int totalTimeSeg) {
        TotalTimeSeg = totalTimeSeg;
    }
}
