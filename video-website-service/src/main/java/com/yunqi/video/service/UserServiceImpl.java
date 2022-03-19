package com.yunqi.video.service;

import com.yunqi.video.dao.LoginDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private LoginDao loginDao;


    @Override
    @Transactional(
            propagation = Propagation.REQUIRED,
            isolation = Isolation.DEFAULT,
            rollbackFor = {RuntimeException.class}
    )
    public void testt(){
        try {
            loginDao.testt();
            System.out.println();
            //int i = 0/0;
        }catch (Exception e){
            throw new RuntimeException();
        }

    }

}
