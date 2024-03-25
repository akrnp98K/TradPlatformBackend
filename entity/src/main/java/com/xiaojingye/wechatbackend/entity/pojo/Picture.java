package com.xiaojingye.wechatbackend.entity.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "t_picture")
public class Picture {
    @TableId
    private String id;
    private String commodityId;
    private String imageUrl;
    private Date createTime;
}
