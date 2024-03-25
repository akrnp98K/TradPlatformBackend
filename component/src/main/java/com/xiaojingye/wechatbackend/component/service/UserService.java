package com.xiaojingye.wechatbackend.component.service;

import com.xiaojingye.wechatbackend.entity.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author xiaoj
* @description 针对表【t_user】的数据库操作Service
* @createDate 2024-03-14 13:30:30
*/
public interface UserService extends IService<User> {
    User getUser(String username);
    User getUserById(Integer id);
}
