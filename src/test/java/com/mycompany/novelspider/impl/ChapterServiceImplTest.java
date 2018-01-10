package com.mycompany.novelspider.impl;

import com.mycompany.novelspider.interfaces.ChapterService;
import com.mycompany.novelspider.model.Chapter;
import org.junit.Test;

import java.util.List;

public class ChapterServiceImplTest{

    @Test
    public void crawl() {
    }

    @Test
    public void getChapters() {
        ChapterService chapterService = new ChapterServiceImpl();
        List<Chapter> chapters = chapterService.getChapters("http://www.seputu.com/");
        for (Chapter chapter:chapters) {
            System.out.println(chapter);
        }
    }
}