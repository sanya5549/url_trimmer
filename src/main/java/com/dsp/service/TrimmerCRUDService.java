package com.dsp.service;

import com.dsp.pojo.Trimmer;
import com.dsp.repo.TrimmerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TrimmerCRUDService {

    @Autowired
    private TrimmerRepo trimmerRepo;

    public List<Trimmer> getAllTrimmedUrlList(){
        return (List<Trimmer>) trimmerRepo.findAll();
    }
    public void insertTrimURL(Trimmer trimmer){
        trimmerRepo.save(trimmer);
    }

    public List<Trimmer> getTrimmedUrl(int partition, int id){
        return trimmerRepo.findByPrimaryKey(partition, id);
    }

//    public void deleteExpiredURL(long expTime){
//        trimmerRepo.deleteTrimmerByExpTimeBefore(expTime);
//    }
}

