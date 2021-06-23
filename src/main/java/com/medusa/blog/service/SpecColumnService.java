package com.medusa.blog.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.medusa.blog.common.enums.SysStates;
import com.medusa.blog.model.SpecColumn;
import com.medusa.blog.mapper.SpecColumnMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecColumnService {
    @Autowired
    private SpecColumnMapper specColumnMapper;

    public List<SpecColumn> queryColumnList() {
        QueryWrapper<SpecColumn> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("COLUMN_STATE", SysStates.ENABLE.value());
        queryWrapper.orderByAsc("COLUMN_SORT");
        return specColumnMapper.selectList(queryWrapper);
    }
}
