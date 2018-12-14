package com.html2pdf;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfCopy;
import com.lowagie.text.pdf.PdfImportedPage;
import com.lowagie.text.pdf.PdfReader;
import com.pdf.BasePDFWrite;
import com.util.ConfigManager;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 * PDFGenUtil 利用phantomjs 抓取网页生成pdf
 * @author neil.zhou
 * @Date 2016/04/44
 * @package com.rayeye.law.app.utils
 * @version 1.0
 */
public class PDFGenUtil {
    private static Logger logger = LogManager.getLogger(PDFGenUtil.class.getName());

    /**
     * 调用web页面渲染并生成pdf
     * @param httpUrl 需要转换的地址
     * @param saveToPath 保存路径
     */
    public static Boolean grabHTMLToPDF(String httpUrl,String saveToPath,String fileName) throws Exception {
        Runtime rt = Runtime.getRuntime();
        Process process = null;
        File file=new File(saveToPath+File.separator);
        if(!file.exists()){
            file.mkdirs();
        }
        //get url
        try {
            try {
                String cmd = ConfigManager.getInstance().getString("pdf.gen.tool").replace("#{url}", httpUrl).replace("#{path}", file.getPath() + File.separator + fileName);
                logger.debug("jvm command==>{}"+ cmd);
                process = rt.exec(cmd);
                return true;
            } catch (Exception e) {
                final String msg = "can't load config file";
                throw new RuntimeException(msg);
            }
        }catch (Exception e){
            logger.error("PDF 生成失败.{}"+e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    /*
     * * 合並pdf文件 * * @param files 要合並文件數組(絕對路徑如{ "e:\\1.html2pdf", "e:\\2.html2pdf" ,
     * "e:\\3.html2pdf"}) * @param newfile
     * 合並後新產生的文件絕對路徑如e:\\temp.html2pdf,請自己刪除用過後不再用的文件請 * @return boolean
     * 產生成功返回true, 否則返回false
     */
    public static boolean mergePdfFiles(String[] files, String newfile) {
        boolean retValue = false;
        Document document = null;
        try {
            document = new Document(new PdfReader(files[0]).getPageSize(1));
            PdfCopy copy = new PdfCopy(document, new FileOutputStream(newfile));
            document.open();
            for (int i = 0; i < files.length; i++) {
                PdfReader reader = new PdfReader(files[i]);
                int n = reader.getNumberOfPages();
                for (int j = 1; j <= n; j++) {
                    document.newPage();
                    PdfImportedPage page = copy.getImportedPage(reader, j);
                    copy.addPage(page);
                }
            }
            retValue = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            document.close();
        }
        return retValue;
    }


    public  static void main(String[] args) throws Exception {
        grabHTMLToPDF("https://www.json.cn/","F:/PDF/",("test.html2pdf"));

/*        String[] files = { "D:\\temp\\test.html2pdf", "D:\\temp\\test1.html2pdf"};
        String savepath = "D:\\temp\\report.html2pdf";
        mergePdfFiles(files, savepath);*/
    }
}

