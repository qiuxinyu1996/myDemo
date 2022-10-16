package com.qiuxinyu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qiuxinyu.entity.Role;
import com.qiuxinyu.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    List<Role> getRolesByUsername(String username);
}
