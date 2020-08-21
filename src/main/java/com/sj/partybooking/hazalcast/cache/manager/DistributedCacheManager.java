package com.sj.partybooking.hazalcast.cache.manager;



import java.util.concurrent.ConcurrentMap;

public interface DistributedCacheManager {

    <K, V> ConcurrentMap<K, V> getCache(final String cacheName);

}
