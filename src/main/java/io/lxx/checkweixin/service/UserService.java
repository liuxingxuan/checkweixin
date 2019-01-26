package io.lxx.checkweixin.service;

import io.lxx.checkweixin.po.User;

import java.util.Date;

public interface UserService {
    User getById(String openId);

    void create(User user);

    void checkInOut(String openId, Date time);
}
