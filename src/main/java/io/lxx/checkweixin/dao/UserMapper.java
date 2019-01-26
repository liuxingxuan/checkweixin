package io.lxx.checkweixin.dao;

import io.lxx.checkweixin.po.User;

public interface UserMapper {
    int deleteByPrimaryKey(String openid);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String openid);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}