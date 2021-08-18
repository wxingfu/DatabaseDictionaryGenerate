package com.wxf.controller;


import com.wxf.dto.ConnDto;
import com.wxf.entity.Table;
import com.wxf.service.DatabaseService;
import com.wxf.utils.ExportUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/database")
public class DatabaseController {

    private final DatabaseService databaseService;

    public DatabaseController(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    @PostMapping("/dict/export")
    public void export(
            HttpServletRequest request, HttpServletResponse response,
            ConnDto connDto) {

        if (connDto != null) {
            List<Table> tables = databaseService.getTables(connDto);
            String dbname = connDto.getDbname();
            ExportUtil.export(response, dbname, tables);
        }
    }
}
