package com.ajoshow.playground.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by andychu on 2017/4/22.
 */
public class AdContent {
    @JsonProperty("native")
    private Content content;

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AdContent{");
        sb.append("content=").append(content);
        sb.append('}');
        return sb.toString();
    }
}
