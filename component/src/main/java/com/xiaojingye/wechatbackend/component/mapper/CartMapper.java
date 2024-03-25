package com.xiaojingye.wechatbackend.component.mapper;

import com.xiaojingye.wechatbackend.entity.pojo.Cart;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @author xiaoj
* @description 针对表【t_cart】的数据库操作Mapper
* @createDate 2024-03-18 15:55:04
* @Entity com.xiaojingye.wechatbackend.component.pojo.Cart
*/

@Repository
@Mapper
public interface CartMapper extends BaseMapper<Cart> {
    Integer insertCart(@Param("cart") Cart cart);
    
    List<Cart> getCartAllItemByUserID(@Param("userId") String userId);
    
    List<Cart> getCartAllItemById(@Param("id") String id);
    
    Integer removeItemById(@Param("id") String id);
}




