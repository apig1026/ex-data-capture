package com.ajoshow.mock.service;

import com.ajoshow.mock.repository.entity.Asset;
import com.ajoshow.mock.repository.entity.AdContent;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by andychu on 2017/4/22.
 */
@Service
public interface AdContentService {

    /**
     * TODO Comment...
     * @param entity
     */
    void saveOrUpdateAdContent(AdContent entity);

    /**
     * TODO Comment...
     * @param entities
     */
    void saveOrUpdateAdContentAssets(List<Asset> entities);

    /**
     * TODO Comment...
     * @param title
     * @return
     */
    List<AdContent> findAdContentEntityByTitle(String title);

    /**
     * TODO Comment...
     * @return
     */
    Optional<AdContent> getRandomAdContentEntity();
}
