package com.yunqi.video.dto;

import com.yunqi.video.domain.bean.Comment;
import com.yunqi.video.domain.bean.Reply;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentData {
    private Comment comment;
    private Long total;
    private List<Reply> replies;
}
