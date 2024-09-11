package com.zibra.cacheeviction.policyimpl;

import com.zibra.cacheeviction.policy.EvictionPolicy;

import java.util.LinkedHashMap;
import java.util.Map;

public class MRUCache<K, V> implements EvictionPolicy<K> {
    private final Map<K, V> cache;
    private K mostRecentlyUsed;

    public MRUCache(int capacity) {
        this.cache = new LinkedHashMap<>(capacity, 0.75f, true);
    }

    @Override
    public void access(K key) {
        mostRecentlyUsed = key;
    }

    @Override
    public K evict() {
        cache.remove(mostRecentlyUsed);
        return mostRecentlyUsed;
    }

    public void put(K key, V value) {
        cache.put(key, value);
        access(key);
    }

    public V get(K key) {
        access(key);
        return cache.getOrDefault(key, null);
    }
}

