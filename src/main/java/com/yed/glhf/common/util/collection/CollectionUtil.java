package com.yed.glhf.common.util.collection;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.apache.commons.beanutils.PropertyUtils;

import java.util.*;


public class CollectionUtil extends org.springside.modules.utils.collection.CollectionUtil {

    public static <E> Set<E> extractToSet(final Collection collection, final String propertyName) {
        if (isEmpty(collection)) {
            return Sets.newHashSet();
        }
        Set set = new LinkedHashSet();
        try {
            for (Object obj : collection) {
                set.add(PropertyUtils.getProperty(obj, propertyName));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return set;
    }


    public static <E> List<E> extractToList(final Collection collection, final String propertyName) {
        if (isEmpty(collection)) {
            return Lists.newArrayList();
        }
        List<E> list = new ArrayList(collection.size());
        try {
            for (Object obj : collection) {
                E value = (E) PropertyUtils.getProperty(obj, propertyName);
                if (obj != null && value != null) {
                    list.add(value);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static <K, V> Map<K, V> extractToMap(final Collection collection, final String keyPropertyName) {
        if (isEmpty(collection)) {
            return Maps.<K, V>newHashMap();
        }
        Map<K, V> map = new HashMap<K, V>(collection.size());
        try {
            for (Object obj : collection) {
                K key = (K) PropertyUtils.getProperty(obj, keyPropertyName);
                if (obj != null && key != null) {
                    map.put(key, (V) obj);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    public static <K, E> Map<K, List<E>> extractToMapList(final Collection collection, final String keyPropertyName) {
        if (isEmpty(collection)) {
            return Maps.<K, List<E>>newHashMap();
        }
        Map<K, List<E>> map = new HashMap<K, List<E>>(collection.size());
        try {
            for (Object obj : collection) {
                K key = (K) PropertyUtils.getProperty(obj, keyPropertyName);
                if (obj != null && key != null) {
                    if (!map.containsKey(key)) {
                        map.put(key, Lists.newArrayList());
                    }
                    map.get(key).add((E) obj);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 返回a+b的新List.
     */
    public static <T> List<T> union(final Collection<T> a, final Collection<T> b) {
        List<T> result = new ArrayList<T>(a);
        result.addAll(b);
        return result;
    }

    /**
     * 返回a-b的新List.
     */
    public static <T> List<T> subtract(final Collection<T> a, final Collection<T> b) {
        List<T> list = new ArrayList<T>(a);
        for (T element : b) {
            list.remove(element);
        }

        return list;
    }

    /**
     * 返回a与b的交集的新List.
     */
    public static <T> List<T> intersection(Collection<T> a, Collection<T> b) {
        List<T> list = new ArrayList<T>();
        for (T element : a) {
            if (b.contains(element)) {
                list.add(element);
            }
        }
        return list;
    }

    public static Map<String, Object> getMap(String key, Object value) {
        Map<String, Object> map = Maps.newHashMap();
        map.put(key, value);
        return map;
    }

    public static <E> List<List<E>> splitList(final List<E> list, int len) {
        if (isEmpty(list)) {
            return null;
        }
        List<List<E>> result = new ArrayList<List<E>>();
        int size = list.size();
        int count = (size + len - 1) / len;
        for (int i = 0; i < count; i++) {
            List<E> subList = list.subList(i * len, ((i + 1) * len > size ? size : len * (i + 1)));
            result.add(subList);
        }
        return result;
    }

    public static StringBuffer splitSqlStr(final List<String> list) {
        if (isEmpty(list)) {
            return null;
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < list.size(); i++) {
            if (i == list.size() - 1) {
                sb.append("'").append(list.get(i)).append("'");
            } else {
                sb.append("'").append(list.get(i)).append("'").append(",");
            }
        }
        return sb;
    }

}
