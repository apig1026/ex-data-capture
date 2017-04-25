package com.ajoshow.mock.service;

import com.ajoshow.mock.repository.entity.AdContentAssetEntity;
import com.ajoshow.mock.repository.entity.AdContentEntity;
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
    void saveOrUpdateAdContent(AdContentEntity entity);

    /**
     * TODO Comment...
     * @param entities
     */
    void saveOrUpdateAdContentAssets(List<AdContentAssetEntity> entities);

    /**
     * TODO Comment...
     * @param title
     * @return
     */
    List<AdContentEntity> findAdContentEntityByTitle(String title);

    /**
     * TODO Comment...
     * @return
     */
    Optional<AdContentEntity> getRandomAdContentEntity();
}
