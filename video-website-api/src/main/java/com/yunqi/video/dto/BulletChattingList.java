package com.yunqi.video.dto;

import com.yunqi.video.domain.bean.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BulletChattingList {
    private List<Comment> bulletChattingList;
}
