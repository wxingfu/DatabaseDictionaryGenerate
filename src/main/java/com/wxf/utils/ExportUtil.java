package com.wxf.utils;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.config.Configure;
import com.deepoove.poi.plugin.table.LoopRowTableRenderPolicy;
import com.deepoove.poi.util.PoitlIOUtils;
import com.wxf.entity.Table;
import org.springframework.util.ResourceUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExportUtil {

    public static void export(HttpServletResponse response, String dbname, List<Table> tables) {
        Map<String, Object> map = new HashMap<>();
        map.put("tables", tables);

        LoopRowTableRenderPolicy policy = new LoopRowTableRenderPolicy();
        Configure config = Configure.builder().bind("Columns", policy).build();

        OutputStream out = null;
        BufferedOutputStream bos = null;
        XWPFTemplate template = null;

        try {
            File file = ResourceUtils.getFile("classpath:templates/dict.docx");
            template = XWPFTemplate.compile(file, config).render(map);

            response.setContentType("application/octet-stream");
            response.setHeader(
                    "Content-disposition",
                    "attachment;filename=\"" + dbname + ".docx" + "\"");
            out = response.getOutputStream();
            bos = new BufferedOutputStream(out);
            template.write(bos);
            bos.flush();
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            PoitlIOUtils.closeQuietlyMulti(template, bos, out);
        }
    }
}
