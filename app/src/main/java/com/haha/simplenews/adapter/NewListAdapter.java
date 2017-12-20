package com.haha.simplenews.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.haha.simplenews.MyApplication;
import com.haha.simplenews.R;
import com.haha.simplenews.bean.NewsListBean;
import com.haha.simplenews.utils.findViewUtil.ViewById;
import com.haha.simplenews.utils.findViewUtil.ViewUtils;

import java.util.List;

/**
 * Created by 格格不入 on 2017/12/14.
 */

public class NewListAdapter extends RecyclerView.Adapter<NewListAdapter.ViewHolder>{
    private List<NewsListBean.NewsBean> mData;
    private OnItemClickListener mListener;

    public NewListAdapter(List<NewsListBean.NewsBean> data){
        this.mData = data;
    }
    @Override
    public NewListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(MyApplication.getContext()).inflate(R.layout.item_news, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NewListAdapter.ViewHolder holder, final int position) {
        NewsListBean.NewsBean newsBean = mData.get(position);
        holder.mtvTitle.setText(newsBean.getTitle());
        holder.mTvTime.setText(newsBean.getPtime());
        holder.mtvWriter.setText(newsBean.getSource());
        Glide.with(MyApplication.getContext())
                .load(newsBean.getImgsrc())
                .crossFade()
                .placeholder(R.drawable.ic_vector_image_load_before)
                .into(holder.mIvImage);
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
        @ViewById(R.id.tv_item_title)
        TextView mtvTitle;
        @ViewById(R.id.tv_item_who)
        TextView mtvWriter;
        @ViewById(R.id.tv_item_time)
        TextView mTvTime;
        @ViewById(R.id.iv_item_image)
        ImageView mIvImage;

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
