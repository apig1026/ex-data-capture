package com.ajoshow.mock.web.dto;

import com.ajoshow.mock.domain.Link;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by andychu on 2017/4/22.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AdContentDto {
    private Set<String> impressionEvent;
    private Set<String> viewEvent;
    @JsonProperty("assets")
    private List<AssetDto> assetsDto;
    private Link link;

    public AdContentDto(){
        impressionEvent = new HashSet<>();
        viewEvent = new HashSet<>();
        assetsDto = new ArrayList<>();
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

    public List<AssetDto> getAssetsDto() {
        return assetsDto;
    }

    public void setAssetsDto(List<AssetDto> assetsDto) {
        this.assetsDto = assetsDto;
    }

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AdContent{");
        sb.append("impressionEvent=").append(impressionEvent);
        sb.append(", viewEvent=").append(viewEvent);
        sb.append(", assetsDto=").append(assetsDto);
        sb.append(", link=").append(link);
        sb.append('}');
        return sb.toString();
    }
}
