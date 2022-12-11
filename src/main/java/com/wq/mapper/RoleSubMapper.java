package com.wq.mapper;

import com.wq.entity.RoleSubExample;
import com.wq.entity.RoleSubKey;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RoleSubMapper {
    int countByExample(RoleSubExample example);

    int deleteByExample(RoleSubExample example);

    int deleteByPrimaryKey(RoleSubKey key);

    int insert(RoleSubKey record);

    int insertSelective(RoleSubKey record);

    List<RoleSubKey> selectByExample(RoleSubExample example);

    int updateByExampleSelective(@Param("record") RoleSubKey record, @Param("example") RoleSubExample example);

    int updateByExample(@Param("record") RoleSubKey record, @Param("example") RoleSubExample example);
}