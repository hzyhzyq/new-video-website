package com.yunqi.video.service.comment;

import com.yunqi.video.domain.bean.Comment;

import java.util.List;

public interface CommentService {
    String upload(Comment comment);
    List<Comment> getComments(String videoId);
    Comment getCommentById(String commentId);
}
