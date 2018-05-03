package com.mmall.model;


import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
public class SysLogWithBLOBs extends SysLog {

    public SysLogWithBLOBs(Integer id, Integer type, Integer targetId, Integer status, String operator, Date createTime, Date operateTime, String operatorIp, String oldValue, String newValue) {
        super(id, type, targetId, status, operator, createTime, operateTime, operatorIp);
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    private String oldValue;

    private String newValue;

    public String getOldValue() {
        return oldValue;
    }

    public void setOldValue(String oldValue) {
        this.oldValue = oldValue == null ? null : oldValue.trim();
    }

    public String getNewValue() {
        return newValue;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue == null ? null : newValue.trim();
    }
}