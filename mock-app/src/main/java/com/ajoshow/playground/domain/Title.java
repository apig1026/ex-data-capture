package com.ajoshow.playground.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Created by andychu on 2017/4/22.
 */
@Embeddable
@JsonIgnoreProperties(ignoreUnknown = true)
public class Title {
    private String text;

    @Column(name="title_text")
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Title{");
        sb.append("text='").append(text).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
