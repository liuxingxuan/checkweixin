package io.lxx.checkweixin.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "xml")//返回类型
public class MessageTextDTO {

    private String ToUserName;

    private String FromUserName;

    private Long CreateTime;

    private String MsgType;

    private String Content;

    @JacksonXmlCData//返回数据类型
    @JacksonXmlProperty(localName = "ToUserName")
    public String getToUserName() {
        return ToUserName;
    }

    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }
    @JacksonXmlCData
    @JacksonXmlProperty(localName = "FromUserName")
    public String getFromUserName() {
        return FromUserName;
    }

    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }
    @JacksonXmlProperty(localName = "CreateTime")
    public Long getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Long createTime) {
        CreateTime = createTime;
    }

    public String getMsgType() {
        return MsgType;
    }
    @JacksonXmlCData
    @JacksonXmlProperty(localName = "MsgType")
    public void setMsgType(String msgType) {
        MsgType = msgType;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}
