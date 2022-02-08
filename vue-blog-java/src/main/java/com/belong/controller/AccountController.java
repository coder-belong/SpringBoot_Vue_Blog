package com.belong.controller;

import cn.hutool.core.map.MapUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.belong.common.Result;
import com.belong.common.dto.LoginDto;
import com.belong.entity.User;
import com.belong.service.impl.UserServiceImpl;
import com.belong.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {
    @Autowired
    UserServiceImpl userService;

    @PostMapping("/login")
    public Result login(@Validated @RequestBody LoginDto loginDto) {
        // 1. 判断用户名密码是否为空，JSR303校验已经帮我们做好了
        // 2. 查询数据库是否有该用户
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", loginDto.getUsername());
        User user = userService.getOne(wrapper);
        Assert.notNull(user, "用户不存在");

        // 3. 校验密码是否与数据库匹配匹对
        String password = SecureUtil.md5(loginDto.getPassword());
        System.out.println(password);
        if (!user.getPassword().equals(password)) {
            return Result.fail("密码错误");
        }

        // 3. 生成token，与用户信息一起返回给前端
        String token = JwtUtils.createToken(user.getUsername());

        return Result.success(MapUtil.builder()
                .put("id", user.getId())
                .put("username", user.getUsername())
                .put("avatar", user.getAvatar())
                .put("email", user.getEmail())
                .put("token", token)
                .map()
        );
    }
}
