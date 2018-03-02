package com.mycompany.novelspider.utils;

import com.mycompany.novelspider.enums.NovelSiteEnum;
import org.junit.Test;

public class NovelSpiderUtilTest {

    @Test
    public void getConfig() {
        System.out.println(NovelSpiderUtil.getConfig(NovelSiteEnum.getNovelSiteEnumByUrl("http://www.biquge.com.tw/4_4102/")));
    }
}