package com.mmall.service;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.mmall.dao.SysDeptMapper;
import com.mmall.dao.SysPermissionMapper;
import com.mmall.dao.SysPermissionModuleMapper;
import com.mmall.dto.DeptLevelDto;
import com.mmall.dto.PermissionDto;
import com.mmall.dto.PermissionModuleLevelDto;
import com.mmall.model.SysDept;
import com.mmall.model.SysPermission;
import com.mmall.model.SysPermissionModule;
import com.mmall.util.LevelUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

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
    private SysPermissionModuleMapper sysPermissionModuleMapper;

    @Resource
    private SysCoreService sysCoreService;

    @Resource
    private SysPermissionMapper sysPermissionMapper;

    // department comparator based on sequence
    private Comparator<DeptLevelDto> deptSeqComparator = new Comparator<DeptLevelDto>() {
        @Override
        public int compare(DeptLevelDto o1, DeptLevelDto o2) {
            return o1.getDeptSeq() - o2.getDeptSeq();
        }
    };

    // permission module comparator based on sequence
    private Comparator<PermissionModuleLevelDto> permissionModuleSeqComparator = new Comparator<PermissionModuleLevelDto>() {
        @Override
        public int compare(PermissionModuleLevelDto o1, PermissionModuleLevelDto o2) {
            return o1.getPermissionModuleSeq() - o2.getPermissionModuleSeq();
        }
    };

    // permission dto comparator based on sequence
    private Comparator<PermissionDto> permissionSeqComparator = new Comparator<PermissionDto>() {
        @Override
        public int compare(PermissionDto o1, PermissionDto o2) {
            return o1.getPermissionSeq() - o2.getPermissionSeq();
        }
    };

    public List<PermissionModuleLevelDto> userPermissionTree(int userId) {
        List<SysPermission> userPermissionList = sysCoreService.getUserPermissionList(userId);
        List<PermissionDto> permissionDtoList = Lists.newArrayList();
        for(SysPermission permission : userPermissionList) {
            PermissionDto dto = PermissionDto.adapt(permission);
            dto.setHasPermission(true);
            dto.setChecked(true);
            permissionDtoList.add(dto);
        }
        return permissionListToTree(permissionDtoList);
    }

    public List<PermissionModuleLevelDto> roleTree(int roleId) {
        // 1. the permissions assigned to the current user
        List<SysPermission> userPermissionList = sysCoreService.getCurrentUserPermissionList();
        // 2. the permissions assigned to the current role
        List<SysPermission> rolePermissionList = sysCoreService.getRolePermissionList(roleId);
        // 3. all the permissions in the system
        List<PermissionDto> permissionDtoList = Lists.newArrayList();
        List<SysPermission> allPermissionsList = sysPermissionMapper.getAllPermissions();


        Set<Integer> userPermissionIdSet = userPermissionList.stream()
                .map(sysPermission -> sysPermission.getId()).collect(Collectors.toSet());
        Set<Integer> rolePermissionIdSet = rolePermissionList.stream()
                .map(sysPermission -> sysPermission.getId()).collect(Collectors.toSet());


        for(SysPermission permission : allPermissionsList) {
            PermissionDto dto = PermissionDto.adapt(permission);
            if(userPermissionIdSet.contains(permission.getId())) {
                dto.setHasPermission(true);
            }
            if(rolePermissionIdSet.contains(permission.getId())) {
                dto.setChecked(true);
            }
            permissionDtoList.add(dto);
        }
        return permissionListToTree(permissionDtoList);
    }

    public List<PermissionModuleLevelDto> permissionListToTree(List<PermissionDto> permissionDtoList) {
        if(CollectionUtils.isEmpty(permissionDtoList)) {
            return Lists.newArrayList();
        }

        // permission module tree
        List<PermissionModuleLevelDto> permissionModuleLevelDtoList = permissionModuleTree();

        // permission module id -> permission 1, permission 2, etc
        Multimap<Integer, PermissionDto> modulePermissionMap = ArrayListMultimap.create();
        for(PermissionDto permissionDto : permissionDtoList) {
            if(permissionDto.getPermissionStatus() == 1) {
                modulePermissionMap.put(permissionDto.getPermissionModuleId(), permissionDto);
            }
        }

        bindPermissionsWithOrder(permissionModuleLevelDtoList, modulePermissionMap);
        return permissionModuleLevelDtoList;
    }

    // recursive add bind permissions to the corresponding permission module
    public void bindPermissionsWithOrder(List<PermissionModuleLevelDto> permissionModuleLevelDtoList,
                                         Multimap<Integer, PermissionDto> modulePermissionMap) {
        if(CollectionUtils.isEmpty(permissionModuleLevelDtoList)) {
            return;
        }
        for(PermissionModuleLevelDto dto : permissionModuleLevelDtoList) {
            List<PermissionDto> permissionDtoList = (List<PermissionDto>) modulePermissionMap.get(dto.getId());
            if(CollectionUtils.isNotEmpty(permissionDtoList)) {
                Collections.sort(permissionDtoList, permissionSeqComparator);
                dto.setPermissionList(permissionDtoList);
            }
            bindPermissionsWithOrder(dto.getPermissionModuleList(), modulePermissionMap);
        }
    }

    // generate permission module tree
    public List<PermissionModuleLevelDto> permissionModuleTree() {
        List<SysPermissionModule> permissionModuleList = sysPermissionModuleMapper.getAllPermissionModule();
        List<PermissionModuleLevelDto> dtoList = Lists.newArrayList();
        for(SysPermissionModule permissionModule : permissionModuleList) {
            dtoList.add(PermissionModuleLevelDto.adapt(permissionModule));
        }
        return permissionModuleListToTree(dtoList);
    }

    public List<PermissionModuleLevelDto> permissionModuleListToTree(List<PermissionModuleLevelDto> dtoList) {
        if(CollectionUtils.isEmpty(dtoList)) {
            return Lists.newArrayList();
        }
        //level -> [permissionModule1, permissionModule2, ...] Map<String, List<Object>>
        Multimap<String, PermissionModuleLevelDto> levelPermissionModuleMap = ArrayListMultimap.create();
        List<PermissionModuleLevelDto> rootList = Lists.newArrayList();

        for(PermissionModuleLevelDto dto : dtoList) {
            levelPermissionModuleMap.put(dto.getPermissionModuleLevel(), dto);
            if(LevelUtil.ROOT.equals(dto.getPermissionModuleLevel()))
                rootList.add(dto);
        }
        // 按照seq从小到大排序
        Collections.sort(rootList, permissionModuleSeqComparator);
        transformPermissionModuleTree(rootList, LevelUtil.ROOT, levelPermissionModuleMap);
        return rootList;
    }

    // level:0, 0, all 0 -> 0.1, 0.2
    // level:0.1
    // level:0.2
    // iteratively generate the next-level list of permission modules of a particular module
    public void transformPermissionModuleTree(List<PermissionModuleLevelDto> permissionModuleLevelList,
                                              String level, Multimap<String, PermissionModuleLevelDto> levelPermissionModuleMap) {
        for(int i = 0; i < permissionModuleLevelList.size(); i++) {
            PermissionModuleLevelDto dto = permissionModuleLevelList.get(i);
            String nextLevel = LevelUtil.calculateLevel(level, dto.getId());
            List<PermissionModuleLevelDto> tempList = (List<PermissionModuleLevelDto>) levelPermissionModuleMap.get(nextLevel);
            if(CollectionUtils.isNotEmpty(tempList)) {
                Collections.sort(tempList, permissionModuleSeqComparator);
                dto.setPermissionModuleList(tempList);
                transformPermissionModuleTree(tempList, nextLevel, levelPermissionModuleMap);
            }
        }
    }


    // generate department tree
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
        Collections.sort(rootList, deptSeqComparator);
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
