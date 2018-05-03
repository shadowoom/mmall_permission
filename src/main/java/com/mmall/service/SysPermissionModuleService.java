package com.mmall.service;

import com.google.common.base.Preconditions;
import com.mmall.common.RequestHolder;
import com.mmall.dao.SysPermissionMapper;
import com.mmall.dao.SysPermissionModuleMapper;
import com.mmall.exception.ParamException;
import com.mmall.model.SysPermissionModule;
import com.mmall.param.PermissionModuleParam;
import com.mmall.util.BeanValidator;
import com.mmall.util.IpUtil;
import com.mmall.util.LevelUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * permission
 * com.mmall.service
 * Created by Zhang Chen
 * 4/26/2018
 */
@Service
public class SysPermissionModuleService {

    @Resource
    private SysPermissionModuleMapper sysPermissionModuleMapper;

    @Resource
    private SysPermissionMapper sysPermissionMapper;

    @Resource
    private SysLogService sysLogService;

    public void save(PermissionModuleParam param) {
        BeanValidator.check(param);
        if(checkExist(param.getParentId(), param.getPermissionModuleName(), param.getId())) {
            throw new ParamException("同一层级下存在相同名称的权限模块");
        }
        SysPermissionModule permissionModule = SysPermissionModule.builder()
                .permissionModuleName(param.getPermissionModuleName())
                .parentId(param.getParentId())
                .permissionModuleSeq(param.getPermissionModuleSeq())
                .permissionModuleStatus(param.getPermissionModuleStatus())
                .remark(param.getRemark()).build();
        permissionModule.setPermissionModuleLevel(LevelUtil.calculateLevel(getLevel(param.getParentId()), param.getParentId()));
        permissionModule.setOperator(RequestHolder.getCurrentUser().getUserName());
        permissionModule.setOperatorIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()));
        Date now = new Date();
        permissionModule.setCreateTime(now);
        permissionModule.setOperateTime(now);
        sysPermissionModuleMapper.insertSelective(permissionModule);
        sysLogService.savePermissionModuleLog(null, permissionModule);
    }

    public void update(PermissionModuleParam param) {
        BeanValidator.check(param);
        if(checkExist(param.getParentId(), param.getPermissionModuleName(), param.getId())) {
            throw new ParamException("同一层级下存在相同名称的权限模块");
        }
        SysPermissionModule moduleBeforeUpdate = sysPermissionModuleMapper.selectByPrimaryKey(param.getId());
        Preconditions.checkNotNull(moduleBeforeUpdate, "待更新的权限模块不存在");
        SysPermissionModule moduleAfterUpdate = SysPermissionModule.builder()
                .id(param.getId())
                .permissionModuleName(param.getPermissionModuleName())
                .parentId(param.getParentId())
                .permissionModuleSeq(param.getPermissionModuleSeq())
                .permissionModuleStatus(param.getPermissionModuleStatus())
                .remark(param.getRemark()).build();
        moduleAfterUpdate.setPermissionModuleLevel(LevelUtil.calculateLevel(getLevel(param.getParentId()),
                param.getParentId()));
        moduleAfterUpdate.setOperator(RequestHolder.getCurrentUser().getUserName());
        moduleAfterUpdate.setOperatorIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()));
        Date now = new Date();
        moduleAfterUpdate.setOperateTime(now);
        updateWithChild(moduleBeforeUpdate, moduleAfterUpdate);
        sysLogService.savePermissionModuleLog(moduleBeforeUpdate, moduleAfterUpdate);
    }

    @Transactional
    void updateWithChild(SysPermissionModule moduleBeforeUpdate,
                         SysPermissionModule moduleAfterUpdate) {
        String newLevelPrefix = moduleAfterUpdate.getPermissionModuleLevel();
        String preLevelPrefix = moduleBeforeUpdate.getPermissionModuleLevel();
        if(!newLevelPrefix .equals(preLevelPrefix)) {
            List<SysPermissionModule> permissionModuleListList =
                    sysPermissionModuleMapper.getChildPermissionModuleListByLevel(preLevelPrefix);
            if(CollectionUtils.isNotEmpty(permissionModuleListList)) {
                for(SysPermissionModule permissionModule : permissionModuleListList) {
                    String level = permissionModule.getPermissionModuleLevel();
                    if(level.indexOf(preLevelPrefix) == 0) {
                        level = newLevelPrefix + level.substring(preLevelPrefix.length());
                        permissionModule.setPermissionModuleLevel(level);
                    }
                }
                sysPermissionModuleMapper.batchUpdateLevel(permissionModuleListList);
            }
        }
        sysPermissionModuleMapper.updateByPrimaryKeySelective(moduleAfterUpdate);
    }

    private boolean checkExist(Integer parentId, String permissionModuleName, Integer permissionModuleId) {
        return sysPermissionModuleMapper.countByNameAndParentId(parentId, permissionModuleName, permissionModuleId) > 0;
    }

    private  String getLevel(Integer permissionModuleId) {
        SysPermissionModule permissionModule = sysPermissionModuleMapper.selectByPrimaryKey(permissionModuleId);
        if(permissionModule == null)
            return null;
        return permissionModule.getPermissionModuleLevel();
    }

    public void delete(int permissionModuleId) {
        SysPermissionModule permissionModule = sysPermissionModuleMapper.selectByPrimaryKey(permissionModuleId);
        Preconditions.checkNotNull(permissionModule, "待删除的权限模块不存在，无法删除");
        if(sysPermissionModuleMapper.countByParentId(permissionModuleId) > 0) {
            throw new ParamException("当前权限模块下面有子模块，无法删除");
        }
        if(sysPermissionMapper.countByPermissionModuleId(permissionModuleId) > 0) {
            throw new ParamException("当前权限模块下面存在权限点，无法删除");
        }
        sysPermissionModuleMapper.deleteByPrimaryKey(permissionModuleId);
    }

}
