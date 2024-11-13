package com.kim.cloud.mapper;

import com.kim.cloud.entities.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    User login(User user);
}
