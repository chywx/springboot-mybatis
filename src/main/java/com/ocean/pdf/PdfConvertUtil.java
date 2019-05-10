package com.ocean.pdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.fit.pdfdom.PDFDomTree;

import java.io.*;

public class PdfConvertUtil {
    public void pdftohtml(byte[] bytes, String htmlPath) throws Exception {
        //加载PDF文档
        PDDocument document = PDDocument.load(bytes);
        // 输出pdf文本
        readText(document);
        //将字节流转换成字符流
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(htmlPath)),"UTF-8"));
        //实例化pdfdom树对象
        PDFDomTree pdfDomTree = new PDFDomTree();
        //开始写入html文件
        pdfDomTree.writeText(document, out);
        //在文件末尾写入要引入的js，因为我将转换的html文件放在了webapp/pdfhtml文件夹下，所以这两个js文件也要放在pdfhtml文件夹下
        out.write("<script src=\"https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js />");
        out.flush();
        out.close();
        document.close();
    }

    public void readText(PDDocument document) throws IOException {
        PDFTextStripper stripper = new PDFTextStripper();
        String text = stripper.getText(document);
        System.out.println(text);
    }

    private static byte[] getBytes(String filePath){
        byte[] buffer = null;
        try {
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1000];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer;
    }
}
