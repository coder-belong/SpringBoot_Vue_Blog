package com.belong.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.JWTVerifier;

import java.util.Date;

/**
 * @description: Jwt工具类，生成JWT和认证
 */
public class JwtUtils {
    // 密钥
    public static final String SECRET_KEY = "my_first_token";

    // token 过期时间 30分钟
    public static final int ExpiresIn = 1000 * 60 * 30;

    // 生成token
    public static String createToken(String username) {
        String token = "";

        // 传入用户信息和密钥来生成token
        token = JWT.create()
                .withAudience(username) // 传入用户信息
                .withExpiresAt(new Date(System.currentTimeMillis() + ExpiresIn))  // token过期时间
                .sign(Algorithm.HMAC256(SECRET_KEY));  // 传入密钥生成token

        return token;
    }

    // 校验token
    public static Boolean verifyToken(String token) {
        try {
            // 根据密钥来校验token
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(SECRET_KEY)).build();
            jwtVerifier.verify(token);
            System.out.println("token校验成功");
            return true;

        } catch (JWTVerificationException e) {
            throw new RuntimeException("token校验失败");
        }
    }

}
