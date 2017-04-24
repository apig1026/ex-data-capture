package com.ajoshow.mock.domain.entity;

import com.ajoshow.mock.domain.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by andychu on 2017/4/23.
 */
@Entity
@Table(name = "ad_content_asset")
public class AdContentAssetEntity implements Serializable {
    private Integer id;
    private Data data;
    private Title title;
    private Image image;
    private Link link;
    private AssetMeta meta;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
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
