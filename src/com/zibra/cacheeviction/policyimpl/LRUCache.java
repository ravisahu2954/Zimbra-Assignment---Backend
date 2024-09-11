package com.zibra.cacheeviction.policyimpl;

import com.zibra.cacheeviction.policy.EvictionPolicy;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache<K, V> implements EvictionPolicy<K> {
    private final LinkedHashMap<K, V> cache;

    public LRUCache(int capacity) {
        this.cache = new LinkedHashMap<K, V>(capacity, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
                return size() > capacity;
            }
        };
    }

    @Override
    public void access(K key) {
        cache.get(key);  // accessing key
    }

    @Override
    public K evict() {
        K eldest = cache.keySet().iterator().next();
        cache.remove(eldest);
        return eldest;
    }

    public void put(K key, V value) {
        cache.put(key, value);
    }

    public V get(K key) {
        return cache.getOrDefault(key, null);
    }
}