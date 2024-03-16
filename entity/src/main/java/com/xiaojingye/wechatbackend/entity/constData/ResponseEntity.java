package com.xiaojingye.wechatbackend.entity.constData;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseEntity {
    public Object data;
    public Integer code;
    public String message;
}
