package com.mycompany.novelspider.utils;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.SocketTimeoutException;

public final class HttpUtil {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public static final int CONNECT_TIMEOUT = 18000;

    public static final int READ_TIMEOUT = 18000;

    private static final String charset = "UTF-8";

    private static HttpClient httpClient = null;

    static {
        PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager();
        poolingHttpClientConnectionManager.setMaxTotal(128);
        poolingHttpClientConnectionManager.setDefaultMaxPerRoute(128);
        httpClient = HttpClients.custom().setConnectionManager(poolingHttpClientConnectionManager).build();
    }

    public static String get(String url,String charset,Integer connectTimeout,Integer readTimeout)
            throws ConnectTimeoutException,SocketTimeoutException,Exception{
        HttpClient httpClient = null;
        HttpGet httpGet = new HttpGet(url);
        String result = "";
        try {
            RequestConfig.Builder builder = RequestConfig.custom();
            if (connectTimeout != null) {
                builder.setConnectTimeout(connectTimeout);
            }
            if (readTimeout != null) {
                builder.setSocketTimeout(readTimeout);
            }
            httpGet.setConfig(builder.build());
            HttpResponse httpResponse = null;
            httpClient = HttpUtil.httpClient;
            httpClient.execute(httpGet);
            result = IOUtils.toString(httpResponse.getEntity().getContent(),charset);
        } finally {
            httpGet.releaseConnection();
        }
        return result;
    }
}
