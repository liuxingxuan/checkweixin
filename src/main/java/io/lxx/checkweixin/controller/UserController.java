package io.lxx.checkweixin.controller;

import com.alibaba.fastjson.JSONObject;
import io.lxx.checkweixin.dto.CheckInOutDTO;
import io.lxx.checkweixin.service.UserService;
import io.lxx.checkweixin.service.impl.WeixinClientImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Date;

@RestController
@EnableAutoConfiguration
@RequestMapping("/user")
public class UserController {

    @Autowired
    private WeixinClientImpl weixinClient;
    @Autowired
    private UserService userService;

    @PostMapping("/checkInOut")
    public void checkInOut(@RequestBody CheckInOutDTO checkInOutDTO) throws IOException {
        JSONObject snsAccessToken = weixinClient.getSnsAccessToken(checkInOutDTO.getCode());
        String openid = snsAccessToken.getString("openid");
        userService.checkInOut(openid , new Date());
    }

}
