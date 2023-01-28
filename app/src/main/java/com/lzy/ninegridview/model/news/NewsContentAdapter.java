package com.lzy.ninegridview.model.news;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lzy.ninegrid.ImageInfo;
import com.lzy.ninegrid.NineGridView;
import com.lzy.ninegrid.NineGridViewAdapter;
import com.lzy.ninegridview.R;
import com.lzy.ninegridview.model.news.bean.NewsContent;
import com.lzy.ninegridview.model.news.bean.NewsImage;

import java.util.ArrayList;
import java.util.List;

/**
 * ================================================
 * 作    者：廖子尧
 * 版    本：1.0
 * 创建日期：2016/3/22
 * 描    述：
 * 修订历史：
 * ================================================
 */
public class NewsContentAdapter extends RecyclerView.Adapter<NewsContentAdapter.PostViewHolder> {
    private LayoutInflater mInflater;
    private Context mContext;
    private List<NewsContent> mDataList;

    public NewsContentAdapter(Context context, List<NewsContent> data) {
        super();
        mContext = context;
        mDataList = data;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public void onBindViewHolder(PostViewHolder holder, int position) {
        holder.bind(mDataList.get(position));
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PostViewHolder(mInflater.inflate(R.layout.item_news, parent, false));
    }

    public void setData(List<NewsContent> data) {
        mDataList = data;
        notifyDataSetChanged();
    }

    public class PostViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView title;
        private NineGridView nineGrid;
        private TextView desc;
        private TextView pubDate;
        private TextView source;
        private NewsContent item;
        private View itemView;

        public PostViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            title = (TextView) itemView.findViewById(R.id.title);
            nineGrid = (NineGridView) itemView.findViewById(R.id.nineGrid);
            desc = (TextView) itemView.findViewById(R.id.desc);
            pubDate = (TextView) itemView.findViewById(R.id.pubDate);
            source = (TextView) itemView.findViewById(R.id.source);
        }

        public void bind(NewsContent item) {
            this.item = item;
            title.setText(item.getTitle());
            desc.setText(item.getDesc());
            pubDate.setText(item.getPubDate());
            source.setText(item.getSource());

            ArrayList<ImageInfo> imageInfo = new ArrayList<>();
            List<NewsImage> images = item.getImageurls();
            if (images != null) {
                for (NewsImage image : images) {
                    ImageInfo info = new ImageInfo();
                    info.setThumbnailUrl(image.getUrl());
                    info.setBigImageUrl(image.getUrl());
                    imageInfo.add(info);
                }
            }
            nineGrid.setAdapter(new NineGridViewAdapter(mContext, imageInfo) {
            });

            if (images != null && images.size() == 1) {
                nineGrid.setSingleImageRatio(images.get(0).getWidth() * 1.0f / images.get(0).getHeight());
            }

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(mContext, NewsLinkActivity.class);
            intent.putExtra("link", item.getLink());
            mContext.startActivity(intent);
        }
    }
}
