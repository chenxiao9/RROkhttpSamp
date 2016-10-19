package com.chen.me.rrokhttpsamp.MovieDetail;

import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.widget.Toast;

import com.chen.me.rrokhttpsamp.R;
import com.chen.me.rrokhttpsamp.adapter.MovieAdapter;
import com.chen.me.rrokhttpsamp.api.MovieService;
import com.chen.me.rrokhttpsamp.bean.Movie.Subject;
import com.chen.me.rrokhttpsamp.bean.MovieEntity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MovieActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener
{

    private MovieEntity mData;
    private List<Subject> movieItems;
    private int start=0;
    private Subscription mSubscription;

    @Bind(R.id.recycler_movie)
    public RecyclerView mRecycler;
    @Bind(R.id.swipe_refresh)
    public SwipeRefreshLayout swipeRefreshLayout;

    private MovieAdapter movieAdapter;

//    public Gson gson=new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        ButterKnife.bind(this);

        movieItems=new ArrayList<>();
        movieAdapter=new MovieAdapter();
        movieAdapter.setMovieData(movieItems);
        mRecycler.setAdapter(movieAdapter);

        final LinearLayoutManager layoutManager=new LinearLayoutManager(MovieActivity.this,LinearLayoutManager.VERTICAL,false);
        mRecycler.setLayoutManager(layoutManager);
        mRecycler.setItemAnimator(new DefaultItemAnimator());
        mRecycler.setOnScrollListener(new RecyclerView.OnScrollListener() {
            int lastVisibleItem=0;
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem=layoutManager.findLastVisibleItemPosition();
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && lastVisibleItem + 1 == movieAdapter.getItemCount()) {
                    // 此处在现实项目中，请换成网络请求数据代码，sendRequest .....
                    //
                    sendRequest();
                }
            }
        });

        swipeRefreshLayout.setColorSchemeColors(Color.BLUE, Color.GREEN, Color.RED, Color.YELLOW);
        swipeRefreshLayout.setEnabled(false);
        swipeRefreshLayout.setOnRefreshListener(this);
        // 这句话是为了，第一次进入页面的时候显示加载进度条
        swipeRefreshLayout.setProgressViewOffset(false, 0,
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources().getDisplayMetrics()));
        sendRequest();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onRefresh() {

    }

    rx.Observer<MovieEntity> observer=new Observer<MovieEntity>() {
        @Override
        public void onCompleted() {
            start+=10;
            Toast.makeText(MovieActivity.this, "Get Top Movie Completed", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onError(Throwable e) {
            swipeRefreshLayout.setRefreshing(false);
            Toast.makeText(MovieActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }

        @Override
        public void onNext(MovieEntity movieEntity) {
            swipeRefreshLayout.setRefreshing(false);
            mData=movieEntity;

            movieItems.addAll(mData.getSubjects());
            movieAdapter.notifyDataSetChanged();
        }
    };

    private void sendRequest(){
        swipeRefreshLayout.setRefreshing(true);
        mSubscription=MovieService.getInstance()
                .getMovie(start, 10)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
