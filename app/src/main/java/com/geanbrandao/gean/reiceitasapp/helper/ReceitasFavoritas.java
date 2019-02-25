package com.geanbrandao.gean.reiceitasapp.helper;

public class ReceitasFavoritas {

    private String id;
    private byte[] foto;
    private String ingredientes;
    private int rating;
    private String nome;
    private String nomeFonte;
    private String porcao;
    private String preparo;
    private String tempo;
    private String site;

    public ReceitasFavoritas() {

    }

    public ReceitasFavoritas(String id, byte[] foto, String ingredientes, int rating, String nome, String nomeFonte, String porcao, String preparo, String tempo, String site) {
        this.id = id;
        this.foto = foto;
        this.ingredientes = ingredientes;
        this.rating = rating;
        this.nome = nome;
        this.nomeFonte = nomeFonte;
        this.porcao = porcao;
        this.preparo = preparo;
        this.tempo = tempo;
        this.site = site;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getPorcao() {
        return porcao;
    }

    public void setPorcao(String porcao) {
        this.porcao = porcao;
    }

    public String getPreparo() {
        return preparo;
    }

    public void setPreparo(String preparo) {
        this.preparo = preparo;
    }

    public String getTempo() {
        return tempo;
    }

    public void setTempo(String tempo) {
        this.tempo = tempo;
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

}
