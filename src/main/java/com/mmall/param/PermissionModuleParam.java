package com.mmall.param;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * permission
 * com.mmall.param
 * Created by Zhang Chen
 * 4/26/2018
 */
@Getter
@Setter
@ToString
public class PermissionModuleParam {

    private Integer id;

    @NotBlank(message = "权限模块名称不能为空")
    @Length(min = 2, max = 20, message = "权限模块名称长度需要在2到20字之间")
    private String permissionModuleName;

    private Integer parentId = 0;

    @NotNull(message = "权限模块展示顺序不能为空")
    private Integer permissionModuleSeq;

    @NotNull(message = "权限模块状态不能为空")
    @Min(value = 0, message = "权限模块状态不合法")
    @Max(value = 1, message = "权限模块状态不合法")
    private Integer permissionModuleStatus;

    @Length(max = 200, message = "权限模块备注必须在200字以内")
    private String remark;

}
