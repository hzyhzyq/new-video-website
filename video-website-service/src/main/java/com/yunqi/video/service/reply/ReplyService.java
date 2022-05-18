package com.yunqi.video.service.reply;

import com.yunqi.video.domain.bean.Reply;

import java.util.List;

public interface ReplyService {
    List<Reply> getTopReply(String commentId);
    List<Reply> getReply(String commentId);
    void sendReply(Reply reply);
}
