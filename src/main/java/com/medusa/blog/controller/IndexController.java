package com.medusa.blog.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.medusa.blog.model.Article;
import com.medusa.blog.service.ArticleService;
import com.medusa.blog.vo.ArticQueryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {
    @Autowired
    private ArticleService articleService;
    @RequestMapping("/")
    public String index(Model model, HttpServletRequest request){
        int currentPage = ServletRequestUtils.getIntParameter(request, "currentPage", 1);
        int pageSize = ServletRequestUtils.getIntParameter(request, "pageSize", 10);
        ArticQueryVO queryVO=new ArticQueryVO();
        queryVO.setCurrentPage(currentPage);
        queryVO.setPageSize(pageSize);
        IPage<Article> articPage = articleService.queryTopArticle(queryVO);
        model.addAttribute("articPage",articPage.getRecords());
        model.addAttribute("articTotal",articPage.getTotal());
        model.addAttribute("current",currentPage);
        model.addAttribute("pageSize",pageSize);
        model.addAttribute("columnId",1);
        return "index";
    }
}
