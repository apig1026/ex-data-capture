package com.ajoshow.mock.web.converter;

import com.ajoshow.mock.domain.AssetMeta;
import com.ajoshow.mock.domain.Title;
import com.ajoshow.mock.domain.utils.ReflectionUtils;
import com.ajoshow.mock.repository.entity.AdContent;
import com.ajoshow.mock.repository.entity.Asset;
import com.ajoshow.mock.web.dto.AdContentDto;
import com.ajoshow.mock.web.dto.AssetDto;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * Created by andychu on 2017/4/25.
 */
public class BeanConverter {

    /**
     * Convert {@link AdContentDto} object to {@link AdContent} object.
     * @param dto
     * @return {@link AdContent}
     */
    public static AdContent convertToEntity(AdContentDto dto){
        if(dto != null){
            // At DTO level, the title is stored inside of Asset.
            // At Entity level, the title is stored inside of AdContent as an unique identifier.
            // As a result, we need to remove the title from
            // the assets and set it to the entity explicitly.

            List<AssetDto> assetDtos = Optional.ofNullable(dto.getAssetsDto())
                    .orElseGet(Collections::emptyList);

            // Find and set title to the entity
            String titleText = assetDtos.stream()
                    .filter(Objects::nonNull)
                    .map(AssetDto::getTitle)
                    .filter(Objects::nonNull)
                    .map(Title::getText)
                    .filter(StringUtils::isNotBlank)
                    .findFirst()
                    .orElseThrow(IllegalArgumentException::new);

            // Remove title asset from the collection
            Iterator<AssetDto> iterator = assetDtos.iterator();
            while(iterator.hasNext()){
                if(iterator.next().getTitle() != null){
                    iterator.remove();
                }
            }

            List<Asset> assetEntities = new ArrayList<>();
            for (AssetDto assetDto : dto.getAssetsDto()) {
                assetDto.setId(null); // get rid off id.
                assetEntities.add(ReflectionUtils.mapValues(assetDto, Asset.class));
            }

            AdContent entity = ReflectionUtils.mapValues(dto, AdContent.class);
            entity.setTitle(titleText);
            entity.setAssets(assetEntities);
            return entity;
        } else{
            return null;
        }

    }

    /**
     * Convert {@link AdContent} object to {@link AdContentDto} object.
     * @param entity
     * @return {@link AdContentDto}
     */
    public static AdContentDto convertToDto(AdContent entity){
        if(entity != null){
            // At DTO level, the title is stored inside of Asset.
            // At Entity level, the title is stored inside of AdContent as an unique identifier.
            // As a result, we need to add title asset back to the collection,
            // as well as to reorganize asset id.
            int count = 1;
            AssetDto titleAssetDto = new AssetDto();
            titleAssetDto.setId(count);
            titleAssetDto.setTitle(new Title(entity.getTitle()));
            titleAssetDto.setMeta(new AssetMeta());

            List<Asset> assetEntities = entity.getAssets();
            List<AssetDto> assetDtos = new ArrayList<>();
            for (Asset assetEntity : assetEntities) {
                AssetDto assetDto = ReflectionUtils.mapValues(assetEntity, AssetDto.class);
                assetDto.setId(++count);
                assetDtos.add(assetDto);
            }
            assetDtos.add(0, titleAssetDto); // prepend at head.

            AdContentDto dto = ReflectionUtils.mapValues(entity, AdContentDto.class);
            dto.setAssetsDto(assetDtos);

            return dto;
        } else{
            return null;
        }
    }
}
