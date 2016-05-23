package com.lpnpcs.yuanhelper.data.entity;

/**
 * Created by lpnpcs.
 * email:lpnpcs@gmail.com
 * description：笑话实体
 */
public class JokeEntity implements IJEntity {

    /**
     * title : 我虽然小，但是不傻啊
     * content : 　　坐大巴回家，旁边坐了一女的，带着一个四五岁的小女孩，长得特别可爱。我故意逗她：“叫姐姐，姐姐给你糖吃”女孩睁大眼睛，看着我说：“阿姨，我只是小，但是我不傻”尼玛！要不是她妈在…我就打死她了…
     * poster :
     * url : http://www.laifudao.com/wangwen/111118.htm
     */

    private String title;
    private String content;
    private String poster;
    private String url;

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

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
