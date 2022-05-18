package com.yunqi.video.api;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yunqi.video.domain.bean.Comment;
import com.yunqi.video.domain.bean.Reply;
import com.yunqi.video.domain.bean.User;
import com.yunqi.video.domain.response.JsonResponse;
import com.yunqi.video.dto.CommentData;
import com.yunqi.video.service.comment.CommentService;
import com.yunqi.video.service.reply.ReplyService;
import com.yunqi.video.service.user.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/reply")
public class ReplyController {
    @Autowired
    ReplyService replyService;
    @Autowired
    CommentService commentService;
    @Autowired
    UserService userService;
    @GetMapping("/get_reply")
    public JsonResponse<CommentData> getReply(@RequestParam("commentId") String commentId,@RequestParam("page") Integer page){
        Comment commentById = commentService.getCommentById(commentId);
        Page<Reply> helper = PageHelper.startPage(page, 3);
        List<Reply> reply = replyService.getReply(commentId);
        long total = helper.getTotal();
        //
        CommentData data = new CommentData();
        data.setReplies(reply);
        data.setComment(commentById);
        data.setTotal(total);
        return new JsonResponse<>(data);
    }
    @PostMapping("/send_reply")
    public JsonResponse<String> sendReply(@RequestParam("commentId") String commentId,@RequestParam("replyContent") String replyContent){
        Subject subject = SecurityUtils.getSubject();
        String currentUserId = (String)subject.getSession().getAttribute("currentUserId");
        User userInfoByUserID = userService.getUserInfoByUserID(currentUserId);
        Reply reply = new Reply();
        reply.setReplyContent(replyContent);
        reply.setCommentId(commentId);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        reply.setCreateTime(dateFormat.format(new Date()));
        //
        reply.setUserId(userInfoByUserID.getId());
        reply.setUserName(userInfoByUserID.getLoginName());
        reply.setUserAvatar(userInfoByUserID.getUserAvatar());
        replyService.sendReply(reply);
        return new JsonResponse<>("success");
    }
}
