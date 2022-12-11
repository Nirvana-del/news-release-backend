package com.wq.mapper;

import com.wq.entity.SubRights;
import com.wq.entity.SubRightsExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SubRightsMapper {
    int countByExample(SubRightsExample example);

    int deleteByExample(SubRightsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SubRights record);

    int insertSelective(SubRights record);

    List<SubRights> selectByExample(SubRightsExample example);

    SubRights selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SubRights record, @Param("example") SubRightsExample example);

    int updateByExample(@Param("record") SubRights record, @Param("example") SubRightsExample example);

    int updateByPrimaryKeySelective(SubRights record);

    int updateByPrimaryKey(SubRights record);
}