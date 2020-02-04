package com.leifeng.community.model;

import lombok.Data;

/**
 * Created by ThinkPad on 2020/1/28.
 */
@Data
public class Community_Question {
    private Long id;
    private String title;
    private String description;
    private Long gmt_create;
    private Long gmt_modified;
    private int creator;
    private int comment_count;
    private int view_count;
    private int like_count;
    private String tag;
}
