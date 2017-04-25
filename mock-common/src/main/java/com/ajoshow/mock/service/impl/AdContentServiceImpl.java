package com.ajoshow.mock.service.impl;

import com.ajoshow.mock.repository.entity.Asset;
import com.ajoshow.mock.repository.entity.AdContent;
import com.ajoshow.mock.repository.AdContentAssetDao;
import com.ajoshow.mock.repository.AdContentDao;
import com.ajoshow.mock.service.AdContentService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;

/**
 * Created by andychu on 2017/4/22.
 */
@Service("AdContentServiceImpl")
public class AdContentServiceImpl implements AdContentService {

    @Autowired
    private AdContentDao dao;

    @Autowired
    private AdContentAssetDao assetDao;


    @Transactional
    @Override
    public void saveOrUpdateAdContent(AdContent entity){
        saveOrUpdateAdContentAssets(entity.getAssets());

        if(entity.getId() == null){
            dao.create(entity);
        }else{
            dao.update(entity);
        }
    }

    @Transactional
    @Override
    public void saveOrUpdateAdContentAssets(List<Asset> entities){
        if(CollectionUtils.isNotEmpty(entities)){
            for(Asset entity : entities){
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
    public List<AdContent> findAdContentEntityByTitle(String title){
        return dao.findAdContentEntityByTitle(title);
    }


    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    @Override
    public Optional<AdContent> getRandomAdContentEntity(){
        long n = dao.countAdContentEntity();
        if(n > 0){
            int randomId = (int)(new SecureRandom().nextDouble() * n) + 1;
            return Optional.of(dao.getAdContentEntityById(randomId));
        }else{
            return Optional.ofNullable(null);
        }
    }

}
