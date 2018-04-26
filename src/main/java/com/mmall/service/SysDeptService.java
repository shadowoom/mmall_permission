package com.mmall.service;

import com.google.common.base.Preconditions;
import com.mmall.common.RequestHolder;
import com.mmall.dao.SysDeptMapper;
import com.mmall.exception.ParamException;
import com.mmall.model.SysDept;
import com.mmall.param.DeptParam;
import com.mmall.util.BeanValidator;
import com.mmall.util.IpUtil;
import com.mmall.util.LevelUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * permission
 * com.mmall.service
 * Created by Zhang Chen
 * 4/22/2018
 */

@Service
public class SysDeptService {

    @Resource
    private SysDeptMapper sysDeptMapper;

    public void save(DeptParam param) {
        BeanValidator.check(param);
        if(checkExist(param.getParentId(), param.getDeptName(), param.getId())) {
            throw new ParamException("同一层级下存在相同名称的部门");
        }
        SysDept dept = SysDept.builder().deptName(param.getDeptName()).parentId(param.getParentId())
                .deptSeq(param.getDeptSeq()).remark(param.getRemark()).build();
        dept.setDeptLevel(LevelUtil.calculateLevel(getLevel(param.getParentId()), param.getParentId()));
        dept.setOperator(RequestHolder.getCurrentUser().getUserName());
        dept.setOperatorIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()));
        Date now = new Date();
        dept.setCreateTime(now);
        dept.setOperateTime(now);
        sysDeptMapper.insertSelective(dept);
    }

    public void update(DeptParam param) {
        BeanValidator.check(param);
        if(checkExist(param.getParentId(), param.getDeptName(), param.getId())) {
            throw new ParamException("同一层级下存在相同名称的部门");
        }
        SysDept deptBeforeUpdate = sysDeptMapper.selectByPrimaryKey(param.getId());
        Preconditions.checkNotNull(deptBeforeUpdate, "待更新的部门不存在");
        SysDept deptAfterUpdate = SysDept.builder().id(param.getId()).deptName(param.getDeptName()).parentId(param.getParentId())
                .deptSeq(param.getDeptSeq()).remark(param.getRemark()).build();
        deptAfterUpdate.setDeptLevel(LevelUtil.calculateLevel(getLevel(param.getParentId()), param.getParentId()));
        deptAfterUpdate.setOperator(RequestHolder.getCurrentUser().getUserName());
        deptAfterUpdate.setOperatorIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()));
        Date now = new Date();
        deptAfterUpdate.setOperateTime(now);
        updateWithChild(deptBeforeUpdate, deptAfterUpdate);
    }

    @Transactional
    private void updateWithChild(SysDept deptBeforeUpdate, SysDept deptAfterUpdate) {
        String newLevelPrefix = deptAfterUpdate.getDeptLevel();
        String preLevelPrefix = deptBeforeUpdate.getDeptLevel();
        if(!newLevelPrefix .equals(preLevelPrefix)) {
            List<SysDept> deptList = sysDeptMapper.getChildDeptListByLevel(preLevelPrefix);
            if(CollectionUtils.isNotEmpty(deptList)) {
                for(SysDept dept : deptList) {
                    String level = dept.getDeptLevel();
                    if(level.indexOf(preLevelPrefix) == 0) {
                        level = newLevelPrefix + level.substring(preLevelPrefix.length());
                        dept.setDeptLevel(level);
                    }
                }
                sysDeptMapper.batchUpdateLevel(deptList);
            }
        }
        sysDeptMapper.updateByPrimaryKeySelective(deptAfterUpdate);
    }

    private boolean checkExist(Integer parentId, String deptName, Integer deptId) {
        return sysDeptMapper.countByNameAndParentId(parentId, deptName, deptId) > 0;
    }

    private  String getLevel(Integer deptId) {
        SysDept dept = sysDeptMapper.selectByPrimaryKey(deptId);
        if(dept == null)
            return null;
        return dept.getDeptLevel();
    }
}
