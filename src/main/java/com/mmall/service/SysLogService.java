package com.mmall.service;

import com.google.common.base.Preconditions;
import com.mmall.beans.LogType;
import com.mmall.beans.PageQuery;
import com.mmall.beans.PageResult;
import com.mmall.common.RequestHolder;
import com.mmall.dao.*;
import com.mmall.dto.SearchLogDto;
import com.mmall.exception.ParamException;
import com.mmall.model.*;
import com.mmall.param.SearchLogParam;
import com.mmall.util.BeanValidator;
import com.mmall.util.IpUtil;
import com.mmall.util.JsonMapper;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class SysLogService {

    @Resource
    private SysLogMapper sysLogMapper;

    @Resource
    private SysDeptMapper sysDeptMapper;

    @Resource
    private SysUserMapper sysUserMapper;

    @Resource
    private SysPermissionModuleMapper sysPermissionModuleMapper;

    @Resource
    private SysPermissionMapper sysPermissionMapper;

    @Resource
    private SysRoleMapper sysRoleMapper;

    @Resource
    private SysRolePermissionService sysRolePermissionService;

    @Resource
    private SysRoleUserService sysRoleUserService;

    public void recoverLig(int id) {
        SysLogWithBLOBs sysLogWithBLOBs = sysLogMapper.selectByPrimaryKey(id);
        Preconditions.checkNotNull(sysLogWithBLOBs, "待还原的记录不存在");
        switch (sysLogWithBLOBs.getType()){
            case LogType.TYPE_DEPT:
                SysDept beforeDept = sysDeptMapper.selectByPrimaryKey(sysLogWithBLOBs.getTargetId());
                Preconditions.checkNotNull(beforeDept,"待还原的部门不存在");
                if(StringUtils.isBlank(sysLogWithBLOBs.getNewValue()) || StringUtils.isBlank(sysLogWithBLOBs.getOldValue())) {
                    throw new ParamException("新增和删除操作不做还原");
                }
                SysDept afterDept = JsonMapper.string2Obj(sysLogWithBLOBs.getOldValue(),
                        new TypeReference<SysDept>() {});
                afterDept.setOperator(RequestHolder.getCurrentUser().getUserName());
                afterDept.setOperatorIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()));
                afterDept.setOperateTime(new Date());
                sysDeptMapper.updateByPrimaryKeySelective(afterDept);
                saveDeptLog(beforeDept, afterDept);
                break;
            case LogType.TYPE_USER:
                SysUser beforeUser = sysUserMapper.selectByPrimaryKey(sysLogWithBLOBs.getTargetId());
                Preconditions.checkNotNull(beforeUser,"待还原的用户不存在");
                if(StringUtils.isBlank(sysLogWithBLOBs.getNewValue()) || StringUtils.isBlank(sysLogWithBLOBs.getOldValue())) {
                    throw new ParamException("新增和删除操作不做还原");
                }
                SysUser afterUser = JsonMapper.string2Obj(sysLogWithBLOBs.getOldValue(),
                        new TypeReference<SysUser>() {});
                afterUser.setOperator(RequestHolder.getCurrentUser().getUserName());
                afterUser.setOperatorIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()));
                afterUser.setOperateTime(new Date());
                sysUserMapper.updateByPrimaryKeySelective(afterUser);
                saveUserLog(beforeUser, afterUser);
                break;
            case LogType.TYPE_PERMISSION_MODULE:
                SysPermissionModule beforeModule = sysPermissionModuleMapper.selectByPrimaryKey(sysLogWithBLOBs.getTargetId());
                Preconditions.checkNotNull(beforeModule,"待还原的权限模块不存在");
                if(StringUtils.isBlank(sysLogWithBLOBs.getNewValue()) || StringUtils.isBlank(sysLogWithBLOBs.getOldValue())) {
                    throw new ParamException("新增和删除操作不做还原");
                }
                SysPermissionModule afterModule = JsonMapper.string2Obj(sysLogWithBLOBs.getOldValue(),
                        new TypeReference<SysPermissionModule>() {});
                afterModule.setOperator(RequestHolder.getCurrentUser().getUserName());
                afterModule.setOperatorIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()));
                afterModule.setOperateTime(new Date());
                sysPermissionModuleMapper.updateByPrimaryKeySelective(afterModule);
                savePermissionModuleLog(beforeModule, afterModule);
                break;
            case LogType.TYPE_PERMISSION:
                SysPermission beforePermission = sysPermissionMapper.selectByPrimaryKey(sysLogWithBLOBs.getTargetId());
                Preconditions.checkNotNull(beforePermission,"待还原的权限点不存在");
                if(StringUtils.isBlank(sysLogWithBLOBs.getNewValue()) || StringUtils.isBlank(sysLogWithBLOBs.getOldValue())) {
                    throw new ParamException("新增和删除操作不做还原");
                }
                SysPermission afterPermission = JsonMapper.string2Obj(sysLogWithBLOBs.getOldValue(),
                        new TypeReference<SysPermission>() {});
                afterPermission.setOperator(RequestHolder.getCurrentUser().getUserName());
                afterPermission.setOperatorIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()));
                afterPermission.setOperateTime(new Date());
                sysPermissionMapper.updateByPrimaryKeySelective(afterPermission);
                savePermissionLog(beforePermission, afterPermission);
                break;
            case LogType.TYPE_ROLE:
                SysRole beforeRole = sysRoleMapper.selectByPrimaryKey(sysLogWithBLOBs.getTargetId());
                Preconditions.checkNotNull(beforeRole,"待还原的角色不存在");
                if(StringUtils.isBlank(sysLogWithBLOBs.getNewValue()) || StringUtils.isBlank(sysLogWithBLOBs.getOldValue())) {
                    throw new ParamException("新增和删除操作不做还原");
                }
                SysRole afterRole = JsonMapper.string2Obj(sysLogWithBLOBs.getOldValue(),
                        new TypeReference<SysRole>() {});
                afterRole.setOperator(RequestHolder.getCurrentUser().getUserName());
                afterRole.setOperatorIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()));
                afterRole.setOperateTime(new Date());
                sysRoleMapper.updateByPrimaryKeySelective(afterRole);
                saveRoleLog(beforeRole, afterRole);
                break;
            case LogType.TYPE_ROLE_PERMISSION:
                SysRole rolePermission = sysRoleMapper.selectByPrimaryKey(sysLogWithBLOBs.getTargetId());
                Preconditions.checkNotNull(rolePermission,"待还原的角色不存在");
                sysRolePermissionService.changeRolePermissions(sysLogWithBLOBs.getTargetId(),
                        JsonMapper.string2Obj(sysLogWithBLOBs.getOldValue(), new TypeReference<List<Integer>>() {}));
                break;
            case LogType.TYPE_ROLE_USER:
                SysRole roleUser = sysRoleMapper.selectByPrimaryKey(sysLogWithBLOBs.getTargetId());
                Preconditions.checkNotNull(roleUser,"待还原的角色不存在");
                sysRoleUserService.changeRoleUsers(sysLogWithBLOBs.getTargetId(),
                        JsonMapper.string2Obj(sysLogWithBLOBs.getOldValue(), new TypeReference<List<Integer>>() {}));
                break;
            default:
                break;
        }
    }

    public PageResult<SysLogWithBLOBs> searchPageList(SearchLogParam param, PageQuery page) {
        BeanValidator.check(page);
        SearchLogDto dto = new SearchLogDto();
        dto.setType(param.getType());
        if(StringUtils.isNotBlank(param.getBeforeSeg())) {
            dto.setBeforeSeg("%" + param.getBeforeSeg() + "%");
        }
        if(StringUtils.isNotBlank(param.getAfterSeg())) {
            dto.setAfterSeg("%" + param.getAfterSeg() + "%");
        }
        if(StringUtils.isNotBlank(param.getOperator())) {
            dto.setOperator("%" + param.getOperator() + "%");
        }
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if (StringUtils.isNotBlank(param.getFromTime())) {
                dto.setFromTime(dateFormat.parse(param.getFromTime()));
            }
            if (StringUtils.isNotBlank(param.getToTime())) {
                dto.setToTime(dateFormat.parse(param.getToTime()));
            }
        }
        catch (Exception e) {
            throw new ParamException("传入的日期格式有误, 正确格式为 yyyy-MM-dd HH:mm:ss");
        }
        int count = sysLogMapper.countBySearchDto(dto);
        if(count > 0) {
            List<SysLogWithBLOBs> logList = sysLogMapper.getPageListBySearchDto(dto,page);
            return PageResult.<SysLogWithBLOBs>builder().total(count).data(logList).build();
        }
        return PageResult.<SysLogWithBLOBs>builder().build();
    }

    public void saveDeptLog(SysDept before, SysDept after) {
        SysLogWithBLOBs sysLog = new SysLogWithBLOBs();
        sysLog.setType(LogType.TYPE_DEPT);
        sysLog.setTargetId(after == null ? before.getId() : after.getId());
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

    public void saveUserLog(SysUser before, SysUser after) {
        SysLogWithBLOBs sysLog = new SysLogWithBLOBs();
        sysLog.setType(LogType.TYPE_USER);
        sysLog.setTargetId(after == null ? before.getId() : after.getId());
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

    public void savePermissionLog(SysPermission before, SysPermission after) {
        SysLogWithBLOBs sysLog = new SysLogWithBLOBs();
        sysLog.setType(LogType.TYPE_PERMISSION);
        sysLog.setTargetId(after == null ? before.getId() : after.getId());
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

    public void saveRoleLog(SysRole before, SysRole after) {
        SysLogWithBLOBs sysLog = new SysLogWithBLOBs();
        sysLog.setType(LogType.TYPE_ROLE);
        sysLog.setTargetId(after == null ? before.getId() : after.getId());
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

    public void savePermissionModuleLog(SysPermissionModule before, SysPermissionModule after) {
        SysLogWithBLOBs sysLog = new SysLogWithBLOBs();
        sysLog.setType(LogType.TYPE_PERMISSION_MODULE);
        sysLog.setTargetId(after == null ? before.getId() : after.getId());
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
