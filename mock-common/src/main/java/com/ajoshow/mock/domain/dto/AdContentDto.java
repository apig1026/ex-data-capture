package com.ajoshow.mock.domain.dto;

import com.ajoshow.mock.domain.AdContent;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by andychu on 2017/4/22.
 */
public class AdContentDto {
    @JsonProperty("native")
    private AdContent adContent;

    public AdContent getAdContent() {
        return adContent;
    }

    public void setAdContent(AdContent adContent) {
        this.adContent = adContent;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AdContentDto{");
        sb.append("adContent=").append(adContent);
        sb.append('}');
        return sb.toString();
    }
}
