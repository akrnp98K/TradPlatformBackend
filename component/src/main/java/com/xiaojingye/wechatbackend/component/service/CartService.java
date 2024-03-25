package com.xiaojingye.wechatbackend.component.service;

import com.xiaojingye.wechatbackend.entity.pojo.Cart;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author xiaoj
* @description 针对表【t_cart】的数据库操作Service
* @createDate 2024-03-18 15:55:04
*/
public interface CartService extends IService<Cart> {
    Integer insertCart(Cart cart);
    
    List<Cart> getCartAllItem(String userId);
    
    List<Cart> getCartAllItemById(String id);
    
    Integer removeItemById(String id);
}
