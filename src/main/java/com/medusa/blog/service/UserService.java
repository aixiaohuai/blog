package com.medusa.blog.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.medusa.blog.common.interceptors.LoginRequired;
import com.medusa.blog.mapper.UserMapper;
import com.medusa.blog.model.User;
import com.medusa.blog.util.JwtToken;
import com.medusa.blog.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    public User getUserById(Long uid) {
        return userMapper.selectById(uid);
    }

    public Result doLogin(String username, String password, HttpServletRequest request) {
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("USER_NAME",username);
        queryWrapper.eq("USER_PASS",password);
        User user = userMapper.selectOne(queryWrapper);
        if(user==null){
            return  Result.fail("用户名或密码错误").action("/login");
        }
        String token = JwtToken.makeToken(user.getId());
        HttpSession session = request.getSession();
        session.setAttribute("token",token);
        return Result.success("登陆成功",null).action("/");
    }

}
