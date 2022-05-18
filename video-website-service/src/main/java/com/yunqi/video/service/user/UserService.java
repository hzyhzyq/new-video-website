package com.yunqi.video.service.user;

import com.yunqi.video.domain.bean.User;

public interface UserService {
    User getUserInfoByUserID(String id);

    User login(String principal);
}
