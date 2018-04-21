package com.mmall.model;

import java.util.Date;

public class SysDept {
    private Integer id;

    private String deptName;

    private Integer parentId;

    private String deptLevel;

    private Integer deptSeq;

    private String remark;

    private String operator;

    private Date createTime;

    private Date operateTime;

    private String operatorIp;

    public SysDept(Integer id, String deptName, Integer parentId, String deptLevel, Integer deptSeq, String remark, String operator, Date createTime, Date operateTime, String operatorIp) {
        this.id = id;
        this.deptName = deptName;
        this.parentId = parentId;
        this.deptLevel = deptLevel;
        this.deptSeq = deptSeq;
        this.remark = remark;
        this.operator = operator;
        this.createTime = createTime;
        this.operateTime = operateTime;
        this.operatorIp = operatorIp;
    }

    public SysDept() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName == null ? null : deptName.trim();
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getDeptLevel() {
        return deptLevel;
    }

    public void setDeptLevel(String deptLevel) {
        this.deptLevel = deptLevel == null ? null : deptLevel.trim();
    }

    public Integer getDeptSeq() {
        return deptSeq;
    }

    public void setDeptSeq(Integer deptSeq) {
        this.deptSeq = deptSeq;
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