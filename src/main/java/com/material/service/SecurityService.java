package com.material.service;

import com.material.jwt.JwtUtil;
import graphql.GraphQLException;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class SecurityService {

    @Autowired
    HttpServletRequest request;

    @Autowired
    JwtUtil jwtUtil;

    public boolean ifLogin(){
        String header = request.getHeader("Authorization");
        if(header == null){
            return false;
        }
        if (header != null) {
            Claims claims = jwtUtil.parseJWT(header);
            if (!claims.getId().equals(claims.getSubject()) || claims.getId() == null) {
                return false;
            }
        }
        return true;
    }

    public void loginJudge() throws GraphQLException{
        String header = request.getHeader("Authorization");
        if(header == null){
            System.out.println("没有登录");
            throw new GraphQLException("没有登录");
        }
        if (header != null) {
            Claims claims = jwtUtil.parseJWT(header);
            if (!claims.getId().equals(claims.getSubject()) || claims.getId() == null) {
                throw new GraphQLException("没有登录");
            }
        }

    }
}
