package com.medusa.blog.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SpecColumn implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("COLUMN_ID")
    private Long columnId;

    @TableField("COLUMN_NAME")
    private String columnName;

    @TableField("COLUMN_CLASS")
    private String columnClass;

    @TableField("COLUMN_URL")
    private String columnUrl;

    @TableField("COLUMN_STATE")
    private Integer columnState;

    @TableField("COLUMN_SORT")
    private Integer columnSort;


}
