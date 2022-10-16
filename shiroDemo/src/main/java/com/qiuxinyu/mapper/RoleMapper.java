package com.qiuxinyu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qiuxinyu.entity.Perm;
import com.qiuxinyu.entity.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleMapper extends BaseMapper<Role> {
    List<Perm> getPermsByRole(String role);
}
