package com.mycompany.novelspider.service;

import com.mycompany.novelspider.enums.NovelSiteEnum;
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
        //构建CloseableHttpClient 对象
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build();
             //根据请求类型创建HttpGet 或者HttpPost
             //通过执行执行HttpGet或者HttpPost 获取CloseableHttpResponse实例
             CloseableHttpResponse closeableHttpResponse = httpClient.execute(new HttpGet(url));
             /**
              *  closeableHttpResponse.getStatusLine().getStatusCode() 状态码
              *  closeableHttpResponse.getEntity().getContentEncoding() 内容编码
              *  closeableHttpResponse.getAllHeaders() 头信息
              */
        ) {
            String charset = NovelSpiderUtil.getConfig(NovelSiteEnum.getNovelSiteEnumByUrl(url)).get("charset");
            //EntityUtils 工具类将HttpEntity对象转成String
            String result = EntityUtils.toString(closeableHttpResponse.getEntity(),charset);
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
