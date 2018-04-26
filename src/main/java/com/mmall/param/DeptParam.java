package com.mmall.param;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * permission
 * com.mmall.param
 * Created by Zhang Chen
 * 4/22/2018
 */

@Getter
@Setter
@ToString
public class DeptParam {

    private Integer id;

    @NotBlank(message = "部门名称不可以为空")
    @Length(max = 15, min = 2, message = "部门名称需要在2-15个字之间")
    private String deptName;

    private Integer parentId = 0;

    @NotNull(message = "展示顺序不可以为空")
    private Integer deptSeq;

    @Length(max = 150, message = "备注长度需要在150字之内")
    private String remark;

}
