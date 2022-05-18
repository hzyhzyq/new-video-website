package com.yunqi.video.service.reply;

import com.yunqi.video.dao.ReplyDao;
import com.yunqi.video.domain.bean.Reply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReplyServiceImpl implements ReplyService{
    @Autowired
    ReplyDao replyDao;

    @Override
    public List<Reply> getTopReply(String commentId){
        return replyDao.getTopReply(commentId);
    }

    @Override
    public  List<Reply> getReply(String commentId){
        return replyDao.getAllReply(commentId);
    }

    @Override
    public void sendReply(Reply reply) {
        replyDao.sendReply(reply);
    }
}
