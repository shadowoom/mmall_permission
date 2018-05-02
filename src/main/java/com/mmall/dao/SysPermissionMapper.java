package com.mmall.dao;

import com.mmall.beans.PageQuery;
import com.mmall.model.SysPermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysPermissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysPermission record);

    int insertSelective(SysPermission record);

    SysPermission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysPermission record);

    int updateByPrimaryKey(SysPermission record);

    int countByPermissionModuleId(@Param("permissionModuleId") int permissionModuleId);

    List<SysPermission> getPageByPermissionModuleId(@Param("permissionModuleId") int permissionModuleId,
                                                    @Param("page") PageQuery page);

    int countByNameAndPermissionModuleId(@Param("permissionModuleId") int permissionModuleId,
                                         @Param("permissionName") String permissionName, @Param("id") Integer id);

    List<SysPermission> getAllPermissions();

    List<SysPermission> getByIdList(@Param("idList") List<Integer> idList);

    List<SysPermission> getPermissionsByUrl(@Param("url") String url);

}