package com.qiuxinyu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qiuxinyu.entity.Role;
import com.qiuxinyu.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
