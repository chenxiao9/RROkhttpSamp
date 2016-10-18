package com.chen.me.rrokhttpsamp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chen.me.rrokhttpsamp.R;
import com.chen.me.rrokhttpsamp.bean.Movie.Subject;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.xiaopan.sketch.SketchImageView;

/**
 * Created by Administrator on 2016/10/18 0018.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private LayoutInflater mInflater;
    private List<Subject> mDatas;

    public MovieAdapter( Context context,List<Subject> mDatas) {
        this.mDatas = mDatas;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        Subject oneSubject=mDatas.get(position);
        holder.tvTitle.setText(oneSubject.getTitle());
        holder.tvRateAverage.setText("平均值："+oneSubject.getRating().getAverage());
        holder.tvRateStarts.setText("推荐数："+oneSubject.getRating().getStars());
        holder.sImg.displayImage(oneSubject.getCasts().get(0).getAvatars().getMedium());
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=mInflater.inflate(R.layout.layout_movie_item,parent,false);
        MovieViewHolder movieViewHolder=new MovieViewHolder(view);
        return movieViewHolder;
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder{
        public MovieViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
        @Bind(R.id.tv_title)
        TextView tvTitle;
        @Bind(R.id.tv_rating_average)
        TextView tvRateAverage;
        @Bind(R.id.tv_rating_stars)
        TextView tvRateStarts;
        @Bind(R.id.img_casts)
        SketchImageView sImg;
    }
}
