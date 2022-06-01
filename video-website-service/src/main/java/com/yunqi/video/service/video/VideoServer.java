package com.yunqi.video.service.video;

import com.yunqi.video.domain.bean.Video;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface VideoServer {
    void delete(Video video);

    Video getVideoById(Integer videoId);

    String upload(MultipartFile uploadInfo) throws IOException;

    List<Video> getRecommendVideo(String type);

    List<Video> getAllVideo(String type);

    void remove(String url);

    void upload(Video video);
}
