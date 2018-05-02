package com.mmall.service;


import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.mmall.common.RequestHolder;
import com.mmall.dao.SysRoleMapper;
import com.mmall.dao.SysRolePermissionMapper;
import com.mmall.dao.SysRoleUserMapper;
import com.mmall.exception.ParamException;
import com.mmall.model.SysRole;
import com.mmall.param.RoleParam;
import com.mmall.util.BeanValidator;
import com.mmall.util.IpUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class SysRoleService {

    @Resource
    private SysRoleMapper sysRoleMapper;

    @Resource
    private SysRoleUserMapper sysRoleUserMapper;

    @Resource
    private SysRolePermissionMapper sysRolePermissionMapper;

    public void save(RoleParam param) {
        BeanValidator.check(param);
        if(checkExist(param.getRoleName(), param.getId())) {
            throw new ParamException("角色名称已经存在");
        }
        SysRole role = SysRole.builder()
                .roleName(param.getRoleName())
                .roleType(param.getRoleType())
                .roleStatus(param.getRoleStatus())
                .remark(param.getRemark()).build();
        role.setOperator(RequestHolder.getCurrentUser().getUserName());
        role.setOperatorIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()));
        Date now = new Date();
        role.setCreateTime(now);
        role.setOperateTime(now);
        sysRoleMapper.insertSelective(role);
    }

    public void update(RoleParam param) {
        BeanValidator.check(param);
        if(checkExist(param.getRoleName(), param.getId())) {
            throw new ParamException("角色名称已经存在");
        }
        SysRole roleBeforeUpdate = sysRoleMapper.selectByPrimaryKey(param.getId());
        Preconditions.checkNotNull(roleBeforeUpdate, "待更新的角色不存在");
        SysRole roleAfterUpdate = SysRole.builder()
                .id(param.getId())
                .roleName(param.getRoleName())
                .roleType(param.getRoleType())
                .roleStatus(param.getRoleStatus())
                .remark(param.getRemark()).build();
        roleAfterUpdate.setOperator(RequestHolder.getCurrentUser().getUserName());
        roleAfterUpdate.setOperatorIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()));
        Date now = new Date();
        roleAfterUpdate.setOperateTime(now);
        sysRoleMapper.updateByPrimaryKeySelective(roleAfterUpdate);
    }

    public List<SysRole> getAllRoles() {
        return sysRoleMapper.getAllRoles();
    }

    private boolean checkExist(String roleName, Integer id) {
        return sysRoleMapper.countByRoleName(roleName, id) > 0;
    }

    public List<SysRole> getRoleListByUserId(int userId) {
        List<Integer> roleIdList = sysRoleUserMapper.getRoleIdListByUserId(userId);
        if(CollectionUtils.isEmpty(roleIdList)) {
            return Lists.newArrayList();
        }
        return sysRoleMapper.getByIdList(roleIdList);
    }

    public List<SysRole> getRoleListByPermissionId(int permissionId) {
        List<Integer> roleIdList = sysRolePermissionMapper.getRoleIdListByPermissionId(permissionId);
        if(CollectionUtils.isEmpty(roleIdList)){
            return Lists.newArrayList();
        }
        return sysRoleMapper.getByIdList(roleIdList);
    }
}
