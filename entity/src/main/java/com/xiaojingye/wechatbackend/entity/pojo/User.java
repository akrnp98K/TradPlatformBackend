package com.xiaojingye.wechatbackend.entity.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    public Long id;

    public String account;

    public String password;

    public String nickname;

    public String email;

    public Date createTime;

    public String phoneNumber;
}
