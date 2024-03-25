package com.xiaojingye.wechatbackend.component.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaojingye.wechatbackend.entity.pojo.Picture;

import java.util.List;

/**
* @author xiaoj
* @description 针对表【t_picture】的数据库操作Service
* @createDate 2024-03-17 16:11:48
*/
public interface PictureService extends IService<Picture> {
    List<Picture> insertPicture(List<Picture> pictureList);
    
    List<String> getPictureUrlByCommodityId(String commodityId);
    
    Integer removePicture(List<String> urls);
    Integer removePictureByCommodityId(String commodityId);
    
    List<Picture> getPictureByCommodityId( String commodityId);
}
