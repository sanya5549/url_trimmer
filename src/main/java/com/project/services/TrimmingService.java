package com.project.services;

import com.dsp.pojo.Trimmer;
import com.dsp.service.TrimmerCRUDService;
import com.dsp.service.TrimmerCacheImpl;
import com.project.pojo.ResponseObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Time;
import java.util.concurrent.TimeUnit;

@Component
public class TrimmingService {
    @Autowired
    ConversionUtils conversionUtils;

    @Autowired
    TrimmerCacheImpl trimmerCache;

    @Autowired
    TrimmerCRUDService trimmerCRUDService;

    private static  final Logger log = LoggerFactory.getLogger(TrimmingService.class);

    public static int latestId =1 ;
    public ResponseObject getTrimmedURLObject(String longURL, int expInHours, String unit){
        log.debug("inside function getTrimmedURLObject :: starting trimming url");
        ResponseObject responseObject = new ResponseObject();
        TimeUnit timeUnit = getTimeUnit(unit);
        Trimmer trimmer = null;
        try{
            int id = latestId;
            trimmer = new Trimmer();

            trimmer.setPartition(id % 1000 + 1);
            trimmer.setId(id);

            long createTime = System.currentTimeMillis();
            long expTime = createTime + expInHours * 60 * 60 * 1000;

            trimmer.setCreateTime(createTime);
            trimmer.setExpTime(expTime);
            trimmer.setLongUrl(longURL);
            trimmer.setShortUrl(conversionUtils.trim(id));
            latestId++;
            updatelatestID();

            trimmerCache.save(String.valueOf(id), trimmer, expInHours, timeUnit);
            trimmerCRUDService.insertTrimURL(trimmer);

            responseObject.setTrimmer(trimmer);
            log.debug("inserted trimmer object");

        }catch (Exception e){
            responseObject.setTrimmer(null);
            responseObject.setExpetion(new Exception("Unable to shorten url"));
        }

        return responseObject;
    }

    private TimeUnit getTimeUnit(String unit){
        switch (unit){
            case "hour": return TimeUnit.HOURS;
            case "min" : return TimeUnit.MINUTES;
            case "sec" : return TimeUnit.SECONDS;
            default: return TimeUnit.DAYS;
        }
    }
    private void updatelatestID(){
        File file = new File("/home/vassar/projects/url_trimmer/src/main/resources/latestId");
        FileWriter writer = null;
        try {
            writer = new FileWriter(file);
            BufferedWriter bwr = new BufferedWriter(writer);
            bwr.write(String.valueOf(latestId));
            bwr.close();
            System.out.println("succesfully written to a file");
        } catch (IOException e) {
            System.out.println("unable to update latest id in file");
        }
    }

    public ResponseObject getOrignalUrl(String trimmedUrl){
        int id = conversionUtils.retrieve(trimmedUrl);
        ResponseObject responseObject = new ResponseObject();

        int partition = id % 1000 + 1;
        try{
            Trimmer trimmerObj = trimmerCache.findById(String.valueOf(id));
            if(trimmedUrl == null){
                responseObject.setTrimmer(null);
                responseObject.setExpetion(new Exception("Link expired"));
            }else{
                responseObject.setTrimmer(trimmerObj);
            }
        }catch (IndexOutOfBoundsException e){
            System.out.println("No such short url found");
            responseObject.setTrimmer(null);
            responseObject.setExpetion(new Exception("No such Short Url"));
        }
        return responseObject;
    }

}
