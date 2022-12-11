package com.wq.service.impl;

import com.wq.entity.Region;
import com.wq.entity.RegionExample;
import com.wq.mapper.RegionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionsServiceImpl implements com.wq.service.RegionsService {
    @Autowired
    RegionMapper regionMapper;
    @Override
    public List<Region> getRegionList() {
        RegionExample regionExample = new RegionExample();
        return regionMapper.selectByExample(regionExample);
    }
}
