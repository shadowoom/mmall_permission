package com.mmall.dto;

import com.google.common.collect.Lists;
import com.mmall.model.SysPermissionModule;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.BeanUtils;

import java.security.Permission;
import java.util.List;

/**
 * permission
 * com.mmall.dto
 * Created by Zhang Chen
 * 4/26/2018
 */

@Getter
@Setter
@ToString
public class PermissionModuleLevelDto extends SysPermissionModule {

    private List<PermissionModuleLevelDto> permissionModuleList = Lists.newArrayList();

    private List<PermissionDto> permissionList = Lists.newArrayList();

    public static PermissionModuleLevelDto adapt(SysPermissionModule sysPermissionModule) {
        PermissionModuleLevelDto dto = new PermissionModuleLevelDto();
        BeanUtils.copyProperties(sysPermissionModule, dto);
        return dto;
    }



}
