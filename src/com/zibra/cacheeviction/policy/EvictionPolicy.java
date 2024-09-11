package com.zibra.cacheeviction.policy;

public interface EvictionPolicy<K> {

    void access(K key);
    K evict();

}
