package com.chen.me.rrokhttpsamp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.chen.me.rrokhttpsamp.Gallery.GalleryActivity;
import com.chen.me.rrokhttpsamp.MovieDetail.MovieActivity;
import com.chen.me.rrokhttpsamp.api.MovieService;
import com.chen.me.rrokhttpsamp.bean.MovieEntity;
import com.chen.me.rrokhttpsamp.recycleDemo.HomeActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    public MovieEntity movieData;

    @Bind(R.id.tv_result)
    public TextView tvResult;
//    @Bind(R.id.btn_click_me)
//    public Button btnClick;
//    @Bind(R.id.btn_detail)
//    public Button btnDetail;
//    @Bind(R.id.btn_recycle)
//    public Button btnRecycleDemo;
//    @Bind(R.id.btn_get_movie)
//    public Button btnGetMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_click_me)
    public void click(View view){
        getMovie();
    }

    @OnClick(R.id.btn_detail)
    public void geDetail(View view){
        Intent i=new Intent(MainActivity.this, GalleryActivity.class);
        startActivity(i);
    }

    @OnClick(R.id.btn_recycle)
    public void goRecycleDemo(View view){
        Intent i=new Intent(MainActivity.this, HomeActivity.class);
        startActivity(i);
    }


    @OnClick(R.id.btn_get_movie)
    public void getMovieDetail(View view){
        Intent i=new Intent(MainActivity.this, MovieActivity.class);
        i.putExtra("movieData",movieData);
        startActivity(i);
    }

    //进行网络请求
    private void getMovie(){

        MovieService.getInstance().getMovie(0, 10)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Subscriber<MovieEntity>() {
            @Override
            public void onCompleted() {
                Toast.makeText(MainActivity.this, "Get Top Movie Completed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable e) {
                tvResult.setText(e.toString());
                Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNext(MovieEntity movieEntity) {
                movieData=movieEntity;
                tvResult.setText(movieEntity.toString());
            }
        });

    }
}
