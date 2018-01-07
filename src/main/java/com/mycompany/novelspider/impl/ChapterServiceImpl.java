package com.mycompany.novelspider.impl;

import com.mycompany.novelspider.interfaces.ChapterService;
import com.mycompany.novelspider.model.Chapter;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class ChapterServiceImpl implements ChapterService {

    protected String crawl(String url) throws Exception{
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build();
             CloseableHttpResponse closeableHttpResponse = httpClient.execute(new HttpGet(url));
        ) {
            String result = EntityUtils.toString(closeableHttpResponse.getEntity(),"gbk");
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Chapter> getChapters(String url) {
        try {
            String result = crawl(url);
            Document document = Jsoup.parse(result);
            Elements elements = document.select("#list dd a");
            List<Chapter> chapterList = new ArrayList<>();
            for (Element element: elements) {
                Chapter chapter = new Chapter();
                chapter.setTitle(element.text());
                chapter.setUrl("http://www.biquge.com.tw"+element.attr("href"));
                chapterList.add(chapter);
            }
            return chapterList;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
