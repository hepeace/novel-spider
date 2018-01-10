package com.mycompany.novelspider.impl;

import com.mycompany.novelspider.interfaces.ChapterDetailService;
import org.junit.Test;

import static org.junit.Assert.*;

public class ChapterDetailServiceImplTest {

    @Test
    public void getChapterDetail() {
        ChapterDetailService chapterDetailService = new ChapterDetailServiceImpl();
        System.out.println(chapterDetailService.getChapterDetail("http://seputu.com/biji1/2.html"));
    }
}