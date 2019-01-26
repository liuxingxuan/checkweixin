package io.lxx.checkweixin.service.impl;

import io.lxx.checkweixin.dao.CheckInOutRecordMapper;
import io.lxx.checkweixin.dao.UserMapper;
import io.lxx.checkweixin.po.CheckInOutRecord;
import io.lxx.checkweixin.po.User;
import io.lxx.checkweixin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CheckInOutRecordMapper checkInOutRecordMapper;

    @Override
    public User getById(String openId) {
        User user = userMapper.selectByPrimaryKey(openId);
        return user;
    }

    @Override
    public void create(User user) {
        userMapper.insert(user);
    }

    @Override
    public void checkInOut(String openId, Date time) {
        CheckInOutRecord checkInOutRecord = new CheckInOutRecord();
        checkInOutRecord.setOpenid(openId);
        checkInOutRecord.setTime(time);

        checkInOutRecordMapper.insert(checkInOutRecord);
    }
}
