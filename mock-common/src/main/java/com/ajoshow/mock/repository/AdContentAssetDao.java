package com.ajoshow.mock.repository;

import com.ajoshow.mock.repository.entity.Asset;
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

    public void create(Asset entity){
        entityManager.persist(entity);
    }

    public void update(Asset entity){
        entityManager.merge(entity);
    }

    public Asset getAdContentAssetEntityById(int id){
        return entityManager.find(Asset.class, id);
    }

    public void delete(int id){
        Asset entity = getAdContentAssetEntityById(id);
        if(entity != null){
            entityManager.remove(entity);
        }
    }
}
