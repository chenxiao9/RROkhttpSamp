package com.chen.me.rrokhttpsamp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chen.me.rrokhttpsamp.R;
import com.chen.me.rrokhttpsamp.bean.Movie.Cast;
import com.chen.me.rrokhttpsamp.bean.Movie.Director;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.xiaopan.sketch.SketchImageView;

/**
 * Created by Administrator on 2016/10/19 0019.
 */

public class CastsAdapter extends RecyclerView.Adapter {

    private List<Director> director;
    private List<Cast> cast;

    private int getDirectorSize() {
        return director == null ? 0 : director.size();
    }

    private int getCastSize() {
        return cast == null ? 0 : cast.size();
    }

    public void setCast(List<Cast> cast) {
        this.cast = cast;
    }

    public void setDirector(List<Director> director) {
        this.director = director;
    }

    public List<Cast> getCast() {
        return cast;
    }

    public List<Director> getDirector() {
        return director;
    }

    @Override
    public int getItemCount() {
        return getDirectorSize() + getCastSize();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int dirSize = getDirectorSize();
        int castSize = getCastSize();
        CastViewHolder theHolder = (CastViewHolder) holder;
        if (position < dirSize) {
            Director dir = director.get(position);
            theHolder.imgView.getOptions().setResizeByFixedSize(true).setForceUseResize(true);
            theHolder.imgView.displayImage(dir.getAvatars().getLarge());
            theHolder.tvName.setText(dir.getName());
            theHolder.tvLink.setText(dir.getAlt());
        } else if (position >= dirSize && position < dirSize + castSize) {
            Cast cast1 = cast.get(position - dirSize);
            theHolder.imgView.getOptions().setResizeByFixedSize(true).setForceUseResize(true);
            theHolder.imgView.displayImage(cast1.getAvatars().getLarge());
            theHolder.tvName.setText(cast1.getName());
            theHolder.tvLink.setText(cast1.getAlt());
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_cast_item, parent, false);
        CastViewHolder holder = new CastViewHolder(view);
        return holder;
    }

    public static class CastViewHolder extends RecyclerView.ViewHolder {
        public CastViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Bind(R.id.img_cast)
        SketchImageView imgView;
        @Bind(R.id.tv_cast_name)
        TextView tvName;
        @Bind(R.id.tv_cast_link)
        TextView tvLink;
    }
}
