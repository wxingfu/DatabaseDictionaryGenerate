package com.wxf.entity;

import lombok.Data;

@Data
public class Column {

    private String Field;
    private String Type;
    // private String Collation;
    private String Null;
    private String Key;
    private String Default;
    private String Extra;
    // private String Privileges;
    private String Comment;
}
