package com.lpnpcs.yuanhelper.data.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by lpnpcs.
 * email:lpnpcs@gmail.com
 * description：搞笑图片实体
 */
public class ImageEntity implements IJEntity{

    /**
     * title : 套路
     * thumburl : http://ww3.sinaimg.cn/large/bd698b0fjw1f3ejwmtgetj20ez1e0n0s.jpg
     * sourceurl : http://down.laifudao.com/images/tupian/2016425141724.jpg
     * height : 1800
     * width : 539
     * class : 2
     * url : http://www.laifudao.com/tupian/56575.htm
     */

    private String title;
    private String thumburl;
    private String sourceurl;
    private String height;
    private String width;
    @SerializedName("class")
    private String classX;
    private String url;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumburl() {
        return thumburl;
    }

    public void setThumburl(String thumburl) {
        this.thumburl = thumburl;
    }

    public String getSourceurl() {
        return sourceurl;
    }

    public void setSourceurl(String sourceurl) {
        this.sourceurl = sourceurl;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getClassX() {
        return classX;
    }

    public void setClassX(String classX) {
        this.classX = classX;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
