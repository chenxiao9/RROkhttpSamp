package com.chen.me.rrokhttpsamp.Gallery;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;

import com.chen.me.rrokhttpsamp.R;
import com.chen.me.rrokhttpsamp.adapter.GalleryAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class GalleryActivity extends AppCompatActivity {

    private MRecycleView mRecyclerView;
    private GalleryAdapter mAdapter;
    private List<Integer> mDatas;

    @Bind(R.id.id_content)
    public ImageView mImg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_gallery);
        ButterKnife.bind(this);

        initDatas();
        mRecyclerView=(MRecycleView) findViewById(R.id.id_recycler_view);
        //设置布局管理器
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        //设置适配器
        mAdapter=new GalleryAdapter(GalleryActivity.this,mDatas);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.setmItemScrollChangeListener(new MRecycleView.OnItemScrollChangeListener() {
            @Override
            public void onChange(View view, int position) {
                mImg.setImageResource(mDatas.get(position));
            }
        });

        mAdapter.setItemClickLitener(new GalleryAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(GalleryActivity.this, position+"", Toast.LENGTH_SHORT)
                        .show();
            }
        });

    }

    private void initDatas()
    {
        mDatas = new ArrayList<Integer>(Arrays.asList(R.mipmap.a,
                R.mipmap.b, R.mipmap.c, R.mipmap.d, R.mipmap.e,
                R.mipmap.f, R.mipmap.g, R.mipmap.h, R.mipmap.l));
    }
}
