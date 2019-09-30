package com.fehead.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @author lmwis
 * @description:
 * @date 2019-09-05 13:27
 * @Version 1.0
 */

@TableName("uri_access_count")
public class UriCount {

    @TableId(type = IdType.AUTO)
    private long id;

    private String uriName;

    private long totalTimes;

    private long successTimes;

    private long failTimes;

    private String des;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUriName() {
        return uriName;
    }

    public void setUriName(String uriName) {
        this.uriName = uriName;
    }

    public long getTotalTimes() {
        return totalTimes;
    }

    public void setTotalTimes(long totalTimes) {
        this.totalTimes = totalTimes;
    }

    public long getSuccessTimes() {
        return successTimes;
    }

    public void setSuccessTimes(long successTimes) {
        this.successTimes = successTimes;
    }

    public long getFailTimes() {
        return failTimes;
    }

    public void setFailTimes(long failTimes) {
        this.failTimes = failTimes;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
