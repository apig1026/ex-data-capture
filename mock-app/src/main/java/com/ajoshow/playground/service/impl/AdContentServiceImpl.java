package com.ajoshow.playground.service.impl;

import com.ajoshow.playground.domain.entity.AdContentEntity;
import com.ajoshow.playground.repository.AdContentDao;
import com.ajoshow.playground.service.AdContentService;
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


    @Transactional
    @Override
    public void saveOrUpdateAdContent(AdContentEntity entity){
        // simple business logic...
        if(entity.getId() == null){
            dao.create(entity);
        }else{
            dao.update(entity);
        }
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    @Override
    public List<AdContentEntity> findAdContentEntityByTitle(String title){
        return dao.findAdContentEntityByTitle(title);
    }


}
