package com.ajoshow.mock.domain.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.util.ReflectionUtils.findField;

/**
 * Custom bean reflection utility.
 * Created by andychu on 2017/4/23.
 * TODO just a quick implementation to substitute for DozeBeanMapper.
 * TODO this utility is MUCH LESS restrict on class checking while perform mapping, the object members may be manipulated. It is extremely DANGEROUS to use.
 * TODO ADD-Feature: design and implement mapping configuration
 * TODO test: thorough unit test
 */
public class ReflectionUtils {

    private static List<Field> getAllField(Class src) {
        if (src == Object.class) {
            return new ArrayList<>();
        } else {
            List<Field> ans = new ArrayList<>();
            ans.addAll(Arrays.asList(src.getDeclaredFields()));
            ans.addAll(getAllField(src.getSuperclass()));
            return ans;
        }
    }

    public static List<String> getFieldNames(Class src) {
        List<String> list = new ArrayList<>();
        List<Field> fields = getAllField(src);
        for (Field f : fields) {
            list.add(f.getName());
        }
        return list;
    }

    public static Object getValue(Object src, String fieldName) throws IllegalArgumentException,
            IllegalAccessException {
        Field f = findField(src.getClass(), fieldName);
        f.setAccessible(true); // TODO dangerous
        return f.get(src);
    }

    public static void putFieldValue(Object src, String fieldName, Object value)
            throws IllegalArgumentException, IllegalAccessException {
        Field f = findField(src.getClass(), fieldName);
        f.setAccessible(true); // TODO dangerous
        f.set(src, value);
    }
    /**
     * Map field values from source object to the given instance of class. Null value will be ignored.
     *
     * @param source
     * @param destinationClass
     * @return
     */
    // TODO dangerous! class checking is absent.
    public static <T> T mapValues(Object source, Class<T> destinationClass) {
        try {
            T dest = destinationClass.newInstance();
            mapValues(source, dest);
            return dest;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Map field values from source object to the destination object. Null value will be ignored.
     *
     * @param source
     * @param dest
     * @return the given destination object with value copied from the source.
     */
    // TODO dangerous! class checking is absent.
    public static void mapValues(Object source, Object dest) {
        List<String> fieldNames = ReflectionUtils.getFieldNames(source.getClass());
        for (String name : fieldNames) {
            try {
                Object value = ReflectionUtils.getValue(source, name);
                if (value != null) {
                    try {
                        ReflectionUtils.putFieldValue(dest, name, value);
                    } catch (NullPointerException e) {
                        // ignore NPE
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException("Reflection map values failed: " + name, e);
            }
        }
    }

}
