package com.xiaojingye.wechatbackend.component.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaojingye.wechatbackend.entity.pojo.Picture;
import com.xiaojingye.wechatbackend.component.service.PictureService;
import com.xiaojingye.wechatbackend.component.mapper.PictureMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author xiaoj
* @description 针对表【t_picture】的数据库操作Service实现
* @createDate 2024-03-17 16:11:48
*/
@Service
public class PictureServiceImpl extends ServiceImpl<PictureMapper, Picture>
    implements PictureService{
    @Autowired
    private PictureMapper pictureMapper;
    @Override
    public List<Picture> insertPicture(List<Picture> pictureList) {
        Integer picture = pictureMapper.insertPicture(pictureList);
        log.debug("Insert Picture ===> " + picture);
        return pictureList;
    }
    
    @Override
    public List<String> getPictureUrlByCommodityId(String commodityId) {
        return pictureMapper.getPictureUrlByCommodityId(commodityId);
    }
    
    @Override
    public Integer removePicture(List<String> urls) {
        return pictureMapper.removePicture(urls);
    }
    
    @Override
    public Integer removePictureByCommodityId(String commodityId) {
        return pictureMapper.removePictureByCommodityId(commodityId);
    }
    
    @Override
    public List<Picture> getPictureByCommodityId(String commodityId) {
        return pictureMapper.getPictureByCommodityId(commodityId);
    }
}




