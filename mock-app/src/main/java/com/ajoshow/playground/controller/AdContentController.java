package com.ajoshow.playground.controller;

import com.ajoshow.playground.domain.AdContent;
import com.ajoshow.playground.domain.Asset;
import com.ajoshow.playground.domain.AssetMeta;
import com.ajoshow.playground.domain.Title;
import com.ajoshow.playground.domain.dto.AdContentDto;
import com.ajoshow.playground.domain.entity.AdContentEntity;
import com.ajoshow.playground.service.AdContentService;
import com.ajoshow.playground.service.impl.AdContentServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.IntStream;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by andychu on 2017/4/22.
 */
@Validated
@Controller
@RequestMapping(value = "/adContents")
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

    @Autowired
    private DozerBeanMapper beanMapper;

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
    @RequestMapping(method= POST, value="/print")
    public AdContentDto findAdContentEntityByTitle() throws IOException {
        AdContentDto dto = fetchAdContent(tenMaxDSUrl);

        System.out.println(objectMapper.writeValueAsString(dto));

        dto.getAdContent();


        return dto;
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
            entity = beanMapper.map(adContent, AdContentEntity.class);
            // At DTO level, the title is stored inside of Asset.
            // At Entity level, the title is stored inside of AdContent as an unique identifier.
            // As a result, we need to remove the title from
            // the assets and set it to the entity explicitly.

            // Find and set title to the entity
            String titleText = Optional.ofNullable(adContent.getAssets())
                    .orElseGet(Collections::emptyList)
                    .stream()
                    .filter(Objects::nonNull)
                    .map(Asset::getTitle)
                    .filter(Objects::nonNull)
                    .map(Title::getText)
                    .filter(StringUtils::isNotBlank)
                    .findFirst()
                    .orElseThrow(IllegalArgumentException::new);
            entity.setTitle(new Title(titleText));

            // Remove title asset from the collection.
            List<Asset> assets = adContent.getAssets(); // won't be null.
            // precondition, assume only and only if one title asset in the list.
            assets.removeIf(asset -> asset.getTitle() != null);

            // Get rid off id
            for (Asset asset : assets) {
                asset.setId(null);
            }

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
            AdContent adContent = beanMapper.map(entity, AdContent.class);
            // At DTO level, the title is stored inside of Asset.
            // At Entity level, the title is stored inside of AdContent as an unique identifier.
            // As a result, we need to add title asset back to the collection,
            // as well as to reorganize asset id.

            int count = 1;
            Asset asset = new Asset();
            asset.setId(count++);
            asset.setTitle(entity.getTitle());
            asset.setMeta(new AssetMeta());

            List<Asset> assets = adContent.getAssets();
            for (int i=count; i<assets.size(); i++) {
                assets.get(i).setId(i);
            }

            assets.set(0, asset); // prepend at head.
            dto.setAdContent(adContent);

        } // else, return empty dto
        return dto;
    }
}
