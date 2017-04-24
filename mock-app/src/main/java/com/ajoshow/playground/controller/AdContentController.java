package com.ajoshow.playground.controller;

import com.ajoshow.playground.ReflectionUtils;
import com.ajoshow.playground.domain.AdContent;
import com.ajoshow.playground.domain.Asset;
import com.ajoshow.playground.domain.AssetMeta;
import com.ajoshow.playground.domain.Title;
import com.ajoshow.playground.domain.dto.AdContentDto;
import com.ajoshow.playground.domain.entity.AdContentAssetEntity;
import com.ajoshow.playground.domain.entity.AdContentEntity;
import com.ajoshow.playground.service.AdContentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.*;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by andychu on 2017/4/22.
 */
@Validated
@Controller
@RequestMapping(value = "/v/1")
public class AdContentController{

    @Value("${app.tenmax.datasource.url}")
    private String tenMaxDSUrl;

    @Value("${app.mockserver.datasource.url}")
    private String mockServerDSUrl;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AdContentService svc;

    @Autowired
    private ObjectMapper objectMapper;

    @ResponseStatus(OK)
    @ResponseBody
    @RequestMapping(method= GET, value="/print")
    public AdContentDto fetchContent() throws IOException {
        AdContentDto dto = fetchAdContent(tenMaxDSUrl);

        System.out.println(objectMapper.writeValueAsString(dto));

        AdContentEntity entity = convertToEntity(dto);
        if(entity != null){
            svc.saveOrUpdateAdContent(entity);
        }else{
            // invalid or null response body causes NULL entity
            // log event or do other business logic.
        }
        return dto;
    }

    @ResponseStatus(OK)
    @ResponseBody
    @RequestMapping(method= POST, value="/query")
    public List<AdContentDto> findAdContentEntityByTitle(
            @RequestParam(name="title") String title
    ) throws IOException {
        // TODO decode title
        List<AdContentEntity> entities = svc.findAdContentEntityByTitle(title);
        List<AdContentDto> dtos = new ArrayList<>();
        for(AdContentEntity entity : entities){
            dtos.add(convertToDto(entity));
        }
        return dtos;
    }

    /**
     * Send GET request to fetch data and return in form of {@link AdContentDto} object.
     *
     * @return {@link AdContentDto} object.
     * @throws IOException
     */
    private AdContentDto fetchAdContent(String url) throws IOException{
        return restTemplate.getForObject(url, AdContentDto.class);
    }

    /**
     * Convert {@link AdContentDto} object to {@link AdContentEntity} object.
     * @param dto
     * @return {@link AdContentEntity}
     */
    private AdContentEntity convertToEntity(AdContentDto dto){
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
    private AdContentDto convertToDto(AdContentEntity entity){
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
