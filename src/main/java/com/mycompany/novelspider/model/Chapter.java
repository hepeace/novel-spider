package com.mycompany.novelspider.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * 小说章节实体
 */
public class Chapter implements Serializable {
    /**
     * 章节标题
     */
    private String title;
    /**
     * 章节对应的url
     */
    private String url;

    public Chapter() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chapter chapter = (Chapter) o;
        return Objects.equals(title, chapter.title) &&
                Objects.equals(url, chapter.url);
    }

    @Override
    public int hashCode() {

        return Objects.hash(title, url);
    }

    @Override
    public String toString() {
        return "Chapter{" +
                "title='" + title + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
