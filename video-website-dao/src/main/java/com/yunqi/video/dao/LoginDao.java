package com.yunqi.video.dao;

import com.yunqi.video.domain.bean.User;
import com.yunqi.video.provider.UserProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

@Mapper
public interface LoginDao {

    @Select("SELECT * FROM user WHERE id = #{id}")
    User getUserInfoByUserID(String id);

    /**
     * 通过用户Email查找用户
     * @param userEmail
     * @return
     */
    @SelectProvider(type = UserProvider.class, method = "getUserInfoByUserEmail")
    User getUserInfoByUserEmail(String userEmail);

    /**
     * 通过用户名查找用户
     * @param userName
     * @return
     */
    @SelectProvider(type = UserProvider.class, method = "getUserInfoByUserName")
    User getUserInfoByUserName(String userName);
}
