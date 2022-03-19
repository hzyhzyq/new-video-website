package com.yunqi.video.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginDao {

    @Insert("INSERT INTO demo (name) VALUES (1001)")
    void testt();
}
