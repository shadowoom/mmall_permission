package com.mmall.model;

import java.util.Date;

public class SysPermission {
    private Integer id;

    private String permissionCode;

    private String permissionName;

    private Integer permissionModuleId;

    private String permissionUrl;

    private Integer permissionType;

    private Integer permissionStatus;

    private Integer permissionSeq;

    private String remark;

    private String operator;

    private Date createTime;

    private Date operateTime;

    private String operatorIp;

    public SysPermission(Integer id, String permissionCode, String permissionName, Integer permissionModuleId, String permissionUrl, Integer permissionType, Integer permissionStatus, Integer permissionSeq, String remark, String operator, Date createTime, Date operateTime, String operatorIp) {
        this.id = id;
        this.permissionCode = permissionCode;
        this.permissionName = permissionName;
        this.permissionModuleId = permissionModuleId;
        this.permissionUrl = permissionUrl;
        this.permissionType = permissionType;
        this.permissionStatus = permissionStatus;
        this.permissionSeq = permissionSeq;
        this.remark = remark;
        this.operator = operator;
        this.createTime = createTime;
        this.operateTime = operateTime;
        this.operatorIp = operatorIp;
    }

    public SysPermission() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPermissionCode() {
        return permissionCode;
    }

    public void setPermissionCode(String permissionCode) {
        this.permissionCode = permissionCode == null ? null : permissionCode.trim();
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName == null ? null : permissionName.trim();
    }

    public Integer getPermissionModuleId() {
        return permissionModuleId;
    }

    public void setPermissionModuleId(Integer permissionModuleId) {
        this.permissionModuleId = permissionModuleId;
    }

    public String getPermissionUrl() {
        return permissionUrl;
    }

    public void setPermissionUrl(String permissionUrl) {
        this.permissionUrl = permissionUrl == null ? null : permissionUrl.trim();
    }

    public Integer getPermissionType() {
        return permissionType;
    }

    public void setPermissionType(Integer permissionType) {
        this.permissionType = permissionType;
    }

    public Integer getPermissionStatus() {
        return permissionStatus;
    }

    public void setPermissionStatus(Integer permissionStatus) {
        this.permissionStatus = permissionStatus;
    }

    public Integer getPermissionSeq() {
        return permissionSeq;
    }

    public void setPermissionSeq(Integer permissionSeq) {
        this.permissionSeq = permissionSeq;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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