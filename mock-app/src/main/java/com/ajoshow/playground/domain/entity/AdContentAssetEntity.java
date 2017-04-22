package com.ajoshow.playground.domain.entity;

import com.ajoshow.playground.domain.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by andychu on 2017/4/23.
 */
@Entity
@Table(name = "ad_content_asset")
public class AdContentAssetEntity implements Serializable {
    private Long id;
    private Data data;
    private Title title;
    private Image img;
    private Link link;
    private AssetMeta meta;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Embedded
    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    @Embedded
    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    @Embedded
    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    @Embedded
    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }

    @Embedded
    public AssetMeta getMeta() {
        return meta;
    }

    public void setMeta(AssetMeta meta) {
        this.meta = meta;
    }
}