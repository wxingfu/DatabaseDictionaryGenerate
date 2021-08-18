package com.wxf.utils;

import com.wxf.dto.ConnInfo;
import com.wxf.entity.Column;
import com.wxf.entity.Table;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseUtil {

    /**
     * 获取数据库所有表信息
     *
     * @return
     */
    public static List<Table> getTables(ConnInfo connInfo) {
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        List<Table> tableList = new ArrayList<>();
        try {
            connection = getConnection(connInfo);
            //获取表名
            statement = connection.createStatement();
            String sql = "show full tables from `" + connInfo.getDbname() + "` where Table_type='BASE TABLE'";
            resultSet = statement.executeQuery(sql);
            Table table = null;
            while (resultSet.next()) {
                table = new Table();
                // 表名
                table.setTableName(resultSet.getString(1));
                // 表字段
                List<Column> columns = getColumns(connection, table.getTableName());
                table.setColumns(columns);
                tableList.add(table);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            release(connection, null, resultSet, statement);
        }
        return tableList;
    }

    public static List<Column> getColumns(Connection connection, String tableName) {
        Statement stm = null;
        ResultSet rs = null;
        List<Column> columns = new ArrayList<>();
        try {
            stm = connection.createStatement();
            String sql = "show full columns from `" + tableName + "`";
            rs = stm.executeQuery(sql);
            Column column = null;
            while (rs.next()) {
                column = new Column();
                column.setField(rs.getString("Field"));
                column.setType(rs.getString("Type"));
                column.setNull(rs.getString("Null"));
                column.setKey(rs.getString("Key"));
                column.setDefault(rs.getString("Default"));
                column.setExtra(rs.getString("Extra"));
                column.setComment(rs.getString("Comment"));
                columns.add(column);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            release(null, null, rs, stm);
        }
        return columns;
    }


    /**
     * 数据库连接
     */
    public static Connection getConnection(ConnInfo connInfo) throws Exception {
        Class.forName(connInfo.getDriverClass());
        return DriverManager.getConnection(connInfo.getUrl(), connInfo.getUsername(), connInfo.getPassword());
    }

    /**
     * 释放资源
     */
    public static void release(Connection connection,
                               PreparedStatement preparedStatement,
                               ResultSet resultSet,
                               Statement statement) {
        if (null != resultSet) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (null != statement) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (null != preparedStatement) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (null != connection) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
