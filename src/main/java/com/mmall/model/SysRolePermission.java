package com.mmall.model;

import java.util.Date;

public class SysRolePermission {
    private Integer id;

    private Integer roleId;

    private Integer permissionId;

    private String operator;

    private Date createTime;

    private Date operateTime;

    private String operatorIp;

    public SysRolePermission(Integer id, Integer roleId, Integer permissionId, String operator, Date createTime, Date operateTime, String operatorIp) {
        this.id = id;
        this.roleId = roleId;
        this.permissionId = permissionId;
        this.operator = operator;
        this.createTime = createTime;
        this.operateTime = operateTime;
        this.operatorIp = operatorIp;
    }

    public SysRolePermission() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }

    public String getOperatorIp() {
        return operatorIp;
    }

    public void setOperatorIp(String operatorIp) {
        this.operatorIp = operatorIp == null ? null : operatorIp.trim();
    }
}