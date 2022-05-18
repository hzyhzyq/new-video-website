package com.yunqi.video.dao;

import com.yunqi.video.domain.bean.Comment;
import com.yunqi.video.provider.CommentGetProvider;
import com.yunqi.video.provider.CommentProvider;
import org.apache.ibatis.annotations.*;

import java.util.Set;

@Mapper
public interface CommentDao {
    @SelectKey(statement = "select LAST_INSERT_ID()", keyProperty = "commentId", before = false, resultType = String.class)
    @InsertProvider(value = CommentProvider.class,method = "insertComment")
    void insertComment(Comment comment);

    @SelectProvider(value = CommentGetProvider.class,method = "getProgress")
    Set<Comment> getProgress(@Param("videoId") String videoId, @Param("limit") String limit);

    @SelectProvider(value = CommentGetProvider.class,method = "getTop")
    Set<Comment> getTop(String videoId);

    @Select("SELECT progress FROM comment WHERE video_id = #{videoId} ORDER BY progress DESC LIMIT 1")
    String getMaxProgress(String videoId);

    @Select("SELECT * FROM comment WHERE comment_id = #{commentId}")
    Comment getCommentById(String commentId);


}
