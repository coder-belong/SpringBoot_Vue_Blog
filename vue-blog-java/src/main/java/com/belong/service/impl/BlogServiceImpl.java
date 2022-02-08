package com.belong.service.impl;

import com.belong.entity.Blog;
import com.belong.mapper.BlogMapper;
import com.belong.service.BlogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author belong
 * @since 2022-02-07
 */
@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {

}
