package com.yunqi.video.domain.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Video {
    private String id;
    private String videoName;
    private String videoDescribe;
    private String videoOwnerId;
    private String videoOwnerName;
    private String videoOwnerAvatar;
    private String videoUrl;
    private String pictureUrl;
    private String videoType;
}
