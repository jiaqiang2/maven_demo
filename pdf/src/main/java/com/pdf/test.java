package com.pdf;

import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.util.ConfigManager;
import org.apache.log4j.Logger;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.*;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class test {

    private static Logger logger = Logger.getLogger(test.class);

    public static void createPDF(Map map, String fileUrl){
            // 模板路径
            String templatePath = ConfigManager.getInstance().getString("modelUrl22222");

            try {
                com.itextpdf.text.pdf.PdfReader reader;
                com.itextpdf.text.pdf.PdfStamper stamper;
                AcroFields form;
                reader = new PdfReader(templatePath);//读取pdf模板
                stamper = new PdfStamper(reader, new FileOutputStream(fileUrl));
                form = stamper.getAcroFields();

                Iterator<String> it = form.getFields().keySet().iterator();
                while (it.hasNext()) {
                    String name = it.next().toString();
                    if (name.equals("name")) {
                        form.setField(name, (String) map.get("name"));
                    }
                    if (name.equals("name1")) {
                        form.setField(name, (String) map.get("name1"));
                    }
                    if (name.equals("name2")) {
                        form.setField(name, (String) map.get("name2"));
                    }

                }
                stamper.setFormFlattening(true);//如果为false那么生成的PDF文件还能编辑，一定要设为true
                stamper.close();
                reader.close();
            } catch(Exception e1){
                logger.error(e1.getMessage(), e1);
            }
    }

    public static void main(String[] args) {
        Map map  = new HashMap(){{
            put("name","哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈");
            put("name1","啊的减肥靠近警方南方a");
            put("name2","沈佳俊");
        }};
        createPDF(map, ConfigManager.getInstance().getString("fileUrl").concat("啊大大撒.pdf"));
    }
}
