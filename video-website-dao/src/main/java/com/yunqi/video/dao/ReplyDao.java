package com.yunqi.video.dao;

import com.yunqi.video.domain.bean.Reply;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ReplyDao {
    @Select("SELECT * FROM reply WHERE comment_id = #{commentId} ORDER BY create_time DESC LIMIT 4")
    List<Reply> getTopReply(String commentId);

    @Select("SELECT * FROM reply WHERE comment_id = #{commentId} ORDER BY create_time")
    List<Reply> getAllReply(String commentId);

    @Insert("INSERT INTO reply (reply_content,user_id,user_name,comment_id,create_time,user_avatar)\n" +
            "VALUES (#{replyContent},#{userId},#{userName},#{commentId},#{createTime},#{userAvatar})")
    void sendReply(Reply reply);
}
