package com.xiaojingye.wechatbackend.component.controller;

import com.xiaojingye.wechatbackend.component.service.UserService;
import com.xiaojingye.wechatbackend.entity.constData.MediaType;
import com.xiaojingye.wechatbackend.entity.constData.ResponseEntity;
import com.xiaojingye.wechatbackend.entity.pojo.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "用户",description = "用户数据CRUD")
@RestController()
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @Operation(summary = "按照账号获取全部数据（测试）",description = "按照账号获取数据（仅用于测试-测试账号：root）")
    @GetMapping("/getUser/{account}")
    @ApiResponse(responseCode = "200", description = "查询到信息", content = @Content(mediaType = MediaType.JSON))
    @ApiResponse(responseCode = "404", description = "查无此人", content = @Content(mediaType = MediaType.JSON))
    public Object getUser(@PathVariable(value = "account") String account) {
        User user = userService.getUser(account);
        ResponseEntity responseEntity = new ResponseEntity();
        if (user == null) {
            responseEntity.setData(null);
            responseEntity.setCode(404);
            responseEntity.setMessage("查无此人");
        }else {
            responseEntity.setData(user);
            responseEntity.setCode(200);
            responseEntity.setMessage("OK");
        }
        return responseEntity;
    }


}
