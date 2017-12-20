package com.haha.simplenews.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by 格格不入 on 2017/12/11.
 *
 *Launch图片bean
 */

public class PhotoBean{

    public boolean error;
    public List<ResultsBean> results;

    public static class ResultsBean implements Parcelable{

        public String _id;
        public String createdAt;
        public String desc;
        public String publishedAt;
        public String source;
        public String type;
        public String url;
        public boolean used;
        public String who;
        public List<String> images;

        protected ResultsBean(Parcel in) {
            _id = in.readString();
            createdAt = in.readString();
            desc = in.readString();
            publishedAt = in.readString();
            source = in.readString();
            type = in.readString();
            url = in.readString();
            used = in.readByte() != 0;
            who = in.readString();
            images = in.createStringArrayList();
        }

        public static final Creator<ResultsBean> CREATOR = new Creator<ResultsBean>() {
            @Override
            public ResultsBean createFromParcel(Parcel in) {
                return new ResultsBean(in);
            }

            @Override
            public ResultsBean[] newArray(int size) {
                return new ResultsBean[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(_id);
            parcel.writeString(createdAt);
            parcel.writeString(desc);
            parcel.writeString(publishedAt);
            parcel.writeString(source);
            parcel.writeString(type);
            parcel.writeString(url);
            parcel.writeByte((byte) (used ? 1 : 0));
            parcel.writeString(who);
            parcel.writeStringList(images);
        }
    }
}
