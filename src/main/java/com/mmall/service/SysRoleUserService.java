package com.mmall.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.mmall.common.RequestHolder;
import com.mmall.dao.SysRoleUserMapper;
import com.mmall.dao.SysUserMapper;
import com.mmall.model.SysRoleUser;
import com.mmall.model.SysUser;
import com.mmall.util.IpUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class SysRoleUserService {

    @Resource
    private SysRoleUserMapper sysRoleUserMapper;

    @Resource
    private SysUserMapper sysUserMapper;

    public List<SysUser> getListByRoleId(int roleId) {
        List<Integer> userIdList = sysRoleUserMapper.getUserIdListByRoleId(roleId);
        if(CollectionUtils.isEmpty(userIdList)) {
            return Lists.newArrayList();
        }
        return sysUserMapper.getUsersByIdList(userIdList);
    }

    public void changeRoleUsers(int roleId, List<Integer> userIdList) {
        List<Integer> originalUserIdList = sysRoleUserMapper.getUserIdListByRoleId(roleId);
        if(userIdList.size() == originalUserIdList.size()) {
            Set<Integer> userIdSet = Sets.newHashSet(userIdList);
            Set<Integer> originalUserIdSet = Sets.newHashSet(originalUserIdList);
            originalUserIdSet.removeAll(userIdSet);
            if(CollectionUtils.isEmpty(originalUserIdSet)){
                return;
            }
        }
        updateRoleUsers(roleId, userIdList);
    }

    @Transactional
    private void updateRoleUsers(int roleId, List<Integer> userIdList) {
        sysRoleUserMapper.deleteByRoleId(roleId);
        if(CollectionUtils.isEmpty(userIdList)){
            return;
        }
        List<SysRoleUser> roleUserList = Lists.newArrayList();
        for(Integer userId : userIdList) {
            SysRoleUser roleUser = SysRoleUser.builder()
                    .roleId(roleId).userId(userId)
                    .operator(RequestHolder.getCurrentUser().getUserName())
                    .operatorIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()))
                    .build();
            Date date = new Date();
            roleUser.setCreateTime(date);
            roleUser.setOperateTime(date);
            roleUserList.add(roleUser);
        }
        sysRoleUserMapper.batchInsert(roleUserList);

    }

}
