package com.geanbrandao.gean.reiceitasapp.helper;


public class Usuario {

    private String nome, email, urlPicture, id;

    public Usuario() {

    }

    public Usuario(String nome, String email, String urlPicture, String id) {
        this.nome = nome;
        this.email = email;
        this.urlPicture = urlPicture;
        this.id = id;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUrlPicture() {
        return urlPicture;
    }

    public void setUrlPicture(String urlPicture) {
        this.urlPicture = urlPicture;
    }

}
