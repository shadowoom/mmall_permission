package com.mmall.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SysPermissionModule {
    private Integer id;

    private String permissionModuleName;

    private Integer parentId;

    private String permissionModuleLevel;

    private Integer permissionModuleSeq;

    private Integer permissionModuleStatus;

    private String remark;

    private String operator;

    private Date createTime;

    private Date operateTime;

    private String operatorIp;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPermissionModuleName() {
        return permissionModuleName;
    }

    public void setPermissionModuleName(String permissionModuleName) {
        this.permissionModuleName = permissionModuleName == null ? null : permissionModuleName.trim();
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getPermissionModuleLevel() {
        return permissionModuleLevel;
    }

    public void setPermissionModuleLevel(String permissionModuleLevel) {
        this.permissionModuleLevel = permissionModuleLevel == null ? null : permissionModuleLevel.trim();
    }

    public Integer getPermissionModuleSeq() {
        return permissionModuleSeq;
    }

    public void setPermissionModuleSeq(Integer permissionModuleSeq) {
        this.permissionModuleSeq = permissionModuleSeq;
    }

    public Integer getPermissionModuleStatus() {
        return permissionModuleStatus;
    }

    public void setPermissionModuleStatus(Integer permissionModuleStatus) {
        this.permissionModuleStatus = permissionModuleStatus;
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