package com.medusa.blog.controller;

import com.medusa.blog.service.UserService;
import com.medusa.blog.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;
    /**
     * 跳转到登录页面
     * @return
     */
    @GetMapping("/login")
    public String toLogin() {

        return "auth/login";
    }

    @ResponseBody
    @PostMapping("/login")
    public Result doLogin(String userName, String passWord, HttpServletRequest request) {
        return userService.doLogin(userName, passWord,request);
    }

    @GetMapping("/logout")
    public void doLogout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("token");
        response.sendRedirect("/");
    }
}
