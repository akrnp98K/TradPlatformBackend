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
@TableName("t_commodity")
public class Commodity {
    @TableId
    public String id;

    public String categoryId;
    
    public String publisherId;

    public String commodityName;

    public Integer itemsQuantity;
    
    public BigDecimal price;

    public String location;

    public String thumbnail;
    
    public String phoneNumber;
    
    public String productDescription;
    
    public Date createTime;
}
