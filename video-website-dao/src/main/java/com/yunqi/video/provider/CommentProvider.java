package com.yunqi.video.provider;

import com.yunqi.video.domain.bean.Comment;
import org.apache.ibatis.jdbc.SQL;

public class CommentProvider {
    public String insertComment(final Comment comment) {
        return new SQL() {
            {
                INSERT_INTO("comment");
                if("1".equals(comment.getInBox())){
                    VALUES("progress","#{progress}");
                    VALUES("content","#{content}");
                    VALUES("user_id","#{userId}");
                    VALUES("user_name","#{userName}");
                    VALUES("user_avatar","#{userAvatar}");
                    VALUES("create_time","#{createTime}");
                    VALUES("video_id","#{videoId}");
                    VALUES("in_box","#{inBox}");
                }else {
                    VALUES("content","#{content}");
                    VALUES("user_id","#{userId}");
                    VALUES("user_name","#{userName}");
                    VALUES("video_id","#{videoId}");
                    VALUES("in_box","#{inBox}");
                    VALUES("user_avatar","#{userAvatar}");
                    VALUES("create_time","#{createTime}");
                }
            }
        }.toString();
    }
}
