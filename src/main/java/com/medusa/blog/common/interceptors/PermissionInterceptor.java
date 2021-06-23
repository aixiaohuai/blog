package com.medusa.blog.common.interceptors;

import com.auth0.jwt.interfaces.Claim;
import com.medusa.blog.common.LocalUser;
import com.medusa.blog.model.User;
import com.medusa.blog.service.UserService;
import com.medusa.blog.util.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Optional;

public class PermissionInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private UserService userService;

    public PermissionInterceptor() {
        super();
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Optional<LoginRequired> LoginRequired = this.getLoginRequired(handler);
        if (!LoginRequired.isPresent()) {
            return true;
        }

        String bearerToken = request.getHeader("Authorization");
        if(bearerToken==null){
            bearerToken = request.getParameter("Authorization");
        }
        if (StringUtils.isEmpty(bearerToken)) {
            throw new RuntimeException();
        }

        if (!bearerToken.startsWith("Bearer")) {
            throw new RuntimeException();
        }
        String tokens[] = bearerToken.split(" ");
        if (!(tokens.length == 2)) {
            throw new RuntimeException();
        }
        String token = tokens[1];
        Optional<Map<String, Claim>> optionalMap = JwtToken.getClaims(token);
        Map<String, Claim> map = optionalMap
                .orElseThrow(() -> new RuntimeException());
        this.setToThreadLocal(map);
        return true;
    }

    private void setToThreadLocal(Map<String,Claim> map) {
        Long uid = map.get("uid").asLong();
        User user = this.userService.getUserById(uid);
        LocalUser.set(user);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        LocalUser.clear();
        super.afterCompletion(request, response, handler, ex);
    }

    private Optional<LoginRequired> getLoginRequired(Object handler) {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            LoginRequired loginRequired = handlerMethod.getMethod().getAnnotation(LoginRequired.class);
            if (loginRequired == null) {
                return Optional.empty();
            }
            return Optional.of(loginRequired);
        }
        return Optional.empty();
    }

}
