package com.lpnpcs.yuanhelper.data.entity;

import java.util.List;

/**
 * Created by lpnpcs.
 * email:lpnpcs@gmail.com
 * description：ZhiHuEntity fram GsonFormat
 */
public class ZhiHuEntity {

    @Override
    public String toString() {
        return "ZhiHuEntity{" +
                "date='" + date + '\'' +
                ", stories=" + stories +
                ", top_stories=" + top_stories +
                '}';
    }

    /**
     * date : 20160510
     * stories : [{"images":["http://pic4.zhimg.com/46203fe785f860afdbffae281f90a137.jpg"],"type":0,"id":8283126,"ga_prefix":"051016","title":"据说，他们是台湾演技最好的三个男人"},{"images":["http://pic4.zhimg.com/9b9ef300c8879e13aa5a86443d867e47.jpg"],"type":0,"id":8282929,"ga_prefix":"051015","title":"你可能知道这部宫崎骏的电影，但它的漫画版推翻了整部动画"},{"images":["http://pic3.zhimg.com/08db2d44727d191b8e27baa1b670aec2.jpg"],"type":0,"id":8281832,"ga_prefix":"051014","title":"离婚之后，你们也可以当好爸妈"},{"images":["http://pic4.zhimg.com/7355c220c53b76473d50ed06680965df.jpg"],"type":0,"id":8276589,"ga_prefix":"051013","title":"看起来一模一样的案件，判决结果却不一样？"},{"images":["http://pic4.zhimg.com/5091ab221625760f281f1d3aec0cba27.jpg"],"type":0,"id":8282015,"ga_prefix":"051012","title":"21 个熊孩子把奥巴马告上了法庭"},{"images":["http://pic3.zhimg.com/8e9443e03674a5c383c8367c8612d6b6.jpg"],"type":0,"id":8276466,"ga_prefix":"051011","title":"关于「水果之王」榴莲的 5 个疑问，这里都有解答"},{"images":["http://pic1.zhimg.com/4a4315a0500bd272629151b859946fb4.jpg"],"type":0,"id":8280265,"ga_prefix":"051010","title":"谁做家务、熬不熬夜、失恋后要不要买醉，算算便知"},{"images":["http://pic3.zhimg.com/f1acec301c5390f7e7d80826240a0942.jpg"],"type":0,"id":8277572,"ga_prefix":"051009","title":"国际航天界正在「变天」，中国岂能无动于衷？"},{"images":["http://pic1.zhimg.com/8b4b2d7821f0f2f01f1b285a0851b3ec.jpg"],"type":0,"id":8279917,"ga_prefix":"051008","title":"为什么车站附近的东西都卖得这么贵？"},{"images":["http://pic1.zhimg.com/8a560bcf49fa844bdea3ebb08d92310c.jpg"],"type":0,"id":7935556,"ga_prefix":"051007","title":"婚外情到底是什么，为什么出轨会渐渐形成风气？"},{"images":["http://pic4.zhimg.com/d655a3d219c29bd1f452068f2cb1ca03.jpg"],"type":0,"id":8275070,"ga_prefix":"051007","title":"一个名不见经传的中国团队，做出了 Nature 级别的科研成果"},{"images":["http://pic4.zhimg.com/ff8bda6fdbd69334a8346eb5776cbb0f.jpg"],"type":0,"id":8276457,"ga_prefix":"051007","title":"你还相信医生吗？"},{"images":["http://pic1.zhimg.com/d84d0afca63623bb06fb08bc66652a38.jpg"],"type":0,"id":8280115,"ga_prefix":"051007","title":"读读日报 24 小时热门 TOP 5 · 抛 10 次硬币都是正面，那第 11 次呢？"},{"images":["http://pic2.zhimg.com/64d8ae859e6651eaab5e65146ab88375.jpg"],"type":0,"id":8275126,"ga_prefix":"051006","title":"瞎扯 · 如何正确地吐槽"}]
     * top_stories : [{"image":"http://pic1.zhimg.com/ab5c8b4153d0f3c86d0ba59408e4c3a0.jpg","type":0,"id":8276466,"ga_prefix":"051011","title":"关于「水果之王」榴莲的 5 个疑问，这里都有解答"},{"image":"http://pic3.zhimg.com/60e806fb909c07cf452b40eaa76b2d4a.jpg","type":0,"id":8280265,"ga_prefix":"051010","title":"谁做家务、熬不熬夜、失恋后要不要买醉，算算便知"},{"image":"http://pic2.zhimg.com/ecaacb124a54490c90e88db7575aeb11.jpg","type":0,"id":8280115,"ga_prefix":"051007","title":"读读日报 24 小时热门 TOP 5 · 抛 10 次硬币都是正面，那第 11 次呢？"},{"image":"http://pic3.zhimg.com/4179e368150825a3c28c988caefd83b6.jpg","type":0,"id":8276457,"ga_prefix":"051007","title":"你还相信医生吗？"},{"image":"http://pic4.zhimg.com/d6d6c8edaa6fd24c0df822e283faa963.jpg","type":0,"id":8275070,"ga_prefix":"051007","title":"一个名不见经传的中国团队，做出了 Nature 级别的科研成果"}]
     */

    private String date;
    /**
     * images : ["http://pic4.zhimg.com/46203fe785f860afdbffae281f90a137.jpg"]
     * type : 0
     * id : 8283126
     * ga_prefix : 051016
     * title : 据说，他们是台湾演技最好的三个男人
     */

    private List<StoriesBean> stories;
    /**
     * image : http://pic1.zhimg.com/ab5c8b4153d0f3c86d0ba59408e4c3a0.jpg
     * type : 0
     * id : 8276466
     * ga_prefix : 051011
     * title : 关于「水果之王」榴莲的 5 个疑问，这里都有解答
     */

    private List<TopStoriesBean> top_stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<StoriesBean> getStories() {
        return stories;
    }

    public void setStories(List<StoriesBean> stories) {
        this.stories = stories;
    }

    public List<TopStoriesBean> getTop_stories() {
        return top_stories;
    }

    public void setTop_stories(List<TopStoriesBean> top_stories) {
        this.top_stories = top_stories;
    }

    public static class StoriesBean {
        private int type;

        @Override
        public String toString() {
            return "StoriesBean{" +
                    "type=" + type +
                    ", id=" + id +
                    ", ga_prefix='" + ga_prefix + '\'' +
                    ", title='" + title + '\'' +
                    ", images=" + images +
                    '}';
        }

        private int id;
        private String ga_prefix;
        private String title;
        private List<String> images;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }

    public static class TopStoriesBean {
        private String image;
        private int type;
        private int id;
        private String ga_prefix;
        private String title;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        @Override
        public String toString() {
            return "TopStoriesBean{" +
                    "image='" + image + '\'' +
                    ", type=" + type +
                    ", id=" + id +
                    ", ga_prefix='" + ga_prefix + '\'' +
                    ", title='" + title + '\'' +
                    '}';
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
