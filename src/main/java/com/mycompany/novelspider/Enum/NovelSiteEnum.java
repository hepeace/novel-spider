package com.mycompany.novelspider.Enum;

/**
 * 支持小说网站枚举
 */
public enum NovelSiteEnum {
    BIXIAWENXUE(1,"bxwx9.org"),
    BIQUGE(2,"biquge.com.tw"),
    DINGDIANXIAOSHUO(3,"x23us.com"),
    DAOMUBIJI(4,"seputu.com");

    private int id;
    private String url;

    private NovelSiteEnum(int id,String url){
        this.id = id;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 通过id获取枚举
     * @param id
     * @return
     */
    public static NovelSiteEnum getNovelSiteEnumById(int id){
       switch (id) {
           case 1: return BIXIAWENXUE;
           case 2: return BIQUGE;
           case 3: return DINGDIANXIAOSHUO;
           case 4: return DAOMUBIJI;
           default: throw  new RuntimeException("id= " + id + "目前暂不支持该网站,敬请期待!");
       }
    }

    /**
     * 通过url获取枚举
     * @param url
     * @return
     */
    public static NovelSiteEnum getNovelSiteEnumByUrl(String url){
        for (NovelSiteEnum novelSiteEnum:values()) {
            if (url.contains(novelSiteEnum.url)) {
                return novelSiteEnum;
            }
        }
        throw new RuntimeException("url= " + url + "目前暂不支持该网站,敬请期待!");
    }
}
