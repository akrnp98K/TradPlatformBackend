package com.xiaojingye.wechatbackend.entity.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_category")
public class Category {
    @TableId(type = IdType.AUTO)
    public String id;
    public String categoryName;
    public String icon;
    public Integer level = 1;
    public String parentCategoryId;
    public Date createTime;
}
