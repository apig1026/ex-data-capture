package com.ajoshow.mock.web.dto;

import com.ajoshow.mock.domain.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

/**
 * Created by andychu on 2017/4/22.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AssetDto {
    private Integer id;
    private Data data;
    private Title title;
    @JsonProperty("img")
    private Image image;
    private Link link;
    @JsonUnwrapped
    private AssetMeta meta;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public AssetMeta getMeta() {
        return meta;
    }

    public void setMeta(AssetMeta meta) {
        this.meta = meta;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Asset{");
        sb.append("id=").append(id);

        if(data != null)
            sb.append(", data=").append(data);

        if(title != null)
            sb.append(", title=").append(title);

        if(link != null)
            sb.append(", link=").append(link);

        if(image != null)
            sb.append(", image=").append(image);

        if(meta != null)
            sb.append(", meta=").append(meta);

        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AssetDto asset = (AssetDto) o;

        if (id != null ? !id.equals(asset.id) : asset.id != null) return false;
        if (data != null ? !data.equals(asset.data) : asset.data != null) return false;
        if (title != null ? !title.equals(asset.title) : asset.title != null) return false;
        if (image != null ? !image.equals(asset.image) : asset.image != null) return false;
        return link != null ? link.equals(asset.link) : asset.link == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (data != null ? data.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (image != null ? image.hashCode() : 0);
        result = 31 * result + (link != null ? link.hashCode() : 0);
        return result;
    }
}
