package com.ajoshow.playground.service;

import com.ajoshow.playground.domain.entity.AdContentEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by andychu on 2017/4/22.
 */
@Service
public interface AdContentService {

    void saveOrUpdateAdContent(AdContentEntity entity);

    List<AdContentEntity> findAdContentEntityByTitle(String title);
}
