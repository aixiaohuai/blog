package com.medusa.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.medusa.blog.model.Article;
import com.medusa.blog.vo.ArticQueryVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleMapper extends BaseMapper<Article> {
    /**
     * 普通文章分页查询
     * @param page
     * @param queryVO
     * @return
     */
    List<Article> queryTopArtictle(Page<Article> page, @Param("articQuery") ArticQueryVO queryVO);

    /**
     * 帖子明细查询
     * @param articleId
     * @return
     */
    Article queryArticDetai(@Param("articleId") Long articleId);

    /**
     * 按专栏查询
     * @param page
     * @param queryVO
     * @return
     */
    List<Article> queryColumnArtictle(Page<Article> page, @Param("queryVO") ArticQueryVO queryVO);
}
