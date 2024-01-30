package com.cun.plus.service.impl;

import com.cun.plus.entity.User;
import com.cun.plus.mapper.UserMapper;
import com.cun.plus.service.UserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author linhongcun
 * @since 2024-01-30
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
