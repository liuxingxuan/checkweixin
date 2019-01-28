package io.lxx.checkweixin.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.lxx.checkweixin.dto.MessageTextDTO;
import io.lxx.checkweixin.service.impl.UserServiceImpl;
import io.lxx.checkweixin.service.impl.WeixinClientImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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


    @GetMapping(value = "/receive",produces = MediaType.APPLICATION_XML_VALUE)
    public MessageTextDTO receive(@RequestBody(required = false) String jsonObject,
                          @RequestParam Map<String, String> allParams,
                          @RequestParam(required = false) String echostr) {
        logger.info("{}", jsonObject);
        logger.info("{}",allParams);
        MessageTextDTO messageTextDTO = new MessageTextDTO();
        messageTextDTO.setToUserName("ogFCw5_TKeS9Bh7RE_lHoijx7BiM");
        messageTextDTO.setFromUserName("gh_6daa2cd1a1b2");
        messageTextDTO.setCreateTime(new Date().getTime());
        messageTextDTO.setMsgType("text");
        messageTextDTO.setContent("llalalala");

        return messageTextDTO;
    }
}
