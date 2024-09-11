package com.zibra.cacheeviction.policyimpl;

import com.zibra.cacheeviction.policy.EvictionPolicy;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class LFUCache<K, V> implements EvictionPolicy<K> {
    private final Map<K, V> cache;
    private final Map<K, Integer> usageCount;
    private final int capacity;

    public LFUCache(int capacity) {
        this.cache = new HashMap<>();
        this.usageCount = new HashMap<>();
        this.capacity = capacity;
    }

    @Override
    public void access(K key) {
        usageCount.put(key, usageCount.getOrDefault(key, 0) + 1);
    }

    @Override
    public K evict() {
        K leastUsed = Collections.min(usageCount.entrySet(), Map.Entry.comparingByValue()).getKey();
        cache.remove(leastUsed);
        usageCount.remove(leastUsed);
        return leastUsed;
    }

    public void put(K key, V value) {
        if (cache.size() >= capacity) {
            evict();
        }
        cache.put(key, value);
        access(key);
    }

    public V get(K key) {
        access(key);
        return cache.getOrDefault(key, null);
    }
}
