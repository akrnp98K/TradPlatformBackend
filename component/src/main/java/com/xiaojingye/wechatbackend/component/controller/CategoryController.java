package com.xiaojingye.wechatbackend.component.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaojingye.wechatbackend.component.service.CategoryService;
import com.xiaojingye.wechatbackend.entity.constData.MediaType;
import com.xiaojingye.wechatbackend.entity.constData.ResponseEntity;
import com.xiaojingye.wechatbackend.entity.pojo.Category;
import com.xiaojingye.wechatbackend.entity.until.CommonUntil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/category")
@Tag(name = "商品分类", description = "商品分类CRUD处理")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    
    @PostMapping("/Category")
    @Operation(summary = "新增一个分类", description = "新增一个商品分类\n一级分类仅传入categoryName，二级三级需要额外的上一级父类ID,其他由服务端处理\n-服务端组件")
    @Parameters(@Parameter(name = "newCategory", description = "商品分类名称"))
    @ApiResponse(content = @Content(mediaType = MediaType.JSON))
    @ApiResponse(responseCode = "200", description = "添加成功", content = @Content(mediaType = MediaType.JSON))
    @ApiResponse(responseCode = "422", description = "参数非法", content = @Content(mediaType = MediaType.JSON))
    @ApiResponse(responseCode = "403", description = "添加的分类已经存在", content = @Content(mediaType = MediaType.JSON))
    @ApiResponse(responseCode = "422", description = "父类不存在", content = @Content(mediaType = MediaType.JSON))
    public Object addCategory(@RequestBody Category newCategory, HttpServletResponse response) {
        ResponseEntity responseEntity = new ResponseEntity();
        if (newCategory == null || newCategory.categoryName.isEmpty()) {
            responseEntity.setData(null);
            responseEntity.setCode(422);
            responseEntity.setMessage("数据不能为空");
            response.setStatus(422);
        } else {
            responseEntity = getCategoryInfo(newCategory.categoryName);
            if (responseEntity.code == 404) {
                newCategory.setId(UUID.randomUUID().toString());
                // 确定分类层级并校验父类合法性
                if (newCategory.parentCategoryId != null && !newCategory.parentCategoryId.isEmpty()) {
                    Category parentCategory = (Category) getCategoryInfo(newCategory.parentCategoryId).data;
                    if (parentCategory == null) {
                        responseEntity.setData(newCategory);
                        responseEntity.setCode(422);
                        responseEntity.setMessage("父类不存在");
                        response.setStatus(422);
                    } else {
                        // 设置分类级别
                        newCategory.setLevel(parentCategory.level + 1);
                    }
                } else {
                    newCategory.setLevel(1);
                }
                newCategory.setCreateTime(new Date(System.currentTimeMillis()));
                int i = categoryService.addCategory(newCategory);
                log.debug("Insert =>" + i);
                responseEntity.setData(newCategory);
                responseEntity.setCode(200);
                responseEntity.setMessage("Success");
                response.setStatus(200);
            } else {
                log.debug("分类重复");
                responseEntity.setData(newCategory);
                responseEntity.setCode(403);
                responseEntity.setMessage("分类已经存在");
                response.setStatus(403);
            }
        }
        
        return responseEntity;
    }
    
    @GetMapping("/getCategoryByName/{categoryName}")
    @Operation(summary = "按照分类名称或者分类ID查询分类信息", description = "根据商品分类名称或者分类ID查询分类信息\n-服务端组件")
    @Parameters(@Parameter(name = "categoryInfo", description = "商品分类名称或ID"))
    @ApiResponse(content = @Content(mediaType = MediaType.JSON))
    @ApiResponse(responseCode = "200", description = "查询到该分类并返回数据", content = @Content(mediaType = MediaType.JSON))
    @ApiResponse(responseCode = "404", description = "未查询到该分类", content = @Content(mediaType = MediaType.JSON))
    public ResponseEntity getCategoryInfo(@PathVariable("categoryName") String categoryInfo) {
        Category category;
        // 校验参数为UUID还是类型名称
        if (CommonUntil.checkUUID(categoryInfo)) {
            category = categoryService.getCategoryById(categoryInfo);
        } else {
            category = categoryService.getCategoryByCateName(categoryInfo);
        }
        ResponseEntity responseEntity = new ResponseEntity();
        if (category == null) {
            responseEntity.setData(null);
            responseEntity.setCode(404);
            responseEntity.setMessage("未找到此分类");
        } else {
            responseEntity.setData(category);
            responseEntity.setCode(200);
            responseEntity.setMessage("OK");
        }
        
        return responseEntity;
    }
    
    @GetMapping("/Category")
    @Operation(summary = "获取所有分类信息", description = "获取所有分类信息或按照分页获取")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = MediaType.JSON))
    @ApiResponse(responseCode = "422", description = "参数非法", content = @Content(mediaType = MediaType.JSON))
    public Object getCategoryList(@RequestParam(value = "startPage", required = false) @Parameter(name = "startPage", description = "起始页") Integer startPage,
                                  @RequestParam(value = "endPage", required = false) @Parameter(name = "endPage", description = "页大小") Integer endPage,
                                  HttpServletResponse response) {
        
        ResponseEntity responseEntity = new ResponseEntity();
        if (((startPage != null && endPage != null) && startPage > endPage) || startPage == null || endPage == null) {
            List<Category> categoryList = categoryService.getCategoryList();
            responseEntity.setCode(200);
            responseEntity.setData(categoryList);
            responseEntity.setMessage("全量返回");
            
        } else {
            Page<Category> categoryList = categoryService.getCategoryList(startPage, endPage);
            responseEntity.setData(categoryList);
            responseEntity.setCode(200);
            responseEntity.setMessage("OK");
        }
        
        return responseEntity;
    }
    
    @PutMapping("/Category")
    @Operation(summary = "修改分类信息", description = "修改分类信息")
    @ApiResponse(responseCode = "200", description = "成功", content = @Content(mediaType = MediaType.JSON))
    @ApiResponse(responseCode = "422", description = "参数非法", content = @Content(mediaType = MediaType.JSON))
    public ResponseEntity updateCategoryInfo(@RequestBody Category newCategory, HttpServletResponse response) {
        ResponseEntity responseEntity = new ResponseEntity();
        if (newCategory == null || (newCategory.parentCategoryId == null && newCategory.categoryName == null && newCategory.icon == null)) {
            responseEntity.setCode(422);
            responseEntity.setData(null);
            responseEntity.setMessage("数据为空");
            response.setStatus(422);
        } else {
            if (newCategory.id != null && getCategoryInfo(newCategory.id).data != null) {
                newCategory.createTime = null;
                // 计算新层级
                if (newCategory.parentCategoryId != null && !newCategory.parentCategoryId.isEmpty()) {
                    Category parent = (Category) getCategoryInfo(newCategory.parentCategoryId).data;
                    if (parent != null) {
                        newCategory.setLevel(parent.level + 1);
                        newCategory.setParentCategoryId(parent.id);
                    }
                } else {
                    newCategory.setLevel(1);
                    newCategory.setParentCategoryId(null);
                }
                int i = categoryService.updateCategory(newCategory);
                responseEntity.setCode(200);
                responseEntity.setMessage("更新 ==>" + i + " 条 =>" + newCategory);
            } else {
                responseEntity.setCode(422);
                responseEntity.setData(null);
                responseEntity.setMessage("没有该分类的信息");
                response.setStatus(422);
            }
        }
        return responseEntity;
    }
    
    
}
