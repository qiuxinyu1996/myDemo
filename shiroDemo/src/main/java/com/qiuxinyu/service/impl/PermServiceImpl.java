package com.qiuxinyu.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qiuxinyu.entity.Perm;
import com.qiuxinyu.service.PermService;
import org.springframework.stereotype.Service;

@Service
public class PermServiceImpl extends ServiceImpl<BaseMapper<Perm>,Perm> implements PermService {
}
