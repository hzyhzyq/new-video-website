package com.yunqi.video.service.video;

import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.yunqi.video.dao.LoginDao;
import com.yunqi.video.dao.VideoDao;
import com.yunqi.video.domain.bean.Video;
import com.yunqi.video.util.FdfsUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class VideoServerImpl implements VideoServer {
    @Autowired
    private FastFileStorageClient fastFileStorageClient;
    @Autowired
    private VideoDao videoDao;
    @Autowired
    private LoginDao loginDao;


    @Override
    public List<Video> getRecommendVideo(String type) {
        List<Video> list = new ArrayList<>();
        if ("all".equals(type)) {
            list = videoDao.getRecommendVideo();
        } else {
            list = videoDao.getRecommendVideoWithType(type);
        }
        return list;
    }

    @Override
    public String upload(MultipartFile uploadInfo) throws IOException {
        FdfsUtil fdfsUtil = new FdfsUtil(fastFileStorageClient);
        String upload = null;
        upload = fdfsUtil.upload(uploadInfo);
        return upload;
    }


    @Transactional
    @Override
    public void delete(Video video) {
        try {
            videoDao.deleteByVideoId(Integer.parseInt(video.getId()));
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException("数据库异常");
        }
        try {
            FdfsUtil fdfsUtil = new FdfsUtil(fastFileStorageClient);
            fdfsUtil.delete(video.getVideoUrl());
            fdfsUtil.delete(video.getPictureUrl());
        } catch (Exception e) {
            throw new RuntimeException("服务器异常");
        }
    }

    @Override
    public void remove(String url) {
        try {
            FdfsUtil fdfsUtil = new FdfsUtil(fastFileStorageClient);
            fdfsUtil.delete(url);
        } catch (Exception e) {
            throw new RuntimeException("服务器异常");
        }
    }

    @Override
    public Video getVideoById(Integer videoId) {
        Video videoById = videoDao.getVideoById(videoId);
        return videoById;
    }

    @Override
    public void upload(Video video){
        videoDao.insertVideo(video);
    }
}
