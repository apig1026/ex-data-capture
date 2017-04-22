package com.ajoshow.playground.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Created by andychu on 2017/4/23.
 */
@Embeddable
@JsonIgnoreProperties(ignoreUnknown = true)
public class AssetMeta {

    private boolean imgNullorImgUrlNull = true;
    private boolean linkNullorLinkUrlNull = true;
    private boolean dataNullorDataValueNull = true;

    @Column
    public boolean isImgNullorImgUrlNull() {
        return imgNullorImgUrlNull;
    }

    public void setImgNullorImgUrlNull(boolean imgNullorImgUrlNull) {
        this.imgNullorImgUrlNull = imgNullorImgUrlNull;
    }

    @Column
    public boolean isLinkNullorLinkUrlNull() {
        return linkNullorLinkUrlNull;
    }

    public void setLinkNullorLinkUrlNull(boolean linkNullorLinkUrlNull) {
        this.linkNullorLinkUrlNull = linkNullorLinkUrlNull;
    }

    @Column
    public boolean isDataNullorDataValueNull() {
        return dataNullorDataValueNull;
    }

    public void setDataNullorDataValueNull(boolean dataNullorDataValueNull) {
        this.dataNullorDataValueNull = dataNullorDataValueNull;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AssetMeta{");
        sb.append("imgNullorImgUrlNull=").append(imgNullorImgUrlNull);
        sb.append(", linkNullorLinkUrlNull=").append(linkNullorLinkUrlNull);
        sb.append(", dataNullorDataValueNull=").append(dataNullorDataValueNull);
        sb.append('}');
        return sb.toString();
    }
}
