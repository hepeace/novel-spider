package com.mycompany.novelspider.utils;

import com.mycompany.novelspider.Enum.NovelSiteEnum;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class NovelSpiderUtil {
    private static final Logger logger = LoggerFactory.getLogger(NovelSpiderUtil.class);
    private static final Map<NovelSiteEnum,Map<String,String>> CONFIG_MAP = new HashMap<>();
    private NovelSpiderUtil() {
    }
    static {
        init();
    }

    /**
     * 读取配置文件
     */
    private static void init() {
        DefaultResourceLoader defaultResourceLoader = new DefaultResourceLoader();
        Resource resource = defaultResourceLoader.getResource("config/Spider-Rule.xml");
        SAXReader saxReader = new SAXReader();
        try {
            Document document = saxReader.read(resource.getFile());
            Element root = document.getRootElement();
            List<Element> sites = root.elements("site");
            for (Element site:sites) {
                List<Element>  subs = site.elements();
                Map<String,String> subMap = new HashMap<>();
                for (Element sub:subs) {
                   String subName = sub.getName();
                   String subText = sub.getTextTrim();
                   subMap.put(subName,subText);
                }
                CONFIG_MAP.put(NovelSiteEnum.getNovelSiteEnumByUrl(subMap.get("url")),subMap);
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            logger.error("支持网站配置文件初始化失败 {}",e.getMessage());
        }
    }


    /**
     * 获取对应网站的配置文件
     * @param novelSiteEnum
     * @return
     */
    public static Map<String,String> getConfig(NovelSiteEnum novelSiteEnum) {
        return CONFIG_MAP.get(novelSiteEnum);
    }
}
