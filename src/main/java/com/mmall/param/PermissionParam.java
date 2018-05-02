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
 * 4/30/2018
 */

@Getter
@Setter
@ToString
public class PermissionParam {

    private Integer id;

    @NotBlank(message = "权限点名称不能为空")
    @Length(min = 2, max = 20, message = "权限点名称长度需要在2到20之间")
    private String permissionName;

    @NotNull(message = "必须指定权限模块")
    private Integer permissionModuleId;

    @Length(min = 6, max = 100, message = "权限点URL长度必须在6-100个字符之间")
    private String permissionUrl;

    @NotNull(message = "必须指定权限点类型")
    @Min(value = 0, message = "权限点类型不合法")
    @Max(value = 3, message = "权限点类型不合法")
    private Integer permissionType;

    @NotNull(message = "必须指定权限点类型")
    @Min(value = 0, message = "权限点状态不合法")
    @Max(value = 1, message = "权限点状态不合法")
    private Integer permissionStatus;

    @NotNull(message = "必须指定权限点的展示顺序")
    private Integer permissionSeq;

    @Length(min = 0, max = 200, message = "权限点备注长度需要在200个字符以内")
    private String remark;

}
