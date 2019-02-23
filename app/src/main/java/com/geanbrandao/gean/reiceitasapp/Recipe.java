package com.geanbrandao.gean.reiceitasapp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Recipe {

    private List<String> smallImageUrls;
    private String sourceDisplayName;
    private List<String> ingredients;
    private String id;
    private String recipeName;
    private int totalTimeInSeconds;
    private Number rating;

    public Recipe() {
    }

    public Recipe(List<String> smallImageUrls, String sourceDisplayName, List<String> ingredients, String id, String recipeName, int totalTimeInSeconds, Number rating) {
        this.smallImageUrls = smallImageUrls;
        this.sourceDisplayName = sourceDisplayName;
        this.ingredients = ingredients;
        this.id = id;
        this.recipeName = recipeName;
        this.totalTimeInSeconds = totalTimeInSeconds;
        this.rating = rating;
    }

    public List<String> getSmallImageUrls() {
        return smallImageUrls;
    }

    public void setSmallImageUrls(List<String> smallImageUrls) {
        this.smallImageUrls = smallImageUrls;
    }

    public String getSourceDisplayName() {
        return sourceDisplayName;
    }

    public void setSourceDisplayName(String sourceDisplayName) {
        this.sourceDisplayName = sourceDisplayName;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Number getRating() {
        return this.rating;
    }

    public void setRating(Number rating) {
        this.rating = rating;
    }

    public int getTotalTimeInSeconds() {
        return this.totalTimeInSeconds;
    }

    public void setTotalTimeInSeconds(int totalTimeInSeconds) {
        this.totalTimeInSeconds = totalTimeInSeconds;
    }

}
