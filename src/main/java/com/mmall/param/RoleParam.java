package com.mmall.param;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class RoleParam {

    private Integer id;

    @NotBlank(message = "角色名称不能为空")
    @Length(min = 2, max = 20, message = "角色名称长度需要在2到20之间")
    private String roleName;

    @Min(value = 1, message = "角色类型不合法")
    @Max(value = 2, message = "角色类型不合法")
    private Integer roleType = 1;

    @NotNull(message = "角色状态不可为空")
    @Min(value = 0, message = "角色状态不合法")
    @Max(value = 1, message = "角色状态不合法")
    private Integer roleStatus;

//    @NotNull(message = "必须指定角色的展示顺序")
//    private Integer roleSeq;

    @Length(min = 0, max = 200, message = "角色备注长度需要在200个字符以内")
    private String remark;

}
