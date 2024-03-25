package com.xiaojingye.wechatbackend.component.mapper;

import com.xiaojingye.wechatbackend.entity.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
* @author xiaoj
* @description 针对表【t_user】的数据库操作Mapper
* @createDate 2024-03-14 13:30:30
* @Entity com.xiaojingye.wechatbackend.entity.pojo.User
*/
@Repository
@Mapper
public interface UserMapper extends BaseMapper<User> {
    User getUser(String account);
    
    User getUserById(@Param("id") Integer id);
}




