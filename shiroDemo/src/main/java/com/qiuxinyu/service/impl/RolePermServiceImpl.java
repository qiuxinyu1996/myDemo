package com.qiuxinyu.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qiuxinyu.entity.relationship.RolePerm;
import com.qiuxinyu.service.RolePermService;
import org.springframework.stereotype.Service;

@Service
public class RolePermServiceImpl extends ServiceImpl<BaseMapper<RolePerm>,RolePerm> implements RolePermService {
}
