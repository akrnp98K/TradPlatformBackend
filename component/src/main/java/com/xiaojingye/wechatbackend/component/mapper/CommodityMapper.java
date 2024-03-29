package com.xiaojingye.wechatbackend.component.mapper;

import com.xiaojingye.wechatbackend.entity.pojo.Commodity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
* @author xiaoj
* @description 针对表【t_commodity】的数据库操作Mapper
* @createDate 2024-03-14 19:28:37
* @Entity com.xiaojingye.wechatbackend.entity.pojo.Commodity
*/
@Repository
@Mapper
public interface CommodityMapper extends BaseMapper<Commodity> {
    Integer insertCommodity(@Param("commodity") Commodity commodity);
    
    Integer updateCommodity(@Param("newCommodity") Commodity commodity);
    
    Integer removeCommodity(@Param("id") String commodityId);
    
    Commodity getCommodityById(@Param("Id") String id);
}




