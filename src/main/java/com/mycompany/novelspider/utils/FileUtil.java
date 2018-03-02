package com.mycompany.novelspider.utils;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

public class FileUtil {
    private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);

    public static File createDir(String dirName) {
        File dir = new File(dirName);
        if (dir.exists()) {
            logger.error("创建目录 " + dirName + "失败, 目标目录已存在!" );
        }

        if (!dirName.endsWith(File.separator)) {
            dirName = dirName + File.separator;
        }

        if (!dir.mkdirs()) {
            logger.error("创建目录 " + dirName + " 失败!");
        }
        return dir;
    }

    public static File createFile(String fileName) {
        File file = new File(fileName);
        if (file.exists()) {
            logger.error("创建单个文件 " + fileName + "失败,已存在!" );
        }

        if (fileName.endsWith(File.separator)) {
            logger.error("创建单个文件 " + fileName + "失败,目标文件不能为目录!");
        }

        if (!file.getParentFile().exists()) {
            if (!file.getParentFile().mkdirs()) {
                logger.error("创建目录 " + fileName + " 失败!");
            }
        }

        try {
            if (!file.createNewFile()) {
                logger.error("创建单个文件 " + fileName + "失败!");
            }
        } catch (IOException e) {
            logger.error("创建单个文件 " + fileName + "失败!" + e.getMessage());
        }
        return file;
    }

    public static String getWebSite(String url) {
        String[] webSites = url.split("\\/");
        String webSite = webSites[2];
        return webSite;
    }

    public static void main(String[] args)  {
//        System.out.println(System.getProperty("user.dir"));
//        getWebSite("http://www.open-open.com/jsoup/selector-syntax.htm");
//        createDir(getWebSite("http://www.open-open.com/jsoup/selector-syntax.htm")+"/"+"1.txt");
        String webSite = getWebSite("http://www.open-open.com/jsoup/selector-syntax.htm");
        String dirName = System.getProperty("user.dir")+"/"+webSite;
        createDir(dirName);
        File file = createFile(dirName+"/"+"1.txt");
        PrintWriter printWriter = null;
        try {
             printWriter = new PrintWriter(file);
             printWriter.write("田有金是一个做冬虫夏草生意的药商，和我的上一辈来往密切，属于“小时候抱过我”这个类别的叔叔");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            printWriter.close();
        }
    }
}
