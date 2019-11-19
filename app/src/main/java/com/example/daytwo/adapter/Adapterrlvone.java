package com.example.daytwo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.daytwo.R;
import com.example.daytwo.bean.Beanone;
import com.example.daytwo.bean.RecentBean;

import java.util.ArrayList;

public class Adapterrlvone extends RecyclerView.Adapter<Adapterrlvone.Holderone> {
    private ArrayList<RecentBean> list;
    private Context context;
    private OnItemClickListener onItemClickListener;
    private OnItemLongClickListener onItemLongClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    public Adapterrlvone(ArrayList<RecentBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public Holderone onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_one, parent, false);
        return new Holderone(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull Holderone holder, final int position) {
        RecentBean recentBean = list.get(position);
        holder.tv_news_id.setText(recentBean.getId()+"");
        holder.tv_title.setText(recentBean.getTitle());
        Glide.with(context).load(recentBean.getThumbnail()).into(holder.iv_thumbnail);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.OnItemClick(position);
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                onItemLongClickListener.OnItemLongClick(position);
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holderone extends RecyclerView.ViewHolder {

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
    public interface OnItemClickListener{
        void OnItemClick(int position);
    }
    public interface OnItemLongClickListener{
        void OnItemLongClick(int position);
    }
}
