package com.ajoshow.mock.web.converter;

import com.ajoshow.mock.domain.utils.ReflectionUtils;
import com.ajoshow.mock.domain.AdContent;
import com.ajoshow.mock.domain.Asset;
import com.ajoshow.mock.domain.AssetMeta;
import com.ajoshow.mock.domain.Title;
import com.ajoshow.mock.web.dto.AdContentDto;
import com.ajoshow.mock.repository.entity.AdContentAssetEntity;
import com.ajoshow.mock.repository.entity.AdContentEntity;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * Created by andychu on 2017/4/25.
 */
public class BeanConverter {

    /**
     * Convert {@link AdContentDto} object to {@link AdContentEntity} object.
     * @param dto
     * @return {@link AdContentEntity}
     */
    public static AdContentEntity convertToEntity(AdContentDto dto){
        AdContentEntity entity = null;
        AdContent adContent = dto.getAdContent();
        if(adContent != null){
            // At DTO level, the title is stored inside of Asset.
            // At Entity level, the title is stored inside of AdContent as an unique identifier.
            // As a result, we need to remove the title from
            // the assets and set it to the entity explicitly.

            List<Asset> assets = Optional.ofNullable(adContent.getAssets())
                    .orElseGet(Collections::emptyList);

            // Find and set title to the entity
            String titleText = assets.stream()
                    .filter(Objects::nonNull)
                    .map(Asset::getTitle)
                    .filter(Objects::nonNull)
                    .map(Title::getText)
                    .filter(StringUtils::isNotBlank)
                    .findFirst()
                    .orElseThrow(IllegalArgumentException::new);

            // Remove title asset from the collection
            Iterator<Asset> iterator = assets.iterator();
            while(iterator.hasNext()){
                if(iterator.next().getTitle() != null){
                    iterator.remove();
                }
            }

            List<AdContentAssetEntity> assetEntities = new ArrayList<>();
            for (Asset asset : adContent.getAssets()) {
                asset.setId(null); // get rid off id.
                assetEntities.add(ReflectionUtils.mapValues(asset, AdContentAssetEntity.class));
            }

            entity = ReflectionUtils.mapValues(adContent, AdContentEntity.class);
            entity.setTitle(titleText);
            entity.setAssets(assetEntities);
        } // else, we got empty adContent, do nothing here.

        return entity;
    }

    /**
     * Convert {@link AdContentEntity} object to {@link AdContentDto} object.
     * @param entity
     * @return {@link AdContentDto}
     */
    public static  AdContentDto convertToDto(AdContentEntity entity){
        AdContentDto dto = new AdContentDto();
        if(entity != null){
            // At DTO level, the title is stored inside of Asset.
            // At Entity level, the title is stored inside of AdContent as an unique identifier.
            // As a result, we need to add title asset back to the collection,
            // as well as to reorganize asset id.
            int count = 1;
            Asset titleAsset = new Asset();
            titleAsset.setId(count);
            titleAsset.setTitle(new Title(entity.getTitle()));
            titleAsset.setMeta(new AssetMeta());

            List<AdContentAssetEntity> assetEntities = entity.getAssets();
            List<Asset> assets = new ArrayList<>();
            for (AdContentAssetEntity assetEntity : assetEntities) {
                Asset asset = ReflectionUtils.mapValues(assetEntity, Asset.class);
                asset.setId(++count);
                assets.add(asset);
            }
            assets.add(0, titleAsset); // prepend at head.

            AdContent adContent = ReflectionUtils.mapValues(entity, AdContent.class);
            adContent.setAssets(assets);
            dto.setAdContent(adContent);

        } // else, return empty dto
        return dto;
    }
}
