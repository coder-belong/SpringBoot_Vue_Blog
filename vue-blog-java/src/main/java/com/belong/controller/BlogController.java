package com.belong.controller;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.belong.common.Result;
import com.belong.entity.Blog;
import com.belong.service.impl.BlogServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author belong
 * @since 2022-02-07
 * 这里就是增删改查操作啦，后端的绝绝子工作
 */
@RestController
public class BlogController {
    @Autowired
    BlogServiceImpl blogService;

    // 分页查询
    @GetMapping("/blogs")
    public Result list(@RequestParam Integer currentPage, @RequestParam Integer pageSize) {
        Page<Blog> page = new Page<>(currentPage, pageSize);
        Page<Blog> page1 = blogService.page(page);
        return Result.success(page1);
    }

    // 根据id查询
    @GetMapping("/blog/{id}")
    public Result detial(@PathVariable Integer id) {
        Blog blog = blogService.getById(id);
        Assert.notNull(blog, "该文章已被删除");
        return Result.success(blog);
    }

    // 编辑文章
    @PutMapping("/blog/edit")
    public Result edit(@Validated @RequestBody Blog blog) {
        // 只能够编辑当前登录的用户id的文章
        UpdateWrapper<Blog> wrapper = new UpdateWrapper<>();
        wrapper.eq("id", blog.getId())
                .eq("user_id", blog.getUserId());
        boolean b = blogService.update(blog, wrapper);
        return Result.success("200", "编辑成功", null);
    }


    // 删除文章
    @DeleteMapping("/blog/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        blogService.removeById(id);
        return Result.success("200", "删除成功", null);
    }


    // 新增文章
    @PostMapping("/blog/add")
    public Result add(@Validated @RequestBody Blog blog) {
        blogService.save(blog);
        return Result.success("200", "新增成功", null);
    }
}
