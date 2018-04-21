package com.mmall.dao;

import com.mmall.model.SysPermissionModule;

public interface SysPermissionModuleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysPermissionModule record);

    int insertSelective(SysPermissionModule record);

    SysPermissionModule selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysPermissionModule record);

    int updateByPrimaryKey(SysPermissionModule record);
}