package com.wxf.dto;

import lombok.Data;

@Data
public class ConnDto {

    private String username;
    private String password;
    private String url;
    private String dbname;
    private String type;
}
