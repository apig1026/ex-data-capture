package com.ajoshow.playground.converter;

import com.ajoshow.playground.domain.*;
import com.ajoshow.playground.domain.entity.AdContentAssetEntity;
import org.dozer.DozerConverter;

import java.util.HashSet;

/**
 * Created by andychu on 2017/4/24.
 */
public class LinkConverter extends DozerConverter<Link, Link> {


    /**
     * Defines two types, which will take part transformation.
     * As Dozer supports bi-directional mapping it is not known which of the classes is source and
     * which is destination. It will be decided in runtime.
     *
     * @param prototypeA type one
     * @param prototypeB type two
     */
    public LinkConverter(Class<Link> prototypeA, Class<Link> prototypeB) {
        super(prototypeA, prototypeB);
    }

    @Override
    public Link convertTo(Link source, Link destination) {
        return convert(source, destination);
    }

    @Override
    public Link convertFrom(Link source, Link destination) {
        return convert(source, destination);
    }

    private Link convert(Link source, Link destination){
        if(source != null){
            destination = new Link();
            destination.setUrl(source.getUrl());
            destination.setClicktrackers(new HashSet<>(source.getClicktrackers()));
            return destination;
        }else {
            return null;
        }
    }
}
