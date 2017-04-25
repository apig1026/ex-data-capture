package com.ajoshow.mock.repository.converter;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by andychu on 2017/4/23.
 */
@Converter
public class StringSetConverter implements AttributeConverter<Set<String>, String> {

    @Override
    public String convertToDatabaseColumn(Set<String> list) {
        if(CollectionUtils.isNotEmpty(list)) {
            return String.join(";", list);
        }else{
            return null;
        }
    }

    @Override
    public Set<String> convertToEntityAttribute(String joined) {
        if(StringUtils.isNoneBlank(joined)) {
            return new HashSet<>(Arrays.asList(joined.split(";")));
        }else{
            return null;
        }
    }

}
