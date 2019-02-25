package com.geanbrandao.gean.reiceitasapp.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ReceitaDetalhes {
    private String id;
    private String yield;
    private String totalTime;
    private List<Images> images;
    private String name;
    private Source source;
    private List<String> ingredientLines;
    private int rating;

    public ReceitaDetalhes() {
    }

    public ReceitaDetalhes(String id, String yield, String totalTime, List<Images> images, String name, Source source, List<String> ingredientLines, int rating) {
        this.id = id;
        this.yield = yield;
        this.totalTime = totalTime;
        this.images = images;
        this.name = name;
        this.source = source;
        this.ingredientLines = ingredientLines;
        this.rating = rating;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public List<String> getIngredientLines() {
        return ingredientLines;
    }

    public void setIngredientLines(List<String> ingredientLines) {
        this.ingredientLines = ingredientLines;
    }

    public String getYield() {
        return yield;
    }

    public void setYield(String yield) {
        this.yield = yield;
    }

    public String getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(String totalTime) {
        this.totalTime = totalTime;
    }

    public List<Images> getImages() {
        return images;
    }

    public void setImages(List<Images> images) {
        this.images = images;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }
}
