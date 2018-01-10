package com.mycompany.novelspider.service;

import com.mycompany.novelspider.Enum.NovelSiteEnum;
import com.mycompany.novelspider.utils.NovelSpiderUtil;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

/**
 * 爬取网站内容
 */
public abstract class SpiderCrawlService {
    protected String crawl(String url) throws Exception{
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build();
             CloseableHttpResponse closeableHttpResponse = httpClient.execute(new HttpGet(url));
        ) {
            String charset = NovelSpiderUtil.getConfig(NovelSiteEnum.getNovelSiteEnumByUrl(url)).get("charset");
            String result = EntityUtils.toString(closeableHttpResponse.getEntity(),charset);
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
