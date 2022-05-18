package com.yunqi.video.domain.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    private String commentId;
    private String progress;
    private String content;
    private String userId;
    private String userName;
    private String userAvatar;
    private String videoId;
    private String inBox;
    private Date createTime;
    private List<Reply> reply;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return commentId.equals(comment.commentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commentId);
    }
}
