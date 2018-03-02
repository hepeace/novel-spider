package com.mycompany.novelspider.impl;

import com.mycompany.novelspider.enums.NovelSiteEnum;
import com.mycompany.novelspider.interfaces.ChapterDetailService;
import com.mycompany.novelspider.model.ChapterDetail;
import com.mycompany.novelspider.service.SpiderCrawlService;
import com.mycompany.novelspider.utils.NovelSpiderUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.Map;

/**
 * 获取章节详情实现
 */
public abstract class AbstractChapterDetailServiceImpl extends SpiderCrawlService implements ChapterDetailService {

    @Override
    public ChapterDetail getChapterDetail(String url) {
        try {
            String temp = super.crawl(url);
            Map<String,String> configMap = NovelSpiderUtil.getConfig(NovelSiteEnum.getNovelSiteEnumByUrl(url));
            Document document =  Jsoup.parse(temp);
            document.setBaseUri(url);
            ChapterDetail chapterDetail = new ChapterDetail();

            //获取章节标题
            String titleSelector = configMap.get("chapter-detail-title-selector");
            String[] splits = titleSelector.split("\\,");
            splits = parseSelector(splits);
            chapterDetail.setTitle(document.select(splits[0]).get(Integer.parseInt(splits[1])).text());

            //获取章节内容
            String contentSelector = configMap.get("chapter-detail-content-selector");
            chapterDetail.setContent(document.select(contentSelector).first().text());

            //获取上一页地址
            String prevSelector = configMap.get("chapter-detail-prev-selector");
            splits = prevSelector.split("\\,");
            splits = parseSelector(splits);
            chapterDetail.setPrev(document.select(splits[0]).get(Integer.parseInt(splits[1])).absUrl("href"));

            //获取下一页地址
            String nextSelector = configMap.get("chapter-detail-next-selector");
            splits = nextSelector.split("\\,");
            splits = parseSelector(splits);
            chapterDetail.setNext(document.select(splits[0]).get(Integer.parseInt(splits[1])).absUrl("href"));


            return  chapterDetail;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 默认判断
     * @param splits
     * @return
     */
    public String[] parseSelector(String[] splits) {
        String[] newSplits = new String[2];
        if (splits.length == 1) {
            newSplits[0] = splits[0];
            newSplits[1] = "0";
            return newSplits;
        } else {
            return splits;
        }
    }

}
