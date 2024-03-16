package com.xiaojingye.wechatbackend.component.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaojingye.wechatbackend.entity.pojo.User;
import com.xiaojingye.wechatbackend.component.service.UserService;
import com.xiaojingye.wechatbackend.component.mapper.UserMapper;
import org.apache.catalina.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author xiaoj
* @description 针对表【t_user】的数据库操作Service实现
* @createDate 2024-03-14 13:30:30
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{
    @Autowired
    private UserMapper userMapper;
    public User getUser(String username) {
        return userMapper.getUser(username);
    }

}




