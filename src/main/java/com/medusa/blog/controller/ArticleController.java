package com.medusa.blog.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.medusa.blog.common.interceptors.LoginRequired;
import com.medusa.blog.model.Article;
import com.medusa.blog.model.SpecColumn;
import com.medusa.blog.service.ArticleService;
import com.medusa.blog.service.SpecColumnService;
import com.medusa.blog.vo.ArticQueryVO;
import com.medusa.blog.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private SpecColumnService specColumnService;

    @GetMapping("/detail/{id}")
    public String articleDetail(@PathVariable Long id, Model model) {
        Article articleDetail = articleService.queryArticDetail(id);
        model.addAttribute("articleDetail",articleDetail);
        return "article/detail";
    }

    @GetMapping("/column/{id}")
    public String panel(@PathVariable Long id, Model model, HttpServletRequest request) throws IOException {
        int currentPage = ServletRequestUtils.getIntParameter(request, "currentPage", 1);
        int pageSize = ServletRequestUtils.getIntParameter(request, "pageSize", 10);
        ArticQueryVO queryVO=new ArticQueryVO();
        queryVO.setCurrentPage(currentPage);
        queryVO.setPageSize(pageSize);
        queryVO.setColumnId(id);
        IPage<Article> articPage = articleService.queryColumnArticle(queryVO);
        model.addAttribute("articlePage",articPage.getRecords());
        model.addAttribute("articleTotal",articPage.getTotal());
        model.addAttribute("current",currentPage);
        model.addAttribute("columnId",id);
        model.addAttribute("pageSize",pageSize);
        return "article/column";
    }

    @GetMapping("/write")
    public String write(Model model) {
        List<SpecColumn> columns = specColumnService.queryColumnList();
        model.addAttribute("columns",columns);
        return "article/write";
    }

    /*编辑或发表文章*/
    @PostMapping("/write")
    @ResponseBody
    @LoginRequired
    public Result submit(Article article, HttpServletResponse response) throws IOException {
        return articleService.write(article);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    @LoginRequired
    public Result delete(@PathVariable Long id){
        articleService.getBaseMapper().deleteById(id);
        return Result.success();
    }

    @GetMapping("/{id}")
    @LoginRequired
    public String update(@PathVariable Long id,Model model){
        Article article = articleService.getBaseMapper().selectById(id);
        List<SpecColumn> columns = specColumnService.queryColumnList();
        model.addAttribute("columns",columns);
        model.addAttribute("article",article);
        return "article/write";
    }

}
