package com.chen.me.rrokhttpsamp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chen.me.rrokhttpsamp.R;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/10/17 0017.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {

    private LayoutInflater mInflater;
    private List<String> mDatas;

    public HomeAdapter(Context context, List<String> mDatas) {
        this.mDatas = mDatas;
        this.mInflater= LayoutInflater.from(context);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    @Override
    public void onBindViewHolder(HomeAdapter.MyViewHolder holder, int position) {
        holder.tv.setText(mDatas.get(position));
    }

    @Override
    public HomeAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=mInflater.inflate(R.layout.layout_home_item,parent,false);
        HomeAdapter.MyViewHolder holder=new HomeAdapter.MyViewHolder(view);
        return holder;
    }

     public static class MyViewHolder extends RecyclerView.ViewHolder{
         public MyViewHolder(View itemView) {
            super(itemView);
            tv=(TextView) itemView.findViewById(R.id.id_num);
        }
        TextView tv;
    }
}
