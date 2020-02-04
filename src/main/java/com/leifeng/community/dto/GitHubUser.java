package com.leifeng.community.dto;

import lombok.Data;

/**
 * Created by ThinkPad on 2020/1/13.
 */
@Data
public class GitHubUser {
    private Long id;
    private String name;
    private String bio;
    private String avatar_url;
}
