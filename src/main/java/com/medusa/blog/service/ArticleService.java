package com.medusa.blog.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.medusa.blog.mapper.ArticleMapper;
import com.medusa.blog.model.Article;
import com.medusa.blog.vo.ArticQueryVO;
import com.medusa.blog.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ArticleService extends ServiceImpl<ArticleMapper,Article> {
    @Autowired
    private ArticleMapper articleMapper;
    public IPage<Article> queryTopArticle(ArticQueryVO queryVO) {
        Page<Article> page=new Page<>(queryVO.getCurrentPage(),queryVO.getPageSize());
        QueryWrapper<Article> queryWrapper=new QueryWrapper<>();
        queryWrapper.orderByDesc("RELEASE_DATE");
        return  articleMapper.selectPage(page, queryWrapper);
    }

    public Article queryArticDetail(Long id) {
        return articleMapper.selectById(id);
    }

    public IPage<Article> queryColumnArticle(ArticQueryVO queryVO) {
        Page<Article> page=new Page<>(queryVO.getCurrentPage(),queryVO.getPageSize());
        QueryWrapper<Article> queryWrapper=new QueryWrapper<>();
        queryWrapper.orderByDesc("RELEASE_DATE");
        queryWrapper.eq("COLUMN_ID",queryVO.getColumnId());
        return  articleMapper.selectPage(page, queryWrapper);
    }

    public Result write(Article article) {
        article.setReleaseDate(new Date());
        if(null!=article.getArticleId()){
            //执行编辑文章
            articleMapper.updateById(article);
            return Result.success("编辑成功",null).action("/");
        }else{
            //执行新增文章
            articleMapper.insert(article);
            return Result.success("发布成功",null).action("/");
        }
    }



}
