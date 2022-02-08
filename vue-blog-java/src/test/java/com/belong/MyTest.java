package com.belong;

import com.belong.utils.JwtUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MyTest {
    // 生成token测试
    @Test
    void createToken() {
        String token = JwtUtils.createToken("1");
        System.out.println(token);
    }

    // 校验token测试
    @Test
    void verifyToken() {
        JwtUtils.verifyToken("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxIiwiZXhwIjoxNjQ0MjM1MDUxfQ.IL2sijfIe6fso07H9h8Bct8mzNZvwqgej2BpeUYoCDo");
    }

}
