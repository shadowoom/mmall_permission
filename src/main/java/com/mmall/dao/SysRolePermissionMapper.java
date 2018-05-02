package com.mmall.dao;

import com.mmall.model.SysRolePermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysRolePermissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysRolePermission record);

    int insertSelective(SysRolePermission record);

    SysRolePermission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysRolePermission record);

    int updateByPrimaryKey(SysRolePermission record);

    List<Integer> getPermissionIdListByRoleIdList(@Param("roleIdList") List<Integer> roleIdList);

    void deleteByRoleId(@Param("roleId") int roleId);

    void batchInsert(@Param("rolePermissionList") List<SysRolePermission> rolePermissionList);

    List<Integer> getRoleIdListByPermissionId(@Param("permissionId") int permissionId);
}