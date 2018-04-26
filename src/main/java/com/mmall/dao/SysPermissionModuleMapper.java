package com.mmall.dao;

import com.mmall.model.SysPermissionModule;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysPermissionModuleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysPermissionModule record);

    int insertSelective(SysPermissionModule record);

    SysPermissionModule selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysPermissionModule record);

    int updateByPrimaryKey(SysPermissionModule record);

    List<SysPermissionModule> getChildPermissionModuleListByLevel(@Param("level")String level);

    void batchUpdateLevel(@Param("sysPermissionModuleList")List<SysPermissionModule> sysPermissionModuleList);

    int countByNameAndParentId(@Param("parentId")Integer parentId, @Param("permissionModuleName")String permissionModuleName, @Param("id")Integer id);
}