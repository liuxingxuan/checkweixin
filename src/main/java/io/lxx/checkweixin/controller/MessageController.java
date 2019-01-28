package io.lxx.checkweixin.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.lxx.checkweixin.dto.MessageAutoResponseDTO;
import io.lxx.checkweixin.dto.MessageTextDTO;
import io.lxx.checkweixin.po.User;
import io.lxx.checkweixin.service.impl.UserServiceImpl;
import io.lxx.checkweixin.service.impl.WeixinClientImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/message")
public class MessageController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private WeixinClientImpl weixinClient;
    @Autowired
    private UserServiceImpl userService;

    @Value("${weixin.accessToken}")
    private String accessToken;

    @Value("${checkInOut.latitude}")
    private Double checkLatitude;

    @Value("${checkInOut.longitude}")
    private Double checkLongitude;

    @PostMapping(value = "/receive", produces = MediaType.APPLICATION_XML_VALUE)
    public Object receive2(@RequestBody JSONObject messageReceiveDTO) throws Exception {
        logger.info("{}", JSON.toJSONString(messageReceiveDTO));
        String msgType = messageReceiveDTO.getString("MsgType");
        if (msgType.equals("event")) {
            String event = messageReceiveDTO.getString("Event");
            if (event.equals("subscribe")) {
                String fromUserName = messageReceiveDTO.getString("FromUserName");
                JSONObject userInfo = weixinClient.getUserInfo(accessToken, fromUserName);
                logger.info("{}", userInfo);
                String openid = userInfo.getString("openid");
                if (openid == null || openid.isEmpty()) {
                    throw new Exception("openId is null, check access token");
                }
                User userOrigin = userService.getById(openid);
                if (userOrigin != null) {
                    return "success";
                }
                User user = new User();
                user.setOpenid(openid);
                String nickname = userInfo.getString("nickname");
                Integer gender = userInfo.getInteger("sex");
                String avatarUrl = userInfo.getString("headimgurl");
                user.setNickname(nickname);
                user.setGender(gender);
                user.setAvatarUrl(avatarUrl);
                userService.create(user);

                MessageAutoResponseDTO messageAutoResponseDTO = new MessageAutoResponseDTO();
                messageAutoResponseDTO.setToUserName(fromUserName);
                String toUserName = messageReceiveDTO.getString("ToUserName");
                messageAutoResponseDTO.setFromUserName(toUserName);
                messageAutoResponseDTO.setCreateTime(new Date().getTime());
                messageAutoResponseDTO.setMsgType("text");
                messageAutoResponseDTO.setContent(String.format("您好，%s,欢迎订阅", nickname));
                return messageAutoResponseDTO;
            }

            if (event.equals("CLICK")) {
                String eventKey = messageReceiveDTO.getString("EventKey");
                if (eventKey == null) {
                    return "success";
                }
                if (eventKey.equals("checkinout")) {
                    String fromUserName = messageReceiveDTO.getString("FromUserName");
                    String positionUserKey = "position" + fromUserName;

                }
                return "success";
            }

        }
        return null;
    }
}
