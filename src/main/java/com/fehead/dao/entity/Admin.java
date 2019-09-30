package com.fehead.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @author lmwis
 * @description:
 * @date 2019-08-29 15:20
 * @Version 1.0
 */

@TableName("admin_info")
public class Admin {


    @TableId(type = IdType.AUTO)
    private long id;

    private int userId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
