package com.mycompany.novelspider.impl;

import com.mycompany.novelspider.Enum.NovelSiteEnum;
import com.mycompany.novelspider.interfaces.ChapterService;
import com.mycompany.novelspider.model.Chapter;
import com.mycompany.novelspider.service.SpiderCrawlService;
import com.mycompany.novelspider.utils.NovelSpiderUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * 获取章节列表实现
 */
public class ChapterServiceImpl extends SpiderCrawlService implements ChapterService {

    @Override
    public List<Chapter> getChapters(String url) {
        try {
            String result = super.crawl(url);
            Document document = Jsoup.parse(result);
            document.setBaseUri(url);
            String selector = NovelSpiderUtil.getConfig(NovelSiteEnum.getNovelSiteEnumByUrl(url)).get("chapter-list-selector");
            Elements elements = document.select(selector);
            List<Chapter> chapterList = new ArrayList<>();
            for (Element element: elements) {
                Chapter chapter = new Chapter();
                chapter.setTitle(element.text());
                chapter.setUrl(element.absUrl("href"));
                chapterList.add(chapter);
            }
            return chapterList;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
