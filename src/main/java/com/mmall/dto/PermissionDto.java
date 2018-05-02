package com.mmall.dto;


import com.mmall.model.SysPermission;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
@ToString
public class PermissionDto extends SysPermission{

    // is it selected by default?
    private boolean checked = false;

    // has the permission to operate?
    private boolean hasPermission = false;

    public static PermissionDto adapt(SysPermission permission) {
        PermissionDto dto = new PermissionDto();
        BeanUtils.copyProperties(permission, dto);
        return dto;
    }

}
