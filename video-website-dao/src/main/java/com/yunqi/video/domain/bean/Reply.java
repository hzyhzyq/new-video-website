package com.yunqi.video.domain.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reply {
    private String replyId;
    private String replyContent;
    private String replyTo;
    private String userId;
    private String userName;
    private String userAvatar;
    private String commentId;
    private String createTime;
}
