package com.chen.me.rrokhttpsamp.adapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chen.me.rrokhttpsamp.MovieDetail.MovieActivity;
import com.chen.me.rrokhttpsamp.R;
import com.chen.me.rrokhttpsamp.bean.Movie.Subject;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.xiaopan.sketch.SketchImageView;

/**
 * Created by Administrator on 2016/10/18 0018.
 */

public class MovieAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Subject> mDatas;

    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER = 1;

    private CastsAdapter castsAdapter;
    private LinearLayoutManager layoutManager;
    public void setMovieData(List<Subject> data) {
        this.mDatas = data;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mDatas==null?0:mDatas.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position+1==getItemCount()){
            return TYPE_FOOTER;
        }else {
            return TYPE_ITEM;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MovieViewHolder){
            MovieViewHolder theHolder=(MovieViewHolder)holder;
            Subject oneSubject = mDatas.get(position);
            theHolder.tvTitle.setText(oneSubject.getTitle());
            theHolder.subTitle.setText(oneSubject.getOriginalTitle());
            theHolder.tvRateAverage.setText("平均值：" + oneSubject.getRating().getAverage());
            theHolder.tvRateStarts.setText("推荐数：" + oneSubject.getRating().getStars());
            theHolder.sImg.getOptions().setResizeByFixedSize(true).setForceUseResize(true);
            theHolder.sImg.displayImage(oneSubject.getImages().getLarge());

            castsAdapter=new CastsAdapter();
            theHolder.castsRecycler.setAdapter(castsAdapter);
            if (null==castsAdapter.getCast()){
                castsAdapter.setCast(oneSubject.getCasts());
            }else {
                castsAdapter.getCast().clear();
                castsAdapter.getCast().addAll(oneSubject.getCasts());
            }
            if (null==castsAdapter.getDirector()){
                castsAdapter.setDirector(oneSubject.getDirectors());
            }else {
                castsAdapter.getDirector().clear();
                castsAdapter.getDirector().addAll(oneSubject.getDirectors());
            }
            castsAdapter.notifyDataSetChanged();
        }

    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        layoutManager=new LinearLayoutManager(parent.getContext(),LinearLayoutManager.HORIZONTAL,false);
        if (viewType==TYPE_ITEM){
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.layout_movie_item, parent, false);
            MovieViewHolder movieViewHolder = new MovieViewHolder(view);
//            castsAdapter=new CastsAdapter();
//            movieViewHolder.castsRecycler.setAdapter(castsAdapter);
            movieViewHolder.castsRecycler.setLayoutManager(layoutManager);
            return movieViewHolder;
        }else if(viewType==TYPE_FOOTER){
            View view =LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.layout_foot_item,parent,false);
            FootViewHolder footViewHolder =new FootViewHolder(view);
            return footViewHolder;
        }
        return null;
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        public MovieViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Bind(R.id.tv_title)
        TextView tvTitle;
        @Bind(R.id.tv_sub_title)
        TextView subTitle;
        @Bind(R.id.tv_rating_average)
        TextView tvRateAverage;
        @Bind(R.id.tv_rating_stars)
        TextView tvRateStarts;
        @Bind(R.id.img_movie)
        SketchImageView sImg;
        @Bind(R.id.id_recycler_casts)
        RecyclerView castsRecycler;
    }

    public static class FootViewHolder extends RecyclerView.ViewHolder{
        public FootViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
        @Bind(R.id.tv_foot)
        TextView tvFoot;
    }
}
