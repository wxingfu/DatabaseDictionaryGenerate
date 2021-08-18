package com.wxf;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.config.Configure;
import com.deepoove.poi.plugin.table.LoopRowTableRenderPolicy;
import com.wxf.entity.Column;
import com.wxf.entity.Table;
import com.wxf.service.DatabaseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class AppTests {

    @Autowired
    private DatabaseService databaseService;

    @Test
    public void test1() {

    }

    @Test
    public void test2() {

    }

    @Test
    public void test3() {

        List<Column> columns = new ArrayList<>();
        Column column = new Column();
        column.setField("username");
        column.setType("varchar(20)");
        // column.setCollation("utf8mb4_0900_ai_ci");
        column.setNull("YES");
        column.setKey("PRI");
        column.setDefault("");
        column.setExtra("");
        // column.setPrivileges("select,insert,update,references");
        column.setComment("微信openid");

        columns.add(column);
        columns.add(column);
        columns.add(column);
        columns.add(column);

        // columnInfo.setColumns(columns);
        // Map<String, Object> map = new HashMap<>();
        // map.put("Columns", columns);

        List<Table> tables = new ArrayList<>();
        Table table = new Table();
        table.setTableName("test");
        table.setColumns(columns);

        tables.add(table);
        tables.add(table);
        tables.add(table);
        tables.add(table);

        Map<String, Object> map = new HashMap<>();
        map.put("tables", tables);

        LoopRowTableRenderPolicy policy = new LoopRowTableRenderPolicy();
        Configure config = Configure.builder().bind("Columns", policy).build();

        try {
            File file = ResourceUtils.getFile("classpath:templates/dict.docx");
            XWPFTemplate template = XWPFTemplate.compile(file, config).render(map);

            FileOutputStream outputStream =
                    new FileOutputStream("F:\\out_dict.docx");

            template.writeAndClose(outputStream);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
