package com.xiaojingye.wechatbackend.component.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaojingye.wechatbackend.component.mapper.CommodityMapper;
import com.xiaojingye.wechatbackend.component.mapper.PictureMapper;
import com.xiaojingye.wechatbackend.component.service.CommodityService;
import com.xiaojingye.wechatbackend.entity.constData.CommodityRequest;
import com.xiaojingye.wechatbackend.entity.pojo.Commodity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author xiaoj
* @description 针对表【t_commodity】的数据库操作Service实现
* @createDate 2024-03-14 19:28:37
*/
@Service
public class CommodityServiceImpl extends ServiceImpl<CommodityMapper, Commodity>
    implements CommodityService{
    @Autowired
    private CommodityMapper commodityMapper;
    
    @Autowired
    private PictureMapper pictureMapper;
    
    @Override
    public CommodityRequest insertCommodity(CommodityRequest commodityRequest) {
        commodityMapper.insertCommodity(commodityRequest.getCommodity());
        if (!commodityRequest.getPictures().isEmpty()) {
            pictureMapper.insertPicture(commodityRequest.getPictures());
        }
        return commodityRequest;
    }
    
    @Override
    public Integer removeCommodity(String commodityId) {
        return commodityMapper.removeCommodity(commodityId);
    }
    
    @Override
    public Page<Commodity> getCommodity(Integer startPage, Integer pageSize) {
        Page<Commodity> page = new Page<>(startPage,pageSize);
        commodityMapper.selectPage(page,null);
        return page;
    }
}




