package com.mycompany.novelspider.impl;

import com.mycompany.novelspider.interfaces.ChapterDetailService;
import com.mycompany.novelspider.model.ChapterDetail;
import org.junit.Test;

import static org.junit.Assert.*;

public class DaoMiChapterDetailServiceImplTest {
    @Test
    public void getChapterDetail() throws Exception {
        ChapterDetailService chapterDetailService = new DaoMiChapterDetailServiceImpl();
        ChapterDetail chapterDetail = chapterDetailService.getChapterDetail("http://www.daomubiji.org/2907.html");
        System.out.println(chapterDetail);
    }

}