package com.zibra.cacheeviction;

import com.zibra.cacheeviction.policyimpl.LFUCache;
import com.zibra.cacheeviction.policyimpl.LRUCache;
import com.zibra.cacheeviction.policyimpl.MRUCache;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class CacheEvictionPolicies {
    public static void main(String[] args) {
        // LRU Cache example
        LRUCache<Integer, String> lruCache = new LRUCache<>(3);
        lruCache.put(1, "one");
        lruCache.put(2, "two");
        lruCache.put(3, "three");
        System.out.println("LRU Cache: " + lruCache.get(1)); // Access 1, now 1 is recently used
        lruCache.put(4, "four"); // Evicts 2 (least recently used)
        System.out.println("LRU Cache after eviction: " + lruCache.get(2)); // null, because 2 is evicted

        // MRU Cache example
        MRUCache<Integer, String> mruCache = new MRUCache<>(3);
        mruCache.put(1, "one");
        mruCache.put(2, "two");
        mruCache.put(3, "three");
        System.out.println("MRU Cache: " + mruCache.get(3)); // Access 3
        mruCache.put(4, "four"); // Evicts 3 (most recently used)
        System.out.println("MRU Cache after eviction: " + mruCache.get(3)); // null, because 3 is evicted

        // LFU Cache example
        LFUCache<Integer, String> lfuCache = new LFUCache<>(3);
        lfuCache.put(1, "one");
        lfuCache.put(2, "two");
        lfuCache.put(3, "three");
        lfuCache.get(1); // Increase usage count for 1
        lfuCache.get(1); // Increase usage count for 1 again
        lfuCache.put(4, "four"); // Evicts 2 (least frequently used)
        System.out.println("LFU Cache after eviction: " + lfuCache.get(2)); // null, because 2 is evicted

    }
}