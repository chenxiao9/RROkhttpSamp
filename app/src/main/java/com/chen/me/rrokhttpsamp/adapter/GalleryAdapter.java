package com.chen.me.rrokhttpsamp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chen.me.rrokhttpsamp.R;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/10/17 0017.
 */

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.MyViewHolder> {

    private LayoutInflater mInflater;
    private List<Integer> mDatas;



    public GalleryAdapter(Context context , List<Integer> mDatas ) {
        this.mDatas = mDatas;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.mImg.setImageResource(mDatas.get(position));
        if (itemClickLitener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemClickLitener.onItemClick(holder.itemView,position);
                }
            });
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=mInflater.inflate(R.layout.layout_item,parent,false);
        MyViewHolder viewHolder=new MyViewHolder(view);
        viewHolder.mImg=(ImageView) view.findViewById(R.id.id_index_gallery_item_image);
        return viewHolder;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(itemView);
        }

        ImageView mImg;
        TextView mTxt;
    }

    /**
     * ItemClick回调接口
     */
    public interface OnItemClickLitener{
        void onItemClick(View view, int position);
    }

    private OnItemClickLitener itemClickLitener;

    public void setItemClickLitener(OnItemClickLitener itemClickLitener) {
        this.itemClickLitener = itemClickLitener;
    }
}
