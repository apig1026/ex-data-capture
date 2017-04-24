package com.ajoshow.mock.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Created by andychu on 2017/4/22.
 */
@Embeddable
@JsonIgnoreProperties(ignoreUnknown = true)
public class Image {

    @JsonProperty("w")
    private Integer width;

    @JsonProperty("h")
    private Integer height;

    private String url;

    @Column(name="image_width")
    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    @Column(name="image_height")
    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    @Column(name="image_url", length=1000)
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Image{");

        if(width != null)
            sb.append(", width=").append(width);

        if(height != null)
            sb.append(", height=").append(height);

        if(url != null)
            sb.append(", url='").append(url).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Image image = (Image) o;

        return url != null ? url.equals(image.url) : image.url == null;
    }

    @Override
    public int hashCode() {
        return url != null ? url.hashCode() : 0;
    }
}
