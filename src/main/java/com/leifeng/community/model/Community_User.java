package com.leifeng.community.model;

import lombok.Data;

/**
 * Created by ThinkPad on 2020/1/25.
 */

@Data
public class Community_User {
    private Long id;
    private String account_id;
    private String name;
    private String token;
    private Long gmt_create;
    private Long gmt_modified;
    private String bio;
    private String avatarUrl;

}
