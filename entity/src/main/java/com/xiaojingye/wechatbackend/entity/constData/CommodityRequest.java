package com.xiaojingye.wechatbackend.entity.constData;

import com.xiaojingye.wechatbackend.entity.pojo.Commodity;
import com.xiaojingye.wechatbackend.entity.pojo.Picture;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommodityRequest {
    private Commodity commodity;
    private List<Picture> pictures;
}
