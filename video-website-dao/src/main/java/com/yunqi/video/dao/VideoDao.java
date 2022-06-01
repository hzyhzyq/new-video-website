package com.yunqi.video.dao;

import com.yunqi.video.domain.bean.Video;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface VideoDao {
    @Insert("INSERT INTO video (video_name,video_describe,video_owner_id,video_owner_name,video_owner_avatar,video_url,picture_url,video_type)\n" +
            "VALUES(#{videoName},#{videoDescribe},#{videoOwnerId},#{videoOwnerName},#{videoOwnerAvatar},#{videoUrl},#{pictureUrl},#{videoType})")
    Integer insertVideo(Video video);

    @Select("SELECT * FROM video WHERE id = #{id}")
    Video getVideoById(Integer id);

    @Delete("DELETE FROM video WHERE id = #{id}")
    void deleteByVideoId(Integer id);

    @Select("SELECT * FROM video\n" +
            "ORDER BY RAND()\n" +
            "LIMIT 8")
    List<Video> getRecommendVideo();

    @Select("SELECT * FROM video\n" +
            "WHERE video_type=#{videoType}\n" +
            "ORDER BY RAND()\n" +
            "LIMIT 8")
    List<Video> getRecommendVideoWithType(String videoType);

    @Select("SELECT * FROM video\n" +
            "WHERE video_type=#{videoType}\n" +
            "ORDER BY id DESC\n")
    List<Video> getAllVideo(String videoType);
}
