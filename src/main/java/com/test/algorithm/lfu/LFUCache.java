package com.test.algorithm.lfu;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * TODO 460. LFU缓存
 * 设计并实现最不经常使用（LFU）缓存的数据结构。它应该支持以下操作：get 和 put。
 * <p>
 * get(key) - 如果键存在于缓存中，则获取键的值（总是正数），否则返回 -1。
 * put(key, value) - 如果键不存在，请设置或插入值。当缓存达到其容量时，它应该在插入新项目之前，使最不经常使用的项目无效。在此问题中，当存在平局（即两个或更多个键具有相同使用频率）时，最近最少使用的键将被去除。
 * <p>
 * 进阶：
 * 你是否可以在 O(1) 时间复杂度内执行两项操作？
 * <p>
 * 示例：
 * <p>
 * LFUCache cache = new LFUCache( 2  capacity (缓存容量)  );
 * <p>
 * cache.put(1,1);
 * cache.put(2,2);
 * cache.get(1);       // 返回 1
 * cache.put(3,3);    // 去除 key 2
 * cache.get(2);       // 返回 -1 (未找到key 2)
 * cache.get(3);       // 返回 3
 * cache.put(4,4);    // 去除 key 1
 * cache.get(1);       // 返回 -1 (未找到 key 1)
 * cache.get(3);       // 返回 3
 * cache.get(4);       // 返回 4
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/lfu-cache
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class LFUCache {
    int min;
    int capacity;
    Map<Integer, Integer> keyToCount;
    Map<Integer, LinkedHashMap<Integer, Integer>> countToLRU;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.min = 0;
        keyToCount = new HashMap<>();
        countToLRU = new HashMap<>();
    }

    public LinkedHashMap<Integer, Integer> getLHM() {
        return new LinkedHashMap<Integer, Integer>(capacity, 0.75f, true) {
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return size() > capacity;
            }
        };
    }

    public int get(int key) {
        if (!keyToCount.containsKey(key)) {
            return -1;
        }
        Integer count = keyToCount.get(key);
        Integer val = countToLRU.get(count).remove(key);
        if (count == min && countToLRU.get(count).size() == 0) {
            min++;
        }
        keyToCount.put(key, count + 1);
        countToLRU.putIfAbsent(count + 1, getLHM());
        countToLRU.get(count + 1).put(key, val);
        return val;
    }

    public void put(int key, int value) {
        if (capacity == 0) {
            return;
        }
        if (keyToCount.containsKey(key)) {
            get(key);
            int freq = keyToCount.get(key);
            countToLRU.get(freq).put(key, value);
            return;
        }
        if (keyToCount.size() >= capacity) {
            Integer k = countToLRU.get(min).entrySet().iterator().next().getKey();
            countToLRU.get(min).remove(k);
            keyToCount.remove(k);
        }
        min = 1;
        keyToCount.put(key, 1);
        countToLRU.putIfAbsent(1, getLHM());
        countToLRU.get(1).put(key, value);
    }

    public static void main(String[] args) {
        LFUCache lfuCache = new LFUCache(2);
        lfuCache.put(1,1);
        lfuCache.put(2,2);
        lfuCache.get(1);
        lfuCache.put(3,3);
        lfuCache.get(2);
        lfuCache.get(3);


    }
}
