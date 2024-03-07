package spring1.web1.demo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.JedisPooled;
import spring1.web1.demo.services.RedisDetailsConfig;

@Repository
public class CacheRepositoryImpl implements ICacheRepository {

    @Autowired
    JedisPooled jedisPool;

    @Autowired
    private RedisDetailsConfig redisDetailsConfig;

    @Override
    public void createCacheEntity(String key, String value) {
        jedisPool.setex(key, redisDetailsConfig.getTtl(), value);
    }

    @Override
    public String getCacheEntity(String key) {
        if (isKeyExist(key)){
            return jedisPool.get(key);
        }
        return null;
    }

    @Override
    public void updateCacheEntity(String key, String value) {
        if (isKeyExist(key)){
            jedisPool.setex(key, redisDetailsConfig.getTtl(), value);
        }
    }

    @Override
    public Boolean isKeyExist(String key) {
        return jedisPool.exists(key);
    }

    @Override
    public void removeKey(String key) {
        if (isKeyExist(key)) {
            jedisPool.del(key);
        }
    }

}
