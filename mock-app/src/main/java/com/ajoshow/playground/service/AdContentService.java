package com.ajoshow.playground.service;

import com.ajoshow.playground.domain.entity.AdContentAssetEntity;
import com.ajoshow.playground.domain.entity.AdContentEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by andychu on 2017/4/22.
 */
@Service
public interface AdContentService {

    void saveOrUpdateAdContent(AdContentEntity entity);

    void saveOrUpdateAdContentAssets(List<AdContentAssetEntity> entities);

    List<AdContentEntity> findAdContentEntityByTitle(String title);
}
