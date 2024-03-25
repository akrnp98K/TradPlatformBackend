package com.xiaojingye.wechatbackend.entity.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_cart")
public class Cart {
    @TableId
    private String id;
    
    private Integer userId;
    
    private  String commodityId;
    
    private  Integer quantity;
    
    private BigDecimal price;
    
    private Date createTime;
    
}
