package com.xiaojingye.wechatbackend.component.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaojingye.wechatbackend.entity.pojo.Category;
import com.xiaojingye.wechatbackend.component.service.CategoryService;
import com.xiaojingye.wechatbackend.component.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author xiaoj
* @description 针对表【t_category】的数据库操作Service实现
* @createDate 2024-03-14 15:41:03
*/

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category>
    implements CategoryService{
    @Autowired
    private CategoryMapper categoryMapper;
    @Override
    public int addCategory(Category newCategory) {
        return categoryMapper.addCategory(newCategory);
    }

    @Override
    public Category getCategoryByCateName(String categoryName) {
        return categoryMapper.getCategoryByName(categoryName);
    }

    @Override
    public Category getCategoryById(String id) {
        return categoryMapper.getOneByIdCategory(id);
    }

    @Override
    public Page<Category> getCategoryList(Integer startPage, Integer endPage) {
        Page<Category> page = new Page<>(startPage,endPage);
        categoryMapper.selectPage(page, null);
        return page;
    }
    @Override
    public List<Category> getCategoryList() {
        return categoryMapper.getCategoryList(null, null);
    }
    @Override
    public int updateCategory(Category category) {
        return categoryMapper.updateCategory(category);
    }
}




