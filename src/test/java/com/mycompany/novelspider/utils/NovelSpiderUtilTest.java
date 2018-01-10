package com.mycompany.novelspider.utils;

import com.mycompany.novelspider.Enum.NovelSiteEnum;
import org.junit.Test;

import static org.junit.Assert.*;

public class NovelSpiderUtilTest {

    @Test
    public void getConfig() {
        System.out.println(NovelSpiderUtil.getConfig(NovelSiteEnum.getNovelSiteEnumByUrl("https://www.bxwx9.org/b/58/58154/9263344.html")));
    }
}