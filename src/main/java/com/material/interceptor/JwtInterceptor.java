package com.material.interceptor;

import com.material.jwt.JwtUtil;
import com.material.query.userQuery;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Component
public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    userQuery userService;

    @Autowired
    JwtUtil jwtUtil;



    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String,Object> map) throws Exception {
        String header = request.getHeader("Authorization");
        String id = (String) map.get("id");
        String password = (String) map.get("passwd");
        if(id != null && password != null){
            return true;
        }

        try {
            if(header != null){
                Claims claims = jwtUtil.parseJWT(header);
                if(!claims.getId().equals(claims.getSubject()) || claims.getId()==null){
                    throw new RuntimeException("权限不足");
                }
            }
        }catch (Exception ex){
            throw new RuntimeException("权限不足!");
        }

        return true;
    }
}
