package com.pdf;

import com.itextpdf.text.pdf.*;
import com.itextpdf.text.*;
import com.util.ConfigManager;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.util.*;

public class BasePDFWrite {
    private static Logger logger = Logger.getLogger(BasePDFWrite.class);

    Document document = null;// 建立一个Document对象
    private static Font fontA ;
    private static Font fontB ;
    private static Font fontC ;
    private static Font fontD ;


    static{
        BaseFont bfChinese_H;
        try {
            /**
             * 新建一个字体,iText的方法 STSongStd-Light 是字体，在iTextAsian.jar 中以property为后缀
             * UniGB-UCS2-H 是编码，在iTextAsian.jar 中以cmap为后缀 H 代表文字版式是 横版， 相应的 V 代表竖版
             */
            bfChinese_H = BaseFont.createFont("STSong-Light","UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);

//          测试
            fontA = new Font(bfChinese_H, 21, Font.NORMAL);
            fontB = new Font(bfChinese_H, 18, Font.NORMAL);
            fontC = new Font(bfChinese_H, 10, Font.NORMAL);
            fontD = new Font(bfChinese_H, 12, Font.NORMAL);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public BasePDFWrite() {}


    /**
     * 设置页面属性
     * @param file
     */
    public BasePDFWrite(File file) {

        //自定义纸张
//        Rectangle rectPageSize = new Rectangle(210, 297);
        // 定义A4页面大小
        Rectangle rectPageSize = new Rectangle(PageSize.A4);
//        rectPageSize = rectPageSize.rotate();// 加上这句可以实现页面的横置
        document = new Document(rectPageSize,80, 80, 60, 40);

        try {
            PdfWriter.getInstance(document,new FileOutputStream(file));
            document.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 建表格(以列的数量建)
     * @param colNumber
     * @return
     */
    public PdfPTable createTable(int colNumber){
        PdfPTable table = new PdfPTable(colNumber);
        try{
            //table.setTotalWidth(maxWidth);
            //table.setLockedWidth(true);
            table.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.getDefaultCell().setBorder(1);
            table.setSpacingBefore(10);
            table.setWidthPercentage(100);
        }catch(Exception e){
            e.printStackTrace();
        }
        return table;
    }

    /**
     * 建表格(以列的宽度比建)
     * @param widths
     * @return
     */
    public PdfPTable createTable(float[] widths){
        PdfPTable table = new PdfPTable(widths);
        try{
            //table.setTotalWidth(maxWidth);
            //table.setLockedWidth(true);
            table.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.getDefaultCell().setBorder(1);
            table.setSpacingBefore(10);
            table.setWidthPercentage(100);
        }catch(Exception e){
            e.printStackTrace();
        }
        return table;
    }


    /**
     * 表格中单元格
     * @param value
     * @param font
     * @param align
     * @return
     */
    public PdfPCell createCell(String value, Font font, int align){
        PdfPCell cell = new PdfPCell();
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(align);
        cell.setPhrase(new Phrase(value,font));
        return cell;
    }

    /**
     * 表格中单元格
     * @param value
     * @param font
     * @param colspan
     * @param rowspan
     * @return
     */
    public PdfPCell createCell(String value,Font font,int align_v,int align_h,int colspan,int rowspan){
        PdfPCell cell = new PdfPCell();
        cell.setVerticalAlignment(align_v);
        cell.setHorizontalAlignment(align_h);
        cell.setColspan(colspan);
        cell.setRowspan(rowspan);
        cell.setPhrase(new Phrase(value,font));
        return cell;
    }

    public PdfPCell creaCell(String value,Font font,int align_v,int align_h,int colspan,int rowspan){
        PdfPCell cell = new PdfPCell();
        cell.setVerticalAlignment(align_v);
        cell.setHorizontalAlignment(align_h);
        cell.setColspan(colspan);
        cell.setRowspan(rowspan);
        cell.setPhrase(new Phrase(value,font));
        cell.setBorderWidth(0);
        return cell;
    }

    /**
     * 建短语
     * @param value
     * @param font
     * @return
     */
    public Phrase createPhrase(String value,Font font){
        Phrase phrase = new Phrase();
        phrase.add(value);
        phrase.setFont(font);
        return phrase;
    }

    /**
     * 建段落
     * @param value
     * @param font
     * @param align
     * @return
     */
    public Paragraph createParagraph(String value,Font font,int align){
        Paragraph paragraph = new Paragraph();
        paragraph.add(new Phrase(value,font));
        paragraph.setAlignment(align);
        paragraph.setSpacingAfter(7);
        paragraph.setSpacingBefore(5);
        return paragraph;
    }

    //  按照宽度比 压缩图片
    public int getPercent2(float h,float w)
    {
        int p=0;
        float p2=0.0f;
        p2=530/w*100;
        System.out.println("--"+p2);
        p=Math.round(p2);
        return p;
    }


    public static void createPDF(LinkedHashMap revicedata, LinkedHashMap sendData, Map map, String fileUrl){
        // 模板路径
        String templatePath = ConfigManager.getInstance().getString("modelUrl");

        try {
            PdfContentByte under;
            com.itextpdf.text.pdf.PdfReader reader;
            com.itextpdf.text.pdf.PdfStamper stamper;
            AcroFields form;
            reader = new PdfReader(templatePath);//读取pdf模板
            stamper = new PdfStamper(reader, new FileOutputStream(fileUrl));
            form = stamper.getAcroFields();

            int r = 1;
            int s = 1;
            Iterator<String> it = form.getFields().keySet().iterator();
            while(it.hasNext()){
                String name = it.next().toString();
                if (name.equals("img")) {
                    com.itextpdf.text.Image image;
                    if (Integer.valueOf(map.get("img").toString()) == 1){
//                        image = Image.getInstance("C:\\Users\\wqp\\Desktop\\pass.png");
                        image = com.itextpdf.text.Image.getInstance(ConfigManager.getInstance().getString("pass.url"));
                    }else {
//                        image = Image.getInstance("C:\\Users\\wqp\\Desktop\\pass.png");
                        image = Image.getInstance(ConfigManager.getInstance().getString("nopass.url"));
                    }

                    // 获取操作的页面
                    under = stamper.getOverContent(3);
                    // 根据域的大小缩放图片
                    Rectangle signRect = form.getFieldPositions(name).get(0).position;
                    image.scaleToFit(signRect.getWidth(), signRect.getHeight());
                    float x = signRect.getLeft();
                    float y = signRect.getBottom();
                    // 添加图片
                    image.setAbsolutePosition(x, y);
                    under.addImage(image);
                    continue;
                }
                if (name.equals("reportNum")){
                    form.setField(name, (String) map.get("reportNum"));
                    continue;
                }
                if (name.equals("enterprise")){
                    form.setField(name, (String) map.get("enterprise"));
                    continue;
                }
                if (name.startsWith("hardware")){
                    if (Integer.valueOf(map.get("hardware").toString())==1){
                        form.setField(name, "√");
                    }else {
                        form.setField(name, "×");
                    }
                    continue;
                }
                if (name.startsWith("concurrent")){
                    if (Integer.valueOf(map.get("concurrent").toString())==1){
                        form.setField(name, "√");
                    }else {
                        form.setField(name, "×");
                    }

                    continue;
                }
                if (name.startsWith("come")){
                    if (Integer.parseInt(map.get("come").toString())==1){
                        form.setField(name, "√");
                    }else {
                        form.setField(name, "×");
                    }
                    continue;
                }
                if (name.equals("server")){
                    if (Integer.parseInt(map.get("server").toString())==1){
                        form.setField(name, "√");
                    }else {
                        form.setField(name, "×");
                    }
                    continue;
                }

                if (name.startsWith("r")){
                    if (Integer.parseInt(revicedata.get(String.valueOf(r)).toString()) == 1){
                        form.setField(name, "√");
                    }
                    if (Integer.parseInt(revicedata.get(String.valueOf(r)).toString()) == 0){
                        form.setField(name, "×");
                    }
                    r++;
                    continue;
                }
                if (name.startsWith("s")){
                    if (Integer.parseInt(sendData.get(String.valueOf(s)).toString()) == 1){
                        form.setField(name, "√");
                    }
                    if (Integer.parseInt(sendData.get(String.valueOf(s)).toString()) == 0){
                        form.setField(name, "×");
                    }
                    s++;
                }
            }

            stamper.setFormFlattening(true);//如果为false那么生成的PDF文件还能编辑，一定要设为true
            stamper.close();
            reader.close();
        } catch (Exception e1) {
            logger.error(e1.getMessage(),e1);
        }
    }

    public static void insertPi(Integer result,String name) {
        /*try {
            // 读图片
            Image image = null;
            if (result == 0) {
                image = Image.getInstance("C:\\Users\\wqp\\Desktop\\pass1.png");
            } else {
                image = Image.getInstance("C:\\Users\\wqp\\Desktop\\nopass.png");
            }
            // 获取操作的页面
            under = stamper.getOverContent(3);
            // 根据域的大小缩放图片
            Rectangle signRect = form.getFieldPositions(name).get(0).position;
            image.scaleToFit(signRect.getWidth(), signRect.getHeight());
            float x = signRect.getLeft();
            float y = signRect.getBottom();
            // 添加图片
            image.setAbsolutePosition(x, y);
            under.addImage(image);

        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }




    public static void main(String[] args) throws Exception {
        LinkedHashMap<String,Integer> revice = new LinkedHashMap<String,Integer>();
        revice.put("1",1);
        revice.put("2",1);
        revice.put("3",1);
        revice.put("4",1);
        revice.put("5",1);
        revice.put("6",1);
        revice.put("7",1);
        revice.put("8",1);
        revice.put("9",1);
        revice.put("10",1);
        revice.put("11",1);
        revice.put("12",1);
        revice.put("13",1);
        revice.put("14",1);
        revice.put("15",1);
        revice.put("16",1);

    /*    reviceData{1=0, 2=0, 3=0, 4=0, 5=0, 6=0, 7=0, 8=0, 9=0, 10=0, 11=0, 12=0, 13=0, 14=0, 15=0, 16=0}
        sendData{1=0, 2=0, 3=0, 4=0, 5=0, 6=0, 7=0, 8=0, 9=0, 10=0, 11=0, 12=0, 13=0, 14=0, 15=0, 16=0}
         reportData{server=1, reportNum=QC-20180420-NO.125, img=1, enterprise=123, concurrent=1, come=0}*/


        LinkedHashMap<String,Integer> send = new LinkedHashMap<String,Integer>();
        send.put("1",1);
        send.put("2",1);
        send.put("3",1);
        send.put("4",1);
        send.put("5",1);
        send.put("6",1);
        send.put("7",1);
        send.put("8",1);
        send.put("9",1);
        send.put("10",1);
        send.put("11",1);
        send.put("12",1);
        send.put("13",1);
        send.put("14",1);
        send.put("15",1);
        send.put("16",1);

        Map map = new HashMap();
        map.put("concurrent",1);
        map.put("come",1);
        map.put("server",1);
        map.put("reportNum","QC-20180420NO.2");
        map.put("enterprise","上海群名数码科技有限公司");
        map.put("img",1);



        BasePDFWrite.createPDF(revice, send, map, ConfigManager.getInstance().getString("fileUrl").concat("公司.pdf"));

//        File file = new File("F:\\PDF\\report.pdf");
//
//        file.createNewFile();
//        new BasePDFWrite(file).generate();


    }
}