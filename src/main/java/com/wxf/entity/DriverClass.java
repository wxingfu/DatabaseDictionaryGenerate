package com.wxf.entity;

public class DriverClass {

    private final static String MYSQL57 = "com.mysql.jdbc.Driver";
    private final static String MYSQL80 = "com.mysql.cj.jdbc.Driver";
    private final static String ORACLE = "";
    private final static String SQLSERVER = "";

    public DriverClass() {
    }


    public static String getClazz(Type type) {
        String driverClass = "";
        switch (type) {
            case MySQL57:
                driverClass = MYSQL57;
                break;
            case MySQL80:
                driverClass = MYSQL80;
                break;
            case SqlServer:
                driverClass = SQLSERVER;
                break;
            case Oracle:
                driverClass = ORACLE;
                break;
            default:
                driverClass = "";
                break;
        }
        return driverClass;
    }
}
