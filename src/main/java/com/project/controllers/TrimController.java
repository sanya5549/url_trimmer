package com.project.controllers;


import com.dsp.pojo.Trimmer;
import com.dsp.service.TrimmerCRUDService;
import com.project.pojo.InputURLObejct;
import com.project.pojo.ResponseObject;
import com.project.services.EventScheduler;
import com.project.services.TrimmingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin

public class TrimController {


    @Autowired
    private TrimmingService trimmingService;

    @Autowired
    EventScheduler eventScheduler;

    @PostMapping("/api/trimurl")
    @ResponseBody
    public Object getTrimmedURL(@RequestBody InputURLObejct inputURLObejct){
        ResponseObject responseObject = trimmingService.getTrimmedURLObject(inputURLObejct.getUrl(),
                Integer.parseInt(inputURLObejct.getExpireIn()), inputURLObejct.getUnit());
        return responseObject;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Object getLongUrl(@PathVariable String id){
        return trimmingService.getOrignalUrl(id);
    }

//    @GetMapping("/delete")
//    public String delete(){
//        eventScheduler.deletedExpiredURL();
//        return "ok";
//    }

}
