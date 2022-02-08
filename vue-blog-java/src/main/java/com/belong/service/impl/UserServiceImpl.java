package com.belong.service.impl;

import com.belong.entity.User;
import com.belong.mapper.UserMapper;
import com.belong.service.UserService;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
