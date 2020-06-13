package com.shu.leetcode.lru;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author arlen
 * @since 2020-04-29 18:57
 */
public class LRUCache<K, V> extends LinkedHashMap<K, V> {

    private final int CACHE_MAX_SIZE;

    public LRUCache(int size) {
        super((int)Math.ceil(size / 0.75) + 1, 0.75f, true);
        CACHE_MAX_SIZE = size;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > CACHE_MAX_SIZE;
    }
}
