package com.geanbrandao.gean.reiceitasapp.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * A recipe search result containing matches, counts, etc.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResultadoFeed {

    private int totalMatchCount;
    private Attribution attribution;
    private List<Receita> matches;

    public ResultadoFeed() {
    }

    public ResultadoFeed(int totalMatchCount, Attribution attribution, List<Receita> matches) {
        this.totalMatchCount = totalMatchCount;
        this.attribution = attribution;
        this.matches = matches;
    }

    public void setTotalMatchCount(int totalMatchCount) {
        this.totalMatchCount = totalMatchCount;
    }

    public int getTotalMatchCount() {
        return totalMatchCount;
    }

    public void setAttribution(Attribution attribution) {
        this.attribution = attribution;
    }

    public Attribution getAttribution() {
        return attribution;
    }

    public void setMatches(List<Receita> matches) {
        this.matches = matches;
    }

    public List<Receita> getMatches() {
        return matches;
    }


}
