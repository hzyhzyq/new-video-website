package com.yunqi.video.domain.bean;

import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private int id;
    private String email;
    private String password;
    private Date createDate;
    private Date updateDate;
}
