package com.mycompany.novelspider.impl;

import com.mycompany.novelspider.enums.NovelSiteEnum;
import com.mycompany.novelspider.interfaces.ChapterDetailService;
import com.mycompany.novelspider.model.ChapterDetail;
import com.mycompany.novelspider.utils.NovelSpiderUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.Map;

public class DaoMiChapterDetailServiceImpl extends AbstractChapterDetailServiceImpl implements ChapterDetailService {
    @Override
    public ChapterDetail getChapterDetail(String url) {
        try {
            String temp = super.crawl(url);
            Map<String,String> stringMap = NovelSpiderUtil.getConfig(NovelSiteEnum.getNovelSiteEnumByUrl(url));
            Document document = Jsoup.parse(temp);
            document.setBaseUri(url);
            ChapterDetail chapterDetail = new ChapterDetail();

            //获取章节标题
            String titleSelector = stringMap.get("chapter-detail-title-selector");
            String[] splits = titleSelector.split("\\,");
            splits = super.parseSelector(splits);
            chapterDetail.setTitle(document.select(splits[0]).get(Integer.parseInt(splits[1])).text());

            //获取章节内容
            String contentSelector = stringMap.get("chapter-detail-content-selector");
            chapterDetail.setContent(document.select(contentSelector).text());

            //获取上一页地址
            String prevSelector = stringMap.get("chapter-detail-prev-selector");
            splits = prevSelector.split("\\,");
            splits = parseSelector(splits);
            chapterDetail.setPrev(document.select(splits[0]).get(Integer.parseInt(splits[1])).absUrl("href"));

            //获取下一页地址
            String nextSelector = stringMap.get("chapter-detail-next-selector");
            splits = nextSelector.split("\\,");
            splits = parseSelector(splits);
            chapterDetail.setNext(document.select(splits[0]).get(Integer.parseInt(splits[1])).absUrl("href"));

            return chapterDetail;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
