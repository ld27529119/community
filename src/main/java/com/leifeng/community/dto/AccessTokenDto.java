package com.leifeng.community.dto;

import lombok.Data;

/**
 * Created by ThinkPad on 2020/1/23.
 */

@Data
public class AccessTokenDto {

    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;
}
