package com.wxf.service;

import com.wxf.dto.ConnDto;
import com.wxf.dto.ConnInfo;
import com.wxf.entity.DriverClass;
import com.wxf.entity.Table;
import com.wxf.entity.Type;
import com.wxf.utils.DatabaseUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DatabaseServiceImpl implements DatabaseService {

    @Override
    public List<Table> getTables(ConnDto connDto) {

        String type = connDto.getType();
        String driverClass = DriverClass.getClazz(Type.valueOf(type));
        String username = connDto.getUsername();
        String password = connDto.getPassword();
        String url = connDto.getUrl();
        String dbname = connDto.getDbname();
        ConnInfo connInfo = new ConnInfo(username, password, url, driverClass, dbname);

        return DatabaseUtil.getTables(connInfo);
    }
}
