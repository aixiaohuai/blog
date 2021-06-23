package com.medusa.blog.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.medusa.blog.model.File;
import org.springframework.stereotype.Repository;

@Repository
public interface FileMapper extends BaseMapper<File> {
    File selectByMd5(String md5);
}
