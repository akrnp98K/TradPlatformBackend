package com.xiaojingye.wechatbackend.component.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaojingye.wechatbackend.entity.pojo.Category;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author xiaoj
* @description 针对表【t_category】的数据库操作Service
* @createDate 2024-03-14 15:41:03
*/
public interface CategoryService extends IService<Category> {
    int addCategory(Category newCategory);

    Category getCategoryByCateName(String categoryName);

    Page<Category> getCategoryList(Integer startPage, Integer endPage);

    Category getCategoryById(String id);
    
    List<Category> getCategoryList();
    
    int updateCategory(Category category);
}
