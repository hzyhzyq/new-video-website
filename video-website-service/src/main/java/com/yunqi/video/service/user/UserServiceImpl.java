package com.yunqi.video.service.user;

import com.yunqi.video.dao.LoginDao;
import com.yunqi.video.domain.bean.User;
import com.yunqi.video.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private LoginDao loginDao;

    @Override
    public User getUserInfoByUserID(String id) {
        return loginDao.getUserInfoByUserID(id);
    }

    /**
     * 查找用户名或者用户Email是否存在
     * @param principal
     * @return
     */
    @Override
    public User login(String principal) {
        User user=null;
        if(StringUtil.isEmail(principal)){
            user= loginDao.getUserInfoByUserEmail(principal);
        }else {
            user=loginDao.getUserInfoByUserName(principal);
        }
        return user;
    }
}
