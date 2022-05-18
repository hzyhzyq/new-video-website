package com.yunqi.video.domain.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String id;
    private String loginName;
    private String userEmail;
    private String userPassword;
    private String userAvatar;
}
