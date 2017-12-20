package com.haha.simplenews.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by 格格不入 on 2017/12/14.
 */

public class NewsListBean{

    //头条
    private List<NewsBean> T1348647909107;

    public List<NewsBean> getT1348647909107() {
        return T1348647909107;
    }

    public void setT1348647909107(List<NewsBean> T1348647909107) {
        this.T1348647909107 = T1348647909107;
    }

    public static class NewsBean implements Parcelable{
        /**
         * votecount : 5320
         * docid : D5J0GOUS00018AOR
         * lmodify : 2017-12-14 07:43:22
         * url_3w : http://news.163.com/17/1214/00/D5J0GOUS00018AOR.html
         * source : 海外网
         * postid : D5J0GOUS00018AOR
         * priority : 104
         * title : 女子机场徒手高空倒挂吓坏众人 玩累了自动掉落
         * mtime : 2017-12-14 07:43:22
         * url : http://3g.163.com/news/17/1214/00/D5J0GOUS00018AOR.html
         * replyCount : 5945
         * ltitle : 女子机场徒手高空倒挂吓坏众人 玩累了自动掉落
         * subtitle :
         * digest : 海外网12日14日电一名疑似来自欧洲的外籍女子13日在泰国素万那普国际机场候机大厅大秀“徒手高空倒挂”特技，惊动机场安保及救援等各相关部门，最后精疲力尽才自己掉
         * boardid : news2_bbs
         * imgsrc : http://cms-bucket.nosdn.127.net/a0bbab9bb29d492898bd985f845b38a720171214014430.png
         * ptime : 2017-12-14 00:10:37
         * daynum : 17514
         */

//        private int votecount;
//        private String docid;
//        private String lmodify;
//        private String url_3w;
        public String source;
//        private String postid;
//        private int priority;
        public String title;
//        private String mtime;
        public String url;
//        private int replyCount;
//        private String ltitle;
//        private String subtitle;
//        private String digest;
//        private String boardid;
        public String imgsrc;
        public String ptime;
//        private String daynum;

//        public int getVotecount() {
//            return votecount;
//        }

//        public void setVotecount(int votecount) {
//            this.votecount = votecount;
//        }
//
//        public String getDocid() {
//            return docid;
//        }
//
//        public void setDocid(String docid) {
//            this.docid = docid;
//        }
//
//        public String getLmodify() {
//            return lmodify;
//        }
//
//        public void setLmodify(String lmodify) {
//            this.lmodify = lmodify;
//        }
//
//        public String getUrl_3w() {
//            return url_3w;
//        }
//
//        public void setUrl_3w(String url_3w) {
//            this.url_3w = url_3w;
//        }

        protected NewsBean(Parcel in) {
            source = in.readString();
            title = in.readString();
            imgsrc = in.readString();
            ptime = in.readString();
            url = in.readString();
        }

        public static final Creator<NewsBean> CREATOR = new Creator<NewsBean>() {
            @Override
            public NewsBean createFromParcel(Parcel in) {
                return new NewsBean(in);
            }

            @Override
            public NewsBean[] newArray(int size) {
                return new NewsBean[size];
            }
        };

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

//        public String getPostid() {
//            return postid;
//        }
//
//        public void setPostid(String postid) {
//            this.postid = postid;
//        }
//
//        public int getPriority() {
//            return priority;
//        }
//
//        public void setPriority(int priority) {
//            this.priority = priority;
//        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

//        public String getMtime() {
//            return mtime;
//        }
//
//        public void setMtime(String mtime) {
//            this.mtime = mtime;
//        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

//        public int getReplyCount() {
//            return replyCount;
//        }
//
//        public void setReplyCount(int replyCount) {
//            this.replyCount = replyCount;
//        }
//
//        public String getLtitle() {
//            return ltitle;
//        }

//        public void setLtitle(String ltitle) {
//            this.ltitle = ltitle;
//        }
//
//        public String getSubtitle() {
//            return subtitle;
//        }
//
//        public void setSubtitle(String subtitle) {
//            this.subtitle = subtitle;
//        }
//
//        public String getDigest() {
//            return digest;
//        }

//        public void setDigest(String digest) {
//            this.digest = digest;
//        }
//
//        public String getBoardid() {
//            return boardid;
//        }
//
//        public void setBoardid(String boardid) {
//            this.boardid = boardid;
//        }

        public String getImgsrc() {
            return imgsrc;
        }

        public void setImgsrc(String imgsrc) {
            this.imgsrc = imgsrc;
        }

        public String getPtime() {
            return ptime;
        }

        public void setPtime(String ptime) {
            this.ptime = ptime;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(source);
            parcel.writeString(title);
            parcel.writeString(imgsrc);
            parcel.writeString(ptime);
            parcel.writeString(url);
        }

//        public String getDaynum() {
//            return daynum;
//        }
//
//        public void setDaynum(String daynum) {
//            this.daynum = daynum;
//        }


    }
}
