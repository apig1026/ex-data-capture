package com.ajoshow.playground.converter;

import com.ajoshow.playground.domain.*;
import com.ajoshow.playground.domain.entity.AdContentAssetEntity;
import org.dozer.DozerConverter;

import java.util.HashSet;

/**
 * Created by andychu on 2017/4/24.
 */
public class AssetConverter extends DozerConverter<Asset, AdContentAssetEntity> {

    /**
     * Defines two types, which will take part transformation.
     * As Dozer supports bi-directional mapping it is not known which of the classes is source and
     * which is destination. It will be decided in runtime.
     *
     * @param prototypeA type one
     * @param prototypeB type two
     */
    public AssetConverter(Class<Asset> prototypeA, Class<AdContentAssetEntity> prototypeB) {
        super(prototypeA, prototypeB);
    }

    @SuppressWarnings("Duplicates")
    @Override
    public AdContentAssetEntity convertTo(Asset source, AdContentAssetEntity destination) {
        if(source != null) {
            destination = new AdContentAssetEntity();
            if(source.getData() != null) {
                Data sourceData = source.getData();
                Data data = new Data();
                data.setLabel(sourceData.getLabel());
                data.setValue(sourceData.getValue());
                destination.setData(data);
            }

            if(source.getTitle() != null){
                Title sourceTitle = source.getTitle();
                Title title = new Title();
                title.setText(sourceTitle.getText());
                destination.setTitle(title);
            }

            if(source.getImage() != null){
                Image sourceImage = source.getImage();
                Image image = new Image();
                image.setUrl(sourceImage.getUrl());
                image.setHeight(sourceImage.getHeight());
                image.setWidth(sourceImage.getWidth());
                destination.setImage(image);

            }

            if(source.getLink() != null){
                Link sourceLink = source.getLink();
                Link link = new Link();
                link.setUrl(sourceLink.getUrl());
                link.setClicktrackers(new HashSet<>(sourceLink.getClicktrackers()));
                destination.setLink(link);
            }

            if(source.getMeta() != null){
                AssetMeta sourceMeta = source.getMeta();
                AssetMeta meta = new AssetMeta();
                meta.setDataNullorDataValueNull(sourceMeta.isDataNullorDataValueNull());
                meta.setImgNullorImgUrlNull(sourceMeta.isImgNullorImgUrlNull());
                meta.setLinkNullorLinkUrlNull(sourceMeta.isLinkNullorLinkUrlNull());
                destination.setMeta(meta);
            }

            return destination;
        }else {
            return null;
        }
    }

    @SuppressWarnings("Duplicates")
    @Override
    public Asset convertFrom(AdContentAssetEntity source, Asset destination) {
        if(source != null) {
            destination = new Asset();
            if(source.getData() != null) {
                Data sourceData = source.getData();
                Data data = new Data();
                data.setLabel(sourceData.getLabel());
                data.setValue(sourceData.getValue());
                destination.setData(data);
            }

            if(source.getTitle() != null){
                Title sourceTitle = source.getTitle();
                Title title = new Title();
                title.setText(sourceTitle.getText());
                destination.setTitle(title);
            }

            if(source.getImage() != null){
                Image sourceImage = source.getImage();
                Image image = new Image();
                image.setUrl(sourceImage.getUrl());
                image.setHeight(sourceImage.getHeight());
                image.setWidth(sourceImage.getWidth());
                destination.setImage(image);

            }

            if(source.getLink() != null){
                Link sourceLink = source.getLink();
                Link link = new Link();
                link.setUrl(sourceLink.getUrl());
                link.setClicktrackers(new HashSet<>(sourceLink.getClicktrackers()));
                destination.setLink(link);
            }

            if(source.getMeta() != null){
                AssetMeta sourceMeta = source.getMeta();
                AssetMeta meta = new AssetMeta();
                meta.setDataNullorDataValueNull(sourceMeta.isDataNullorDataValueNull());
                meta.setImgNullorImgUrlNull(sourceMeta.isImgNullorImgUrlNull());
                meta.setLinkNullorLinkUrlNull(sourceMeta.isLinkNullorLinkUrlNull());
                destination.setMeta(meta);
            }
            return destination;
        }else {
            return null;
        }
    }

}
