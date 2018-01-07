package com.mycompany.novelspider.impl;

import com.mycompany.novelspider.interfaces.ChapterService;
import com.mycompany.novelspider.model.Chapter;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ChapterServiceImplTest{

//    @Autowired
//    private ChapterService chapterService;

    @Test
    public void crawl() {
        ChapterService chapterService = new ChapterServiceImpl();
        List<Chapter> chapters = chapterService.getChapters("http://www.biquge.com.tw/16_16289/");
        for (Chapter chapter:chapters) {
            System.out.println(chapter);
        }
    }

    @Test
    public void getChapters() {
    }
}