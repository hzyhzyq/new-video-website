package com.yunqi.video.service.comment;

import com.mysql.cj.util.StringUtils;
import com.yunqi.video.dao.CommentDao;
import com.yunqi.video.dao.LoginDao;
import com.yunqi.video.domain.bean.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    LoginDao loginDao;
    @Autowired
    CommentDao commentDao;

    @Override
    public String upload(Comment comment) {
        commentDao.insertComment(comment);
        return comment.getCommentId();
    }

    @Override
    public List<Comment> getComments(String videoId) {
        //没有弹幕
        String maxProgress = commentDao.getMaxProgress(videoId);
        if(StringUtils.isNullOrEmpty(maxProgress)){
            return new ArrayList<>();
        }
        //排序加去重
        TreeSet<Comment> comments = new TreeSet<>(new Comparator<Comment>() {
            @Override
            public int compare(Comment o1, Comment o2) {
                if(o1.getProgress() == null){
                    return -1;
                }
                if(o1.getProgress().equals(o2.getProgress())){
                    return Integer.parseInt(o1.getCommentId()) - Integer.parseInt(o2.getCommentId());
                }
                return Integer.parseInt(o1.getProgress()) - Integer.parseInt(o2.getProgress());
            }
        });
        Set<Comment> progress = commentDao.getProgress(videoId, (Integer.parseInt(maxProgress)*3)+"");
        comments.addAll(progress);
        Set<Comment> top = commentDao.getTop(videoId);
        comments.addAll(top);
        return new ArrayList<>(comments);
    }

    @Override
    public Comment getCommentById(String commentId) {
        return commentDao.getCommentById(commentId);
    }
    @Override
    public List<Comment> getAllComments(String videoId) {
        return commentDao.getAllComment(videoId);
    }
}
