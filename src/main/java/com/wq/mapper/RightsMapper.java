package com.wq.mapper;

import com.wq.entity.Rights;
import com.wq.entity.RightsExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RightsMapper {
    int countByExample(RightsExample example);

    int deleteByExample(RightsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Rights record);

    int insertSelective(Rights record);

    List<Rights> selectByExample(RightsExample example);

    Rights selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Rights record, @Param("example") RightsExample example);

    int updateByExample(@Param("record") Rights record, @Param("example") RightsExample example);

    int updateByPrimaryKeySelective(Rights record);

    int updateByPrimaryKey(Rights record);
}