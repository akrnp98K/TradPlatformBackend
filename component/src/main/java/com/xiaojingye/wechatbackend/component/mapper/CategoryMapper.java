package com.xiaojingye.wechatbackend.component.mapper;

import com.xiaojingye.wechatbackend.entity.pojo.Category;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @author xiaoj
* @description 针对表【t_category】的数据库操作Mapper
* @createDate 2024-03-14 15:41:03
* @Entity com.xiaojingye.wechatbackend.entity.pojo.Category
*/
@Repository
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {
    int addCategory(@Param("newCategory") Category newCategory);

    Category getCategoryByName(@Param("categoryName") String categoryName);

    List<Category> getCategoryList(@Param("startPage") Integer startPage,@Param("endPage") Integer endPage);

    Category getOneByIdCategory(@Param("id") String id);
    
    int updateCategory(@Param("newCategory") Category newCategory);
}




