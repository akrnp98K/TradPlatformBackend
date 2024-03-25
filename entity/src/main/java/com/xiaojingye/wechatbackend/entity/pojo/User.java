package com.xiaojingye.wechatbackend.entity.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "用户实体类")
public class User {
    @Schema(description = "用户ID",requiredMode = Schema.RequiredMode.REQUIRED)
    public Long id;
    @Schema(description = "账户",requiredMode = Schema.RequiredMode.REQUIRED)
    public String account;
    @Schema(description = "密码",requiredMode = Schema.RequiredMode.REQUIRED)
    public String password;
    @Schema(description = "昵称",requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    public String nickname;
    @Schema(description = "邮件",requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    public String email;
    @Schema(description = "创建时间",requiredMode = Schema.RequiredMode.AUTO)
    public Date createTime;
    @Schema(description = "手机号",requiredMode = Schema.RequiredMode.REQUIRED)
    public String phoneNumber;
    @Schema(description = "地区",requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    public String location;
}
