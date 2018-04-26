package com.mmall.dto;

import com.google.common.collect.Lists;
import com.mmall.model.SysDept;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * permission
 * com.mmall.dto
 * Created by Zhang Chen
 * 4/22/2018
 */

@Getter
@Setter
@ToString
public class DeptLevelDto extends SysDept{

    private List<DeptLevelDto> deptList = Lists.newArrayList();

    public static DeptLevelDto adapt(SysDept sysDept) {
        DeptLevelDto dto = new DeptLevelDto();
        BeanUtils.copyProperties(sysDept, dto);
        return dto;
    }
}
