package com.mint.springblog.service.impl;

import com.mint.springblog.entity.User;
import com.mint.springblog.mapper.UserMapper;
import com.mint.springblog.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author mint
 * @since 2022-01-05
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
