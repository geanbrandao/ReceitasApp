package com.geanbrandao.gean.reiceitasapp;

public class Receita {

    private int rating;
    private String id;
    private String smallImageUrls; // lista
    private String sourceDisplayName;
    private int totalTimeInSeconds;
    private String ingredients;
    private String recipeName;

    public Receita() {

    }

    public Receita(int rating, String id, String smallImageUrls, String sourceDisplayName, int totalTimeInSeconds, String ingredients, String recipeName) {
        this.rating = rating;
        this.id = id;
        this.smallImageUrls = smallImageUrls;
        this.sourceDisplayName = sourceDisplayName;
        this.totalTimeInSeconds = totalTimeInSeconds;
        this.ingredients = ingredients;
        this.recipeName = recipeName;
    }

    public String getSmallImageUrls() {
        return smallImageUrls;
    }

    public void setSmallImageUrls(String smallImageUrls) {
        this.smallImageUrls = smallImageUrls;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSourceDisplayName() {
        return sourceDisplayName;
    }

    public void setSourceDisplayName(String sourceDisplayName) {
        this.sourceDisplayName = sourceDisplayName;
    }

    public int getTotalTimeInSeconds() {
        return totalTimeInSeconds;
    }

    public void setTotalTimeInSeconds(int totalTimeInSeconds) {
        this.totalTimeInSeconds = totalTimeInSeconds;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }
}
