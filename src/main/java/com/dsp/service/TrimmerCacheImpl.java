package com.dsp.service;

import com.dsp.pojo.Trimmer;
import com.dsp.repo.TimmerCacheRepo;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
public class TrimmerCacheImpl implements TimmerCacheRepo {

    private RedisTemplate<String, Object> redisTemplate;
    private ValueOperations valueOperations;
    private HashOperations hashOperations;


    public TrimmerCacheImpl(RedisTemplate<String, Object> redisTemplate){
        this.redisTemplate = redisTemplate;
        valueOperations = redisTemplate.opsForValue();
        hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public void save(String id, Trimmer trimmer, int exp, TimeUnit timeUnit) {
        valueOperations.set(id, trimmer, exp, timeUnit);
    }

    @Override
    public Trimmer findById(String id) {
        return (Trimmer) valueOperations.get(id);
    }

}
