package com.xiaojingye.wechatbackend.component.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaojingye.wechatbackend.entity.constData.CommodityRequest;
import com.xiaojingye.wechatbackend.entity.pojo.Commodity;

/**
* @author xiaoj
* @description 针对表【t_commodity】的数据库操作Service
* @createDate 2024-03-14 19:28:37
*/
public interface CommodityService extends IService<Commodity> {
    CommodityRequest insertCommodity(CommodityRequest commodityRequest);
    
    Integer removeCommodity(String commodityId);
    
    Page<Commodity> getCommodity(Integer startPage, Integer pageSize);
}
