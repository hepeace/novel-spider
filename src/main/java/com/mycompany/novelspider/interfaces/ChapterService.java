package com.mycompany.novelspider.interfaces;

import com.mycompany.novelspider.model.Chapter;

import java.util.List;

public interface ChapterService {
    /**
     * 获取章节列表实体
     * @param url
     * @return
     */
    public  List<Chapter> getChapters (String url);
}
