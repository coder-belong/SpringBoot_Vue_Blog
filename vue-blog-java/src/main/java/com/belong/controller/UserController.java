package com.belong.controller;


import com.belong.common.Result;
import com.belong.entity.User;
import com.belong.service.impl.UserServiceImpl;
import com.belong.utils.JwtUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author belong
 * @since 2022-02-07
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户表操作")
public class UserController {
    @Autowired
    UserServiceImpl userService;

    @ApiOperation("查询全部用户")
    @GetMapping("/getAll")
    public List<User> getAllUser() {
        return userService.list();
    }

    @GetMapping("/test01")
    public Result test01() {
        //return Result.success(userService.list());
        return Result.success("200", "查询成功", userService.list());
    }

    @GetMapping("/test02")
    public Result test02() {
        JwtUtils.verifyToken("123213");
        return Result.success("200", "操作成功", null);
    }

    @ApiOperation("新增一个用户 *需要token")
    @PostMapping("/save_user")
    public Result test03(@Validated @RequestBody User user ) {
        return Result.success(user);
    }
}
