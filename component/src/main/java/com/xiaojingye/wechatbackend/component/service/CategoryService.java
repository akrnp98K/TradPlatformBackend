package com.xiaojingye.wechatbackend.component.service;

import com.xiaojingye.wechatbackend.entity.pojo.Category;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author xiaoj
* @description 针对表【t_category】的数据库操作Service
* @createDate 2024-03-14 15:41:03
*/
public interface CategoryService extends IService<Category> {
    int addCategory(Category newCategory);

    Category getCategoryByCateName(String categoryName);

    List<Category> getCategoryList(Integer startPage,Integer endPage);

    Category getCategoryById(String id);
    
    int updateCategory(Category category);
}
