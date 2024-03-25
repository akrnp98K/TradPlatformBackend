package com.xiaojingye.wechatbackend.component.mapper;

import com.xiaojingye.wechatbackend.entity.pojo.Picture;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @author xiaoj
* @description 针对表【t_picture】的数据库操作Mapper
* @createDate 2024-03-17 16:11:48
* @Entity com.xiaojingye.wechatbackend.component.pojo.Picture
*/
@Repository
@Mapper
public interface PictureMapper extends BaseMapper<Picture> {
    Integer insertPicture(List<Picture> pictures);
    List<String> getPictureUrlByCommodityId(@Param("commodityId") String commodityId);
    
    Integer removePicture(List<String> urls);
    
    Integer removePictureByCommodityId(@Param("commodityId") String commodityId);
    
    List<Picture> getPictureByCommodityId(@Param("commodityId") String commodityId);
    
}




