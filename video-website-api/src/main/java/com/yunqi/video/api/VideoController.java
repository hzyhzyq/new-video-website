package com.yunqi.video.api;

import com.mysql.cj.util.StringUtils;
import com.yunqi.video.domain.bean.User;
import com.yunqi.video.domain.bean.Video;
import com.yunqi.video.domain.constant.ErrorInfo;
import com.yunqi.video.domain.response.JsonResponse;
import com.yunqi.video.service.user.UserService;
import com.yunqi.video.service.video.VideoServer;
import com.yunqi.video.util.StringUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/video")
public class VideoController {
    @Autowired
    private VideoServer videoServerImpl;
    @Autowired
    private UserService userService;

    @PostMapping("/upload")
    public JsonResponse<String> upload(@RequestParam("file") MultipartFile file) {
        String upload = null;
        try {
            upload = videoServerImpl.upload(file);
            return new JsonResponse<>(upload);
        } catch (Exception e) {
            return new JsonResponse<>("error");
        }
    }

    @PostMapping("/uploadVideo")
    public JsonResponse<String> uploadVideo(@RequestParam("videoName") String videoName,
                                            @RequestParam("videoDescribe") String videoDescribe,
                                            @RequestParam("videoType") String videoType,
                                            @RequestParam("videoUrl") String videoUrl,
                                            @RequestParam("pictureUrl") String pictureUrl) {
        Video video = new Video();
        video.setVideoName(videoName);
        video.setVideoDescribe(videoDescribe);
        video.setVideoType(videoType);
        video.setVideoUrl(videoUrl);
        video.setPictureUrl(pictureUrl);
        //
        Subject subject = SecurityUtils.getSubject();
        String currentUserId = (String) subject.getSession().getAttribute("currentUserId");
        User userInfoByUserID = userService.getUserInfoByUserID(currentUserId);
        //
        video.setVideoOwnerId(currentUserId);
        video.setVideoOwnerName(userInfoByUserID.getLoginName());
        video.setVideoOwnerAvatar(userInfoByUserID.getUserAvatar());
        videoServerImpl.upload(video);
        return new JsonResponse<>("success");
    }

    @PostMapping("/remove")
    public JsonResponse<String> remove(@RequestParam("url") String url) {
        videoServerImpl.remove(url);
        return new JsonResponse<>("success");
    }

    @GetMapping("/get_video")
    public JsonResponse<Video> get_video(@RequestParam("videoId") Integer videoId) {
        Video videoById = videoServerImpl.getVideoById(videoId);
        String videoUrl = videoById.getVideoUrl();
        String pictureUrl = videoById.getPictureUrl();
        videoById.setVideoUrl(StringUtil.changeToUrl(videoUrl) + "/index.m3u8");
        videoById.setPictureUrl(StringUtil.changeToPictureUrl(pictureUrl));
        return new JsonResponse<>(videoById);
    }

    @PostMapping("/delete")
    public JsonResponse<String> delete(@RequestParam("videoId") Integer videoId) {
        Video videoById = videoServerImpl.getVideoById(videoId);
        if (StringUtils.isNullOrEmpty(videoById.getPictureUrl()) || StringUtils.isNullOrEmpty(videoById.getVideoUrl())) {
            return new JsonResponse<>(ErrorInfo.PARAM_ERROR);
        }
        videoServerImpl.delete(videoById);
        return new JsonResponse<>("success");
    }

    @GetMapping("/get_recommend_video")
    public JsonResponse<List<Video>> getRecommendVideo(@RequestParam(value = "type", required = false, defaultValue = "all") String type) {
        List<Video> recommendVideo = videoServerImpl.getRecommendVideo(type);
        Iterator<Video> iterator = recommendVideo.iterator();
        while (iterator.hasNext()) {
            Video next = iterator.next();
            String videoUrl = next.getVideoUrl();
            String pictureUrl = next.getPictureUrl();
            next.setVideoUrl(StringUtil.changeToUrl(videoUrl) + "/index.m3u8");
            next.setPictureUrl(StringUtil.changeToPictureUrl(pictureUrl));
        }
        return new JsonResponse<>(recommendVideo);
    }
}
