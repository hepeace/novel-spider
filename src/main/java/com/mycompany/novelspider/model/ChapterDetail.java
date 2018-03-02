package com.mycompany.novelspider.model;

import org.apache.commons.lang.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * 章节详情实体
 */
public class ChapterDetail implements Serializable {
    private static final long serialVersionUID = 456319336225595699L;

    /**
     * 章节标题
     */
    private String title;

    /**
     * 章节内容
     */
    private String content;

    /**
     * 章节上一页地址
     */
    private String prev;

    /**
     * 章节下一页地址
     */
    private String next;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPrev() {
        return prev;
    }

    public void setPrev(String prev) {
        this.prev = prev;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public ChapterDetail() {
    }

    @Override
    public String toString() {
        return "ChapterDetail{" +
                "title='" + title + '\'' +
//                ", content='" + StringUtils.abbreviate(content,30) + '\'' +
                ", content='" + content + '\'' +
                ", prev='" + prev + '\'' +
                ", next='" + next + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChapterDetail that = (ChapterDetail) o;
        return Objects.equals(title, that.title) &&
                Objects.equals(content, that.content) &&
                Objects.equals(prev, that.prev) &&
                Objects.equals(next, that.next);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, content, prev, next);
    }
}
