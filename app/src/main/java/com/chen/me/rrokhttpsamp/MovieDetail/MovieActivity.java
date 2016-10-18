package com.chen.me.rrokhttpsamp.MovieDetail;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.chen.me.rrokhttpsamp.R;
import com.chen.me.rrokhttpsamp.adapter.MovieAdapter;
import com.chen.me.rrokhttpsamp.bean.Movie.Subject;
import com.chen.me.rrokhttpsamp.bean.MovieEntity;
import com.google.gson.Gson;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MovieActivity extends AppCompatActivity {

    private MovieEntity mData;
    private List<Subject> movieItems;

    @Bind(R.id.recycler_movie)
    public RecyclerView mRecycler;

    private MovieAdapter movieAdapter;

    public Gson gson=new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        ButterKnife.bind(this);

        mData=gson.fromJson(getIntent().getStringExtra("movieData"),MovieEntity.class);
        movieItems=mData.getSubjects();
        movieAdapter=new MovieAdapter(MovieActivity.this,movieItems);
        mRecycler.setAdapter(movieAdapter);
        mRecycler.setLayoutManager(new LinearLayoutManager(MovieActivity.this,LinearLayoutManager.HORIZONTAL,false));
    }
}
