package com.xiaojingye.wechatbackend.entity.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_commodity")
public class Commodity {
    @TableId
    public String id;

    public String categoryId;

    public String commodityName;

    public Integer items_quantity;

    public String location;

    public String thumbnail;
}
