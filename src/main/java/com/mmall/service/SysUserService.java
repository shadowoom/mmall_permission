package com.mmall.service;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.mmall.beans.Mail;
import com.mmall.beans.PageQuery;
import com.mmall.beans.PageResult;
import com.mmall.common.RequestHolder;
import com.mmall.dao.SysRoleUserMapper;
import com.mmall.dao.SysUserMapper;
import com.mmall.exception.ParamException;
import com.mmall.model.SysRole;
import com.mmall.model.SysUser;
import com.mmall.param.UserParam;
import com.mmall.util.*;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * permission
 * com.mmall.service
 * Created by Zhang Chen
 * 4/25/2018
 */

@Service
public class SysUserService {

    @Resource
    private SysUserMapper sysUserMapper;

    @Resource
    private SysRoleUserMapper sysRoleUserMapper;

    public void save(UserParam param) {
        BeanValidator.check(param);
        if(checkTelephoneExist(param.getUserTelephone(), param.getId())) {
            throw new ParamException("电话已被占用");
        }
        if(checkEmailExist(param.getUserEmail(), param.getId())) {
            throw new ParamException("邮箱已被占用");
        }
        String password = PasswordUtil.randomPassword();

        //TODO - send generated password to the email account:
        password = "12345678";

        String encryptedPassword = MD5Util.encrypt(password);
        SysUser user = SysUser.builder().userName(param.getUserName())
                .userTelephone(param.getUserTelephone())
                .userEmail(param.getUserEmail())
                .userPassword(encryptedPassword).deptId(param.getDeptId())
                .userStatus(param.getUserStatus())
                .remark(param.getRemark()).build();
        user.setOperator(RequestHolder.getCurrentUser().getUserName());
        user.setOperatorIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()));
        Date now = new Date();
        user.setCreateTime(now);
        user.setOperateTime(now);
        //TODO: sendEmail
//        Set<String> receivers = new HashSet<>();
//        receivers.add(param.getUserEmail());
//        String message = "<h1>来自www.phaccessory.com网站的注册激活邮件,激活请点击以下链接:</h1>\n" +
//                        "<h3><a href=\"http://localhost:8080/admin/index.page\">http://localhost:8080/admin/index.page</a></h3>" +
//                        "您的登录密码为: " + password + "。请妥善保管!";
//        Mail newMail = Mail.builder().subject("来自www.phaccessory.com的注册密码").message(message).receivers(receivers).build();
//        MailUtil.send(newMail);
        sysUserMapper.insertSelective(user);
    }

    public void update(UserParam param) {
        BeanValidator.check(param);
        if(checkTelephoneExist(param.getUserTelephone(), param.getId())) {
            throw new ParamException("电话已被占用");
        }
        if(checkEmailExist(param.getUserEmail(), param.getId())) {
            throw new ParamException("邮箱已被占用");
        }
        SysUser userBeforeUpdate = sysUserMapper.selectByPrimaryKey(param.getId());
        Preconditions.checkNotNull(userBeforeUpdate, "待更新的用户不存在");
        SysUser userAfterUpdate = SysUser.builder().id(param.getId())
                .userName(param.getUserName())
                .userTelephone(param.getUserTelephone())
                .userEmail(param.getUserEmail())
                .userPassword(userBeforeUpdate.getUserPassword()).deptId(param.getDeptId())
                .userStatus(param.getUserStatus())
                .remark(param.getRemark()).build();
        userAfterUpdate.setOperator(RequestHolder.getCurrentUser().getUserName());
        userAfterUpdate.setOperatorIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()));
        Date now = new Date();
        userAfterUpdate.setOperateTime(now);
        sysUserMapper.updateByPrimaryKeySelective(userAfterUpdate);
    }

    public SysUser findUserByKeyword(String keyword) {
        return sysUserMapper.findUserByKeyword(keyword);
    }

    public boolean checkEmailExist(String mail, Integer userId) {
        return sysUserMapper.countByMail(mail, userId) > 0;
    }

    public boolean checkTelephoneExist(String telephone, Integer userId) {
        return sysUserMapper.countByTelephone(telephone, userId) > 0;
    }

    public PageResult<SysUser> getPageByDeptId(int deptId, PageQuery pageQuery) {
        BeanValidator.check(pageQuery);
        int count = sysUserMapper.countByDeptId(deptId);
        if(count > 0) {
            List<SysUser> userList = sysUserMapper.getPageByDeptId(deptId, pageQuery);
            return PageResult.<SysUser>builder().data(userList).total(count).build();
        }
        return PageResult.<SysUser>builder().build();
    }

    public List<SysUser> getAllUsers() {
        return sysUserMapper.getAllUsers();
    }

    public List<SysUser> getUserListByRoleList(List<SysRole> roleList) {
        if(CollectionUtils.isEmpty(roleList)) {
            return Lists.newArrayList();
        }
        List<Integer> roleIdList = roleList.stream()
                .map(sysRole -> sysRole.getId()).collect(Collectors.toList());
        List<Integer> userIdList = sysRoleUserMapper.getUserIdListByRoleIdList(roleIdList);
        if(CollectionUtils.isEmpty(userIdList)) {
            return Lists.newArrayList();
        }
        return sysUserMapper.getUsersByIdList(userIdList);
    }

}
