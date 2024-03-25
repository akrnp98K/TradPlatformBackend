package com.xiaojingye.wechatbackend.component.controller;


import com.xiaojingye.wechatbackend.component.service.CartService;
import com.xiaojingye.wechatbackend.entity.constData.ResponseEntity;
import com.xiaojingye.wechatbackend.entity.pojo.Cart;
import com.xiaojingye.wechatbackend.entity.until.CommonUntil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/cart")
@Slf4j
public class CartController {
    @Autowired
    private CartService cartService;
    
    @PostMapping("/cart")
    public ResponseEntity addItemToCart(@RequestBody List<Cart> cartList) {
        if (cartList == null) {
            return new ResponseEntity(null, 400, "空数据");
        }
        
        cartList.forEach(cart -> {
            cart.setId(UUID.randomUUID().toString());
            cart.setCreateTime(new Date(System.currentTimeMillis()));
            cartService.insertCart(cart);
            log.debug("Insert ==> " + cart);
        });
        return new ResponseEntity(cartList, 200, "OK");
    }
    
    @GetMapping("/cart")
    public ResponseEntity getCartItem(@RequestParam("id") String id) {
        List<Cart> cartAllItem = null;
        if (!CommonUntil.checkUUID(id)) {
            if (id == null || Integer.parseInt(id) <= 0) {
                return new ResponseEntity(null, 422, "空数据");
            }
            cartAllItem = cartService.getCartAllItem(id);
        }else {
            cartAllItem = cartService.getCartAllItemById(id);
        }
        
        return new ResponseEntity(cartAllItem, 200, "全量返回");
    }
    
    @DeleteMapping("/cart")
    public ResponseEntity removeItem(@RequestBody List<Cart> cartList) {
        if (cartList == null) {
            return new ResponseEntity(null, 422, "空数据");
        }
        cartList.forEach(item -> cartService.removeItemById(item.getId()));
        return new ResponseEntity(null,200,"OK");
    }
    @PutMapping("/cart")
    public ResponseEntity updateItem(@RequestBody Cart cart) {
        if (cart == null || cart.getId() == null) {
            return new ResponseEntity(null, 422, "空数据");
        }
        cartService.removeItemById(cart.getId());
        List<Cart> list = new ArrayList<>();
        list.add(cart);
        return addItemToCart(list);
    }
}
