package com.chen.me.rrokhttpsamp.recycleDemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.chen.me.rrokhttpsamp.R;
import com.chen.me.rrokhttpsamp.adapter.HomeAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity {

    @Bind(R.id.id_recycler_demo)
    protected RecyclerView mRecycler;
    private List<String> mDatas;
    private HomeAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        initData();
        mAdapter=new HomeAdapter(HomeActivity.this,mDatas);
        mRecycler.setAdapter(mAdapter);
//        mRecycler.setLayoutManager(new GridLayoutManager(this,4));
//        mRecycler.addItemDecoration(new DividerItemDecoration(HomeActivity.this,LinearLayoutManager.VERTICAL));
        mRecycler.addItemDecoration(new DividerGridItemDecoration(HomeActivity.this));
        mRecycler.setLayoutManager(new StaggeredGridLayoutManager(4,StaggeredGridLayoutManager.HORIZONTAL));
    }

    protected void initData()
    {
        mDatas = new ArrayList<String>();
        for (int i = 'A'; i < 'z'; i++)
        {
            mDatas.add("" + (char) i);
        }
    }
}
