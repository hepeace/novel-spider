package com.mycompany.novelspider.utils;

import org.junit.Test;

import static org.junit.Assert.*;

public class DownloadUtilTest {
    @Test
    public void download() throws Exception {
        DownloadUtil downloadUtil = new DownloadUtil();
        downloadUtil.download("https://www.bxwx9.org/b/5/5740/41209550.html");
    }

    @Test
    public void downloadBatch() throws Exception {
        DownloadUtil downloadUtil = new DownloadUtil();
        downloadUtil.downloadBatch("http://www.biquge.com.tw/4_4102/");
//        downloadUtil.downloadBatch("http://www.biquge.com.tw/11_11850");
    }
}