package com.yunqi.video.provider;

import org.apache.ibatis.jdbc.SQL;

public class UserProvider {
    public String getUserInfoByUserEmail() {
        return new SQL() {
            {
                SELECT("id,login_name,user_password,user_email");
                FROM("user");
                WHERE("user_email= #{userEmail}");
            }
        }.toString();
    }

    public String getUserInfoByUserName() {
        return new SQL() {
            {
                SELECT("id,login_name,user_password,user_email");
                FROM("user");
                WHERE("login_name= #{userName}");
            }
        }.toString();
    }
}
