package com.mmall.service;


import com.google.common.collect.Lists;
import com.mmall.common.RequestHolder;
import com.mmall.dao.SysPermissionMapper;
import com.mmall.dao.SysRolePermissionMapper;
import com.mmall.dao.SysRoleUserMapper;
import com.mmall.model.SysPermission;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysCoreService {

    @Resource
    private SysPermissionMapper sysPermissionMapper;

    @Resource
    private SysRoleUserMapper sysRoleUserMapper;

    @Resource
    private SysRolePermissionMapper sysRolePermissionMapper;

    public List<SysPermission> getCurrentUserPermissionList() {
        int userId = RequestHolder.getCurrentUser().getId();
        return getUserPermissionList(userId);
    }

    public List<SysPermission> getRolePermissionList(int roleId) {
        List<Integer> permissionIdList = sysRolePermissionMapper.
                getPermissionIdListByRoleIdList(Lists.<Integer>newArrayList(roleId));
        if(CollectionUtils.isEmpty(permissionIdList)){
            return Lists.newArrayList();
        }
        return sysPermissionMapper.getByIdList(permissionIdList);
    }

    public List<SysPermission> getUserPermissionList(int userId) {
        if(isSuperAdmin()) {
            return sysPermissionMapper.getAllPermissions();
        }
        List<Integer> userRoleIdList = sysRoleUserMapper.getRoleIdListByUserId(userId);
        if(CollectionUtils.isEmpty(userRoleIdList)) {
            return Lists.newArrayList();
        }
        List<Integer> userPermissionIdList = sysRolePermissionMapper.getPermissionIdListByRoleIdList(userRoleIdList);
        if(CollectionUtils.isEmpty(userPermissionIdList)){
            return Lists.newArrayList();
        }
        return sysPermissionMapper.getByIdList(userPermissionIdList);
    }

    public boolean isSuperAdmin() {
        return true;
    }

}
