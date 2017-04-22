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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Title title = (Title) o;

        return text != null ? text.equals(title.text) : title.text == null;
    }

    @Override
    public int hashCode() {
        return text != null ? text.hashCode() : 0;
    }
}
