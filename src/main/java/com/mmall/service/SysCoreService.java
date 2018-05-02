package com.mmall.service;


import com.google.common.collect.Lists;
import com.mmall.common.RequestHolder;
import com.mmall.dao.SysPermissionMapper;
import com.mmall.dao.SysRolePermissionMapper;
import com.mmall.dao.SysRoleUserMapper;
import com.mmall.model.SysPermission;
import com.mmall.model.SysUser;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
        // customized super admin rule, can be modified
        // it can be configured in configuration file
        // can also assign to a particular user or set a particular role
        SysUser sysUser = RequestHolder.getCurrentUser();
        if(sysUser.getUserEmail().contains("admin")) {
            return true;
        }
        return false;
    }

    // determine whether the particular user has permission to access a url
    // can be extended for further functionality
    public boolean hasUrlPermission(String url) {
        if(isSuperAdmin()) {
            return true;
        }
        List<SysPermission> permissionList = sysPermissionMapper.getPermissionsByUrl(url);
        if(CollectionUtils.isEmpty(permissionList)){
            return true;
        }

        List<SysPermission> userPermissionList = getCurrentUserPermissionList();
        Set<Integer> userPermissionIdSet = userPermissionList.stream()
                .map(permission -> permission.getId()).collect(Collectors.toSet());
        boolean hasValidPermission = false;
        // rule: as long as the user possess access right to one permission,
        // then he/she can access all permissions with same name
        for(SysPermission sysPermission : permissionList) {
            // determine whether a user possess the authorization right of a particular permission
            if(sysPermission == null || sysPermission.getPermissionStatus() != 1) { // invalid permission
                continue;
            }
            hasValidPermission = true;
            if(userPermissionIdSet.contains(sysPermission.getId())) {
                return true;
            }
        }
        if(!hasValidPermission) {
            return true;
        }
        return false;
    }

}
