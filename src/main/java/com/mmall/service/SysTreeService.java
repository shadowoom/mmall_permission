package com.mmall.service;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.mmall.dao.SysDeptMapper;
import com.mmall.dto.DeptLevelDto;
import com.mmall.model.SysDept;
import com.mmall.model.SysPermissionModule;
import com.mmall.util.LevelUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * permission
 * com.mmall.service
 * Created by Zhang Chen
 * 4/22/2018
 */
@Service
public class SysTreeService {

    @Resource
    private SysDeptMapper sysDeptMapper;

    @Resource
    private SysPermissionModule sysPermissionModule;

    // department comparator based on sequence
    private Comparator<DeptLevelDto> deptSeqComparator = new Comparator<DeptLevelDto>() {
        @Override
        public int compare(DeptLevelDto o1, DeptLevelDto o2) {
            return o1.getDeptSeq() - o2.getDeptSeq();
        }
    };

    public List<DeptLevelDto> deptTree() {
        List<SysDept> deptList = sysDeptMapper.getAllDept();
        List<DeptLevelDto> dtoList = Lists.newArrayList();
        for(SysDept sysDept : deptList) {
            DeptLevelDto dto = DeptLevelDto.adapt(sysDept);
            dtoList.add(dto);
        }
        return deptListToTree(dtoList);
    }

    public List<DeptLevelDto> deptListToTree(List<DeptLevelDto> deptLevelList) {
        if(CollectionUtils.isEmpty(deptLevelList))
            return Lists.newArrayList();
        //level -> [dept1, dept2, ...]
        Multimap<String, DeptLevelDto> levelDeptMap = ArrayListMultimap.create();
        List<DeptLevelDto> rootList = Lists.newArrayList();
        for(DeptLevelDto dto : deptLevelList) {
            levelDeptMap.put(dto.getDeptLevel(), dto);
            if(LevelUtil.ROOT.equals(dto.getDeptLevel()))
                rootList.add(dto);
        }

        // 按照seq从小到大排序
        Collections.sort(rootList, new Comparator<DeptLevelDto>() {
            @Override
            public int compare(DeptLevelDto o1, DeptLevelDto o2) {
                return o1.getDeptSeq() - o2.getDeptSeq();
            }
        });
        // generate department tree via recursion
        transformDeptTree(deptLevelList, LevelUtil.ROOT, levelDeptMap);
        return rootList;
    }

    // level:0, 0, all 0 -> 0.1, 0.2
    // level:0.1
    // level:0.2
    public void transformDeptTree(List<DeptLevelDto> deptLevelList,
                                  String level, Multimap<String, DeptLevelDto> levelDeptMap) {
        //iterator through each item of the level
        for (int i = 0; i < deptLevelList.size(); i++) {
            //iterate through every elements
            DeptLevelDto deptLevelDto = deptLevelList.get(i);
            // handle data of that particular level
            String nextLevel = LevelUtil.calculateLevel(level, deptLevelDto.getId());
            // handle the next level
            List<DeptLevelDto> tempDeptList = (List<DeptLevelDto>) levelDeptMap.get(nextLevel);
            if(CollectionUtils.isNotEmpty(tempDeptList)) {
                //sort the list
                Collections.sort(tempDeptList, deptSeqComparator);
                // set the next-level dept
                deptLevelDto.setDeptList(tempDeptList);
                // recursively handle the next level
                transformDeptTree(tempDeptList, nextLevel, levelDeptMap);
            }
        }
    }

}
