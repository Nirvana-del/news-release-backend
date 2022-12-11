package com.wq.mapper;

import com.wq.entity.RoleRightsExample;
import com.wq.entity.RoleRightsKey;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RoleRightsMapper {
    int countByExample(RoleRightsExample example);

    int deleteByExample(RoleRightsExample example);

    int deleteByPrimaryKey(RoleRightsKey key);

    int insert(RoleRightsKey record);

    int insertSelective(RoleRightsKey record);

    List<RoleRightsKey> selectByExample(RoleRightsExample example);

    int updateByExampleSelective(@Param("record") RoleRightsKey record, @Param("example") RoleRightsExample example);

    int updateByExample(@Param("record") RoleRightsKey record, @Param("example") RoleRightsExample example);
}