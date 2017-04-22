package com.ajoshow.playground.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by andychu on 2017/4/22.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Content {
    private Set<String> impressionEvent;
    private Set<String> viewEvent;
    private List<Asset> assets;
    private Link link;

    public Content(){
        impressionEvent = new HashSet<>();
        viewEvent = new HashSet<>();
        assets = new ArrayList<>();
    }

    public Set<String> getImpressionEvent() {
        return impressionEvent;
    }

    public void setImpressionEvent(Set<String> impressionEvent) {
        this.impressionEvent = impressionEvent;
    }

    public Set<String> getViewEvent() {
        return viewEvent;
    }

    public void setViewEvent(Set<String> viewEvent) {
        this.viewEvent = viewEvent;
    }

    public List<Asset> getAssets() {
        return assets;
    }

    public void setAssets(List<Asset> assets) {
        this.assets = assets;
    }

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Content{");
        sb.append("impressionEvent=").append(impressionEvent);
        sb.append(", viewEvent=").append(viewEvent);
        sb.append(", assets=").append(assets);
        sb.append(", link=").append(link);
        sb.append('}');
        return sb.toString();
    }
}