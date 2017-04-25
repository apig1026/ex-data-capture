package com.ajoshow.mock.repository;

import com.ajoshow.mock.repository.entity.AdContentAssetEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by andychu on 2017/4/23.
 */
// simple CRUD jpa
@Repository
public class AdContentAssetDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void create(AdContentAssetEntity entity){
        entityManager.persist(entity);
    }

    public void update(AdContentAssetEntity entity){
        entityManager.merge(entity);
    }

    public AdContentAssetEntity getAdContentAssetEntityById(int id){
        return entityManager.find(AdContentAssetEntity.class, id);
    }

    public void delete(int id){
        AdContentAssetEntity entity = getAdContentAssetEntityById(id);
        if(entity != null){
            entityManager.remove(entity);
        }
    }
}
