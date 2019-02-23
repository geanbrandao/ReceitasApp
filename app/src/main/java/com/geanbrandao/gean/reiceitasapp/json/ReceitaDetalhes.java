package com.geanbrandao.gean.reiceitasapp.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReceitaDetalhes {

    private String yield;
    private String totalTime;
    private List<Images> images;
    private String name;
    private Source source;

    public ReceitaDetalhes() {
    }

    public ReceitaDetalhes(String yield, String totalTime, List<Images> images, String name, Source source) {
        this.yield = yield;
        this.totalTime = totalTime;
        this.images = images;
        this.name = name;
        this.source = source;
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
