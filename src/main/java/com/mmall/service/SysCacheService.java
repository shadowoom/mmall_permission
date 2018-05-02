package com.mmall.service;

import com.google.common.base.Joiner;
import com.mmall.beans.CacheKeyConstants;
import com.mmall.util.JsonMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import redis.clients.jedis.ShardedJedis;

import javax.annotation.Resource;

@Service
@Slf4j
public class SysCacheService {

    @Resource(name = "redisPool")
    private RedisPool redisPool;

    public void saveToCache(String valueToSave, int timeoutSeconds, CacheKeyConstants prefix) {
        saveToCache(valueToSave, timeoutSeconds, prefix, null);
    }

    public void saveToCache(String valueToSave, int timeoutSeconds, CacheKeyConstants prefix, String... keys) {
        if(valueToSave == null) {
            return;
        }
        ShardedJedis shardedJedis = null;
        try{
            String cacheKey = generateCacheKey(prefix, keys);
            shardedJedis = redisPool.instance();
            shardedJedis.setex(cacheKey, timeoutSeconds, valueToSave);
        }
        catch (Exception e) {
            log.error("save cache exception, prefix:{}, keys:{}", prefix.name(),
                    JsonMapper.obj2String(keys), e);
        }
        finally {
            redisPool.safeClose(shardedJedis);
        }
    }

    public String getFromCache(CacheKeyConstants prefix, String... keys) {
        ShardedJedis shardedJedis = null;
        String cacheKey = generateCacheKey(prefix, keys);
        try{
            shardedJedis = redisPool.instance();
            String value = shardedJedis.get(cacheKey);
            return value;
        }
        catch (Exception e) {
            log.error("retrieve cache exception, prefix:{}, keys:{}", prefix.name(),
                    JsonMapper.obj2String(keys), e);
            return null;
        }
        finally {
            redisPool.safeClose(shardedJedis);
        }
    }

    private String generateCacheKey(CacheKeyConstants prefix, String... keys) {
        String key = prefix.name();
        if(keys != null && keys.length > 0) {
            key += "_" + Joiner.on("_").join(keys);
        }
        return key;
    }

}
