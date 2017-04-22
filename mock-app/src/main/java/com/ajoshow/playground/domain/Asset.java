package com.ajoshow.playground.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

/**
 * Created by andychu on 2017/4/22.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Asset {
    private Integer id;
    private Data data;
    private Title title;
    private Image img;
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

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
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

        if(img != null)
            sb.append(", img=").append(img);

        if(meta != null)
            sb.append(", meta=").append(meta);

        sb.append('}');
        return sb.toString();
    }
}
