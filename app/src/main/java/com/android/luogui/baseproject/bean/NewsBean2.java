/*
 * Copyright (c) 2018.
 * Create by LuoGui.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.luogui.baseproject.bean;

import java.util.List;

/**
 * describe
 * Created by  LuoGui on 2018/1/12.
 */

public class NewsBean2 {

    /**
     * res : [{"content":"超正点美女佳妮白嫩玉体","id":102663,"urls":"http://movie.jzfywz.com/comment/1515746928.76_1178456.jpg"},{"content":"爆乳女桓淼淼妩媚深沟诱惑挑逗","id":102662,"urls":"http://movie.jzfywz.com/comment/1515741638.77_1178456.jpg"},{"content":"爆乳女桓淼淼妩媚牛仔衣","id":102661,"urls":"http://movie.jzfywz.com/comment/1515727797.28_1178456.jpg"},{"content":"饱满双峰捧不住 极品美妞赵小米真养眼","id":102660,"urls":"http://movie.jzfywz.com/comment/1515659264.56_1178456.jpg"},{"content":"性感妹子胸大臀大第7期","id":102659,"urls":"http://movie.jzfywz.com/comment/1515638613.91_1178456.jpg"},{"content":"<猫头鹰的秋天>发的帖子校花美不美","id":102658,"urls":"http://movie.jzfywz.com/comment/1515581792.66_1544133.jpg"},{"content":"饱满女神颖儿曼妙身材尽收眼底","id":102657,"urls":"http://movie.jzfywz.com/comment/1515573937.14_1178456.jpg"},{"content":"床上清纯女孩刘飞儿丰满身姿","id":102656,"urls":"http://movie.jzfywz.com/comment/1515549261.72_1178456.jpg"},{"content":"萌妹子性感诱惑","id":102655,"urls":"http://movie.jzfywz.com/comment/1515485200.9_1178456.jpg"},{"content":"大胸黑丝诱惑吊带","id":102654,"urls":"http://movie.jzfywz.com/comment/1515476659.61_1178456.jpg"}]
     * status : success
     */

    private String status;
    private List<ResBean> res;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ResBean> getRes() {
        return res;
    }

    public void setRes(List<ResBean> res) {
        this.res = res;
    }

    public static class ResBean {
        /**
         * content : 超正点美女佳妮白嫩玉体
         * id : 102663
         * urls : http://movie.jzfywz.com/comment/1515746928.76_1178456.jpg
         */

        private String content;
        private int id;
        private String urls;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUrls() {
            return urls;
        }

        public void setUrls(String urls) {
            this.urls = urls;
        }
    }
}
