package com.mycompany.novelspider.impl;

import com.mycompany.novelspider.interfaces.ChapterDetailService;
import org.junit.Test;

public class ChapterDetailServiceImplTest {

    @Test
    public void getChapterDetail() {
        ChapterDetailService chapterDetailService = new DaoMiChapterDetailServiceImpl();
        System.out.println(chapterDetailService.getChapterDetail("http://www.biquge.com.tw/4_4102/2390027.html"));
    }
}