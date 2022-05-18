package com.yunqi.video.api;

import com.yunqi.video.domain.bean.Comment;
import com.yunqi.video.domain.bean.Reply;
import com.yunqi.video.domain.bean.User;
import com.yunqi.video.domain.constant.ErrorInfo;
import com.yunqi.video.domain.response.JsonResponse;
import com.yunqi.video.dto.BulletChattingList;
import com.yunqi.video.service.comment.CommentService;
import com.yunqi.video.service.reply.ReplyService;
import com.yunqi.video.service.user.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    CommentService commentService;
    @Autowired
    ReplyService replyService;
    @Autowired
    private UserService userService;

    @GetMapping("/get_comment")
    public JsonResponse<BulletChattingList> upload(@RequestParam("videoId") String videoId){
        BulletChattingList chattingList = new BulletChattingList();
        List<Comment> comments = commentService.getComments(videoId);
        Iterator<Comment> iterator = comments.iterator();
        while (iterator.hasNext()){
            Comment next = iterator.next();
            String commentId = next.getCommentId();
            List<Reply> topReply = replyService.getTopReply(commentId);
            next.setReply(topReply);
        }
        chattingList.setBulletChattingList(comments);
        JsonResponse<BulletChattingList> response = new JsonResponse<>(chattingList);
        return response;
    }
    @PostMapping("/send_comment")
    public JsonResponse<String> send(@RequestParam("videoId") String videoId,@RequestParam("commentContent") String commentContent,@RequestParam(value = "progress",required = false) Integer progress,@RequestParam("inBox")String inBox){
        if(!"1".equals(inBox)&&!"0".equals(inBox)){
            return new JsonResponse<>(ErrorInfo.PARAM_ERROR);
        }
        //从session中获取用户信息
        Subject subject = SecurityUtils.getSubject();
        String currentUserId = (String)subject.getSession().getAttribute("currentUserId");
        User userInfoByUserID = userService.getUserInfoByUserID(currentUserId);
        //
        Comment comment = new Comment();
        comment.setProgress(progress+"");
        comment.setContent(commentContent);
        comment.setUserId(currentUserId);
        comment.setUserName(userInfoByUserID.getLoginName());
        comment.setUserAvatar(userInfoByUserID.getUserAvatar());
        comment.setCreateTime(new Date());
        comment.setVideoId(videoId);
        comment.setInBox(inBox);
        String id = commentService.upload(comment);
        return new JsonResponse<>(id);
    }
}
