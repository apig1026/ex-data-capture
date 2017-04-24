package com.ajoshow.playground.service.impl;

import com.ajoshow.playground.domain.entity.AdContentAssetEntity;
import com.ajoshow.playground.domain.entity.AdContentEntity;
import com.ajoshow.playground.repository.AdContentAssetDao;
import com.ajoshow.playground.repository.AdContentDao;
import com.ajoshow.playground.service.AdContentService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by andychu on 2017/4/22.
 */
@Service
public class AdContentServiceImpl implements AdContentService {

    @Autowired
    private AdContentDao dao;

    @Autowired
    private AdContentAssetDao assetDao;


    @Transactional
    @Override
    public void saveOrUpdateAdContent(AdContentEntity entity){
        saveOrUpdateAdContentAssets(entity.getAssets());

        if(entity.getId() == null){
            dao.create(entity);
        }else{
            dao.update(entity);
        }
    }

    @Transactional
    @Override
    public void saveOrUpdateAdContentAssets(List<AdContentAssetEntity> entities){
        if(CollectionUtils.isNotEmpty(entities)){
            for(AdContentAssetEntity entity : entities){
                if(entity.getId() == null){
                    assetDao.create(entity);
                }else{
                    assetDao.update(entity);
                }
            }
        }// else do nothing.
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    @Override
    public List<AdContentEntity> findAdContentEntityByTitle(String title){
        return dao.findAdContentEntityByTitle(title);
    }


}
