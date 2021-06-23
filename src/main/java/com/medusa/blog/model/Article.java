package com.medusa.blog.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ARTICLE_ID",type = IdType.AUTO)
    private Long articleId;

    @TableField("COLUMN_ID")
    private Long columnId;

    @TableField("ARTICLE_TITLE")
    private String articleTitle;

    @TableField("ARTICLE_CONTENT")
    private String articleContent;

    @TableField("RELEASE_DATE")
    private Date releaseDate;
}
