package com.dsp.repo;

import com.dsp.pojo.Trimmer;

import java.util.List;
import java.util.concurrent.TimeUnit;

public interface TimmerCacheRepo {
    void save(String id, Trimmer trimmer, int exp, TimeUnit timeUnit);
    Trimmer findById(String id);
}
