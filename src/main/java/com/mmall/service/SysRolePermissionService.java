package com.mmall.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.mmall.beans.LogType;
import com.mmall.common.RequestHolder;
import com.mmall.dao.SysLogMapper;
import com.mmall.dao.SysRolePermissionMapper;
import com.mmall.model.SysLogWithBLOBs;
import com.mmall.model.SysPermissionModule;
import com.mmall.model.SysRolePermission;
import com.mmall.util.IpUtil;
import com.mmall.util.JsonMapper;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class SysRolePermissionService {

    @Resource
    private SysRolePermissionMapper sysRolePermissionMapper;


    @Resource
    private SysLogMapper sysLogMapper;

    public void changeRolePermissions(Integer roleId, List<Integer> permissionIdList) {
        List<Integer> originalPermissionIdList =
                sysRolePermissionMapper.getPermissionIdListByRoleIdList(Lists.newArrayList(roleId));
        if(originalPermissionIdList.size() == permissionIdList.size()) {
            Set<Integer> originalPermissionIdSet = Sets.newHashSet(originalPermissionIdList);
            Set<Integer> permissionIdSet = Sets.newHashSet(permissionIdList);
            originalPermissionIdSet.removeAll(permissionIdSet);
            if(CollectionUtils.isEmpty(originalPermissionIdSet)){
                return;
            }
        }
        updateRolePermissions(roleId, permissionIdList);
        saveRolePermissionLog(roleId, originalPermissionIdList, permissionIdList);
    }

    @Transactional
    void updateRolePermissions(int roleId, List<Integer> permissionIdList) {
        sysRolePermissionMapper.deleteByRoleId(roleId);
        if(CollectionUtils.isEmpty(permissionIdList)){
            return;
        }
        List<SysRolePermission> rolePermissionList = Lists.newArrayList();
        for(Integer permissionId : permissionIdList) {
            SysRolePermission rolePermission = SysRolePermission.builder()
                    .roleId(roleId).permissionId(permissionId)
                    .operator(RequestHolder.getCurrentUser().getUserName())
                    .operatorIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()))
                    .build();
            Date date = new Date();
            rolePermission.setCreateTime(date);
            rolePermission.setOperateTime(date);
            rolePermissionList.add(rolePermission);
        }
        sysRolePermissionMapper.batchInsert(rolePermissionList);

    }

    private void saveRolePermissionLog(int roleId, List<Integer> before, List<Integer> after) {
        SysLogWithBLOBs sysLog = new SysLogWithBLOBs();
        sysLog.setType(LogType.TYPE_ROLE_PERMISSION);
        sysLog.setTargetId(roleId);
        sysLog.setOldValue(before == null ? "" : JsonMapper.obj2String(before));
        sysLog.setNewValue(after == null ? "" : JsonMapper.obj2String(after));
        sysLog.setOperator(RequestHolder.getCurrentUser().getUserName());
        sysLog.setOperatorIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()));
        Date date = new Date();
        sysLog.setCreateTime(date);
        sysLog.setOperateTime(date);
        sysLog.setStatus(1);
        sysLogMapper.insertSelective(sysLog);
    }

}
