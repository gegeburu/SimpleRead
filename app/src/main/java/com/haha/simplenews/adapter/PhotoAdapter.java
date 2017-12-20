package com.haha.simplenews.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.haha.simplenews.MyApplication;
import com.haha.simplenews.R;
import com.haha.simplenews.bean.PhotoBean;
import com.haha.simplenews.utils.findViewUtil.ViewById;
import com.haha.simplenews.utils.findViewUtil.ViewUtils;

import java.util.List;

/**
 * Created by 格格不入 on 2017/12/19.
 */

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.ViewHolder>{
    private List<PhotoBean.ResultsBean> mData;
    private OnItemClickListener mListener;
    public PhotoAdapter(List<PhotoBean.ResultsBean> data){
        this.mData = data;
    }
    @Override
    public PhotoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(MyApplication.getContext()).inflate(R.layout.item_photo, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PhotoAdapter.ViewHolder holder, final int position) {
        String url = mData.get(position).url;
        Glide.with(MyApplication.getContext())
                .load(url)
                .crossFade(500)
                .placeholder(R.drawable.img_default_meizi)
                .into(holder.ivImage);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @ViewById(R.id.iv_image)
        ImageView ivImage;

        public ViewHolder(View itemView) {
            super(itemView);
            ViewUtils.inject(itemView,this);
        }
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        this.mListener = listener;
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }
}
