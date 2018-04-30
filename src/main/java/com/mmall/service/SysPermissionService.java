package com.mmall.service;

import com.google.common.base.Preconditions;
import com.mmall.beans.PageQuery;
import com.mmall.beans.PageResult;
import com.mmall.common.RequestHolder;
import com.mmall.dao.SysPermissionMapper;
import com.mmall.exception.ParamException;
import com.mmall.model.SysPermission;
import com.mmall.param.PermissionParam;
import com.mmall.util.BeanValidator;
import com.mmall.util.IpUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * permission
 * com.mmall.service
 * Created by Zhang Chen
 * 4/30/2018
 */

@Service
public class SysPermissionService {

    @Resource
    private SysPermissionMapper sysPermissionMapper;

    public void save(PermissionParam param) {
        BeanValidator.check(param);
        if(checkExist(param.getPermissionModuleId(), param.getPermissionName(), param.getId())) {
            throw new ParamException("当前权限模块下面存在相同名称的权限点");
        }
        SysPermission permission = SysPermission.builder()
                .permissionName(param.getPermissionName())
                .permissionModuleId(param.getPermissionModuleId())
                .permissionUrl(param.getPermissionUrl())
                .permissionType(param.getPermissionType())
                .permissionStatus(param.getPermissionStatus())
                .permissionSeq(param.getPermissionSeq())
                .remark(param.getRemark()).build();
        permission.setPermissionCode(generateCode());
        permission.setOperator(RequestHolder.getCurrentUser().getUserName());
        permission.setOperatorIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()));
        Date now = new Date();
        permission.setCreateTime(now);
        permission.setOperateTime(now);
        sysPermissionMapper.insertSelective(permission);
    }

    public void update(PermissionParam param) {
        BeanValidator.check(param);
        if(checkExist(param.getPermissionModuleId(), param.getPermissionName(), param.getId())) {
            throw new ParamException("当前权限模块下面存在相同名称的权限点");
        }
        SysPermission permissionBeforeUpdate = sysPermissionMapper.selectByPrimaryKey(param.getId());
        Preconditions.checkNotNull(permissionBeforeUpdate, "待更新的权限点不存在");
        SysPermission permissionAfterUpdate = SysPermission.builder()
                .id(param.getId())
                .permissionName(param.getPermissionName())
                .permissionModuleId(param.getPermissionModuleId())
                .permissionUrl(param.getPermissionUrl())
                .permissionType(param.getPermissionType())
                .permissionStatus(param.getPermissionStatus())
                .permissionSeq(param.getPermissionSeq())
                .remark(param.getRemark()).build();
        permissionAfterUpdate.setOperator(RequestHolder.getCurrentUser().getUserName());
        permissionAfterUpdate.setOperatorIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()));
        Date now = new Date();
        permissionAfterUpdate.setOperateTime(now);
        sysPermissionMapper.updateByPrimaryKeySelective(permissionAfterUpdate);
    }

    public boolean checkExist(int permissionModuleId, String name, Integer id) {
        return sysPermissionMapper.countByNameAndPermissionModuleId(permissionModuleId, name, id) > 0;
    }

    // generate permission code
    private String generateCode() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        return dateFormat.format(new Date()) + "_" + (int) Math.random() * 100;
    }

    public PageResult<SysPermission> getPageByPermissionModuleId(int permissionModuleId, PageQuery page) {
        BeanValidator.check(page);
        int count = sysPermissionMapper.countByPermissionModuleId(permissionModuleId);
        if(count > 0) {
            List<SysPermission> permissionList =  sysPermissionMapper.getPageByPermissionModuleId(permissionModuleId, page);
            return PageResult.<SysPermission>builder().data(permissionList).total(count).build();
        }
        return PageResult.<SysPermission>builder().build();
    }

}
