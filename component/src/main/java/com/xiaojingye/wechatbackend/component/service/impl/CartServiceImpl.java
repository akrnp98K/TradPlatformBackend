package com.xiaojingye.wechatbackend.component.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaojingye.wechatbackend.entity.pojo.Cart;
import com.xiaojingye.wechatbackend.component.service.CartService;
import com.xiaojingye.wechatbackend.component.mapper.CartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author xiaoj
* @description 针对表【t_cart】的数据库操作Service实现
* @createDate 2024-03-18 15:55:04
*/
@Service
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart>
    implements CartService{
   
    @Autowired
    private CartMapper cartMapper;
    @Override
    public Integer insertCart(Cart cart) {
        return cartMapper.insertCart(cart);
    }
    
    @Override
    public List<Cart> getCartAllItem(String userId) {
        return cartMapper.getCartAllItemByUserID(userId);
    }
    
    @Override
    public List<Cart> getCartAllItemById(String id) {
        return cartMapper.getCartAllItemById(id);
    }
    
    @Override
    public Integer removeItemById(String id) {
        return cartMapper.removeItemById(id);
    }
}




