package com.medusa.blog.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class User {

    @TableId("USER_ID")
    private Long id;

    @TableField("USER_NAME")
    private String username;

    @TableField("USER_PASS")
    private String password;
}
