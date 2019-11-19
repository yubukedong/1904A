package com.example.daytwo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.daytwo.R;
import com.example.daytwo.bean.RecentBean;

import java.util.ArrayList;
import java.util.List;

public class Adapterrlvtwo extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<RecentBean> list;
    private Context context;

    public Adapterrlvtwo(List<RecentBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType==0){
            View inflate = LayoutInflater.from(context).inflate(R.layout.item_one, parent, false);
            return new Holderone(inflate);
        }else {
            View inflate = LayoutInflater.from(context).inflate(R.layout.item_two, parent, false);
            return new Holdertwo(inflate);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int type = getItemViewType(position);
        RecentBean bean = list.get(position);
        switch (type){
            case 0:
                Holderone holderone= (Holderone) holder;
                holderone.tv_news_id.setText(bean.getId()+"");
                holderone.tv_title.setText(bean.getTitle());
                Glide.with(context).load(bean.getThumbnail()).into(holderone.iv_thumbnail);
                break;
            case 1:
                Holdertwo holdertwo= (Holdertwo) holder;
                holdertwo.tv_news_ids.setText(bean.getId()+"");
                holdertwo.tv_titles.setText(bean.getTitle());
                Glide.with(context).load(bean.getThumbnail()).into(holdertwo.iv_thumbnails);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position%2==0){
            return 0;
        }else {
            return 1;
        }
    }
    public class Holderone extends RecyclerView.ViewHolder{

        private final TextView tv_news_id;
        private final ImageView iv_thumbnail;
        private final TextView tv_title;

        public Holderone(@NonNull View itemView) {
            super(itemView);
            tv_news_id = itemView.findViewById(R.id.tv_news_id);
            iv_thumbnail = itemView.findViewById(R.id.iv_thumbnail);
            tv_title = itemView.findViewById(R.id.tv_title);
        }
    }
    public class Holdertwo extends RecyclerView.ViewHolder{

        private final TextView tv_news_ids;
        private final TextView tv_titles;
        private final ImageView iv_thumbnails;

        public Holdertwo(@NonNull View itemView) {
            super(itemView);
            tv_news_ids = itemView.findViewById(R.id.tv_news_ids);
            tv_titles = itemView.findViewById(R.id.tv_titles);
            iv_thumbnails = itemView.findViewById(R.id.iv_thumbnails);
        }
    }
}
