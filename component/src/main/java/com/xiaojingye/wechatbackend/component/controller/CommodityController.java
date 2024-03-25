package com.xiaojingye.wechatbackend.component.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaojingye.wechatbackend.component.service.CategoryService;
import com.xiaojingye.wechatbackend.component.service.CommodityService;
import com.xiaojingye.wechatbackend.component.service.PictureService;
import com.xiaojingye.wechatbackend.component.service.UserService;
import com.xiaojingye.wechatbackend.entity.constData.CommodityRequest;
import com.xiaojingye.wechatbackend.entity.constData.ResponseEntity;
import com.xiaojingye.wechatbackend.entity.pojo.Category;
import com.xiaojingye.wechatbackend.entity.pojo.Commodity;
import com.xiaojingye.wechatbackend.entity.pojo.Picture;
import com.xiaojingye.wechatbackend.entity.pojo.User;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/commodity")
@Slf4j
public class CommodityController {
    public final CommodityService commodityService;
    
    private final PictureService pictureService;
    
    private final CategoryService categoryService;
    
    private final UserService userService;
    
    @Autowired
    public CommodityController(CommodityService commodityService, PictureService pictureService, CategoryService categoryService, UserService userService) {
        this.commodityService = commodityService;
        this.pictureService = pictureService;
        this.categoryService = categoryService;
        this.userService = userService;
    }
    
    @PostMapping("/commodity")
    public ResponseEntity addCommodity(@RequestBody CommodityRequest commodityRequest, HttpServletResponse response) {
        System.out.println("commodity = " + commodityRequest.getCommodity());
        System.out.println("pictures = " + commodityRequest.getPictures());
        ResponseEntity responseEntity = new ResponseEntity();
        Commodity commodity = commodityRequest.getCommodity();
        if (commodity.categoryId != null && !commodity.categoryId.isEmpty()) {
            Category category = categoryService.getCategoryById(commodity.getCategoryId());
            if (category == null) {
                responseEntity.setCode(422);
                responseEntity.setData(null);
                responseEntity.setMessage("分类信息不存在");
                response.setStatus(422);
                return responseEntity;
            }
            commodity.setId(UUID.randomUUID().toString());
            commodity.setCreateTime(new Date(System.currentTimeMillis()));
            commodity.setLocation("CN Hubei");
            User user = userService.getUserById(Integer.parseInt(commodity.publisherId));
            if (user != null) {
                commodity.setPhoneNumber(user.getPhoneNumber());
            } else {
                responseEntity.setCode(422);
                responseEntity.setData(null);
                responseEntity.setMessage("用户信息有误");
                response.setStatus(422);
                return responseEntity;
            }
            commodityRequest.setCommodity(commodity);
            
            // 图片处理
            List<Picture> pictures = commodityRequest.getPictures();
            pictures.forEach(item -> {
                item.setId(UUID.randomUUID().toString());
                item.setCreateTime(new Date(System.currentTimeMillis()));
                item.setCommodityId(commodity.getId());
            });
            commodityRequest.setPictures(pictures);
            commodityService.insertCommodity(commodityRequest);
        }
        return new ResponseEntity(commodityRequest, 200, "OK");
    }
    
    @PutMapping("/commodity")
    public ResponseEntity updateCommodity(@RequestBody CommodityRequest newCommodity, HttpServletResponse response) {
        ResponseEntity responseEntity = new ResponseEntity();
        if (newCommodity == null) {
            responseEntity.setCode(422);
            responseEntity.setMessage("数据为空");
            responseEntity.setData(null);
            response.setStatus(422);
            return responseEntity;
        }
        Commodity commodity = newCommodity.getCommodity();
        if (commodity == null) {
            responseEntity.setCode(422);
            responseEntity.setMessage("数据为空");
            responseEntity.setData(null);
            response.setStatus(422);
            return responseEntity;
        }
        if (commodity.id == null) {
            responseEntity.setCode(404);
            responseEntity.setMessage("找不到该商品");
            responseEntity.setData(null);
            response.setStatus(404);
            return responseEntity;
        }
        
        removeCommodity(commodity.getId());
        
        responseEntity = addCommodity(newCommodity, response);
        return responseEntity;
    }
    
    
    @DeleteMapping("/commodity")
    public ResponseEntity removeCommodity(@RequestParam String commodityId) {
        if (commodityId == null) {
            return new ResponseEntity(null, 422, "参数空");
        }
        
        Integer removeCommodity = commodityService.removeCommodity(commodityId);
        Integer picture = pictureService.removePictureByCommodityId(commodityId);
        String result = "Remove : Commodity ==> " + removeCommodity + ": Picture ==> " + picture;
        log.debug(result);
        
        return new ResponseEntity(null, 200, result);
    }
    
    @GetMapping("/commodity")
    public ResponseEntity getCommodity(@RequestParam(value = "startPage", required = false) @Parameter(name = "startPage", description = "起始页") Integer startPage,
                                       @RequestParam(value = "pageSize", required = false) @Parameter(name = "pageSize", description = "大小") Integer pageSize) {
        if (startPage == null || pageSize == null || startPage <= 0 || pageSize <= 0) {
            startPage = 0;
            pageSize = Integer.MAX_VALUE;
        }
        Page<Commodity> commodity = commodityService.getCommodity(startPage, pageSize);
        ResponseEntity responseEntity = new ResponseEntity();
        responseEntity.setData(commodity);
        responseEntity.setCode(200);
        responseEntity.setMessage("OK");
        
        return responseEntity;
    }
    
    
}
