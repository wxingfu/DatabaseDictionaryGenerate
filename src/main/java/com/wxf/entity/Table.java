package com.wxf.entity;

import lombok.Data;

import java.util.List;

@Data
public class Table {

    private String tableName;
    private String tableType;
    private List<Column> columns;

}
