package com.mycompany.novelspider.interfaces;

import com.mycompany.novelspider.model.ChapterDetail;

public interface ChapterDetailService {
    /**
     * 获取章节详情接口
     * @param url
     * @return
     */
    public ChapterDetail getChapterDetail(String url);
}
