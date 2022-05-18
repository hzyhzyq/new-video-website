package com.yunqi.video.domain.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {
    private int id;
    private String nickName;
    private String profilePicture;
    private Date createDate;
    private Date updateDate;
}
