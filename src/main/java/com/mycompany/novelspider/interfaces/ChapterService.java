package com.mycompany.novelspider.interfaces;

import com.mycompany.novelspider.model.Chapter;

import java.util.List;

public interface ChapterService {
    /**
     * 入参是小说链接,返回小说章节list
     * @param url
     * @return
     */
    public  List<Chapter> getChapters (String url);
}
