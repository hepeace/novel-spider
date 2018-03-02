package com.mycompany.novelspider.utils;

import com.mycompany.novelspider.enums.NovelSiteEnum;
import com.mycompany.novelspider.impl.DaoMiChapterDetailServiceImpl;
import com.mycompany.novelspider.impl.DefaultChapterDetailServiceImpl;
import com.mycompany.novelspider.impl.ChapterServiceImpl;
import com.mycompany.novelspider.interfaces.ChapterDetailService;
import com.mycompany.novelspider.interfaces.ChapterService;
import com.mycompany.novelspider.model.Chapter;
import com.mycompany.novelspider.model.ChapterDetail;

import java.io.*;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

public class DownloadUtil extends PrintWriteUtil{

    private static final int threadPool = 8;

    public  boolean download(String url){
        if (url.contains(NovelSiteEnum.getNovelSiteEnumById(5).getUrl())) {
            ChapterDetailService chapterDetailService = new DaoMiChapterDetailServiceImpl();
            ChapterDetail chapterDetail = chapterDetailService.getChapterDetail(url);
            String charset = NovelSpiderUtil.getConfig(NovelSiteEnum.getNovelSiteEnumByUrl(url)).get("charset");
            String content = chapterDetail.getContent();
            FileUtil.createDir(System.getProperty("user.dir")+"/"+FileUtil.getWebSite(url));
            File file = new File(chapterDetail.getTitle()+".txt");
            super.printWrite(file,charset,content);
        } else {
            ChapterDetailService chapterDetailService = new DefaultChapterDetailServiceImpl();
            ChapterDetail chapterDetail = chapterDetailService.getChapterDetail(url);
            String charset = NovelSpiderUtil.getConfig(NovelSiteEnum.getNovelSiteEnumByUrl(url)).get("charset");
            FileUtil.createDir(System.getProperty("user.dir")+"/"+FileUtil.getWebSite(url));
            File file = new File(FileUtil.getWebSite(url)+chapterDetail.getTitle() + ".txt");
            super.printWrite(file, charset, chapterDetail.getContent());
        }
        return true;
    }

    public void downloadBatch(String url) {
        ChapterService chapterService = new ChapterServiceImpl();
        List<Chapter> chapterList = chapterService.getChapters(url);
        ForkJoinPool forkJoinPool = new ForkJoinPool(threadPool);
        Future<List<Boolean>> res = forkJoinPool.submit(() -> chapterList.parallelStream().map(chapter -> download(chapter.getUrl())).collect(Collectors.toList()));
        try {
            List<Boolean> tt = res.get();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            forkJoinPool.shutdown();
        }
    }
}
