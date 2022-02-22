package com.yunqi.videowebsite.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface DemoDao {
    public Long query(@Param("id") Long id);
}
