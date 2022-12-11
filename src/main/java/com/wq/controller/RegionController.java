package com.wq.controller;

import com.wq.entity.Region;
import com.wq.service.impl.RegionsServiceImpl;
import com.wq.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
public class RegionController {
    @Autowired
    RegionsServiceImpl regionsServiceImpl;

    @GetMapping("/regions")
    public Result getRegionList(){
        try {
            List<Region> regionList = regionsServiceImpl.getRegionList();
            Map<String, List<Region>> map = new HashMap<>();
            map.put("regionList", regionList);
            System.out.println(map);
            return Result.success(map);
        }catch (Exception e){
            return Result.error(null);
        }
    }
}
