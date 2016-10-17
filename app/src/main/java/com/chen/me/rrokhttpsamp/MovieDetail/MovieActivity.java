package com.chen.me.rrokhttpsamp.MovieDetail;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.chen.me.rrokhttpsamp.R;
import com.chen.me.rrokhttpsamp.bean.MovieEntity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MovieActivity extends AppCompatActivity {

    private MovieEntity mData;
    @Bind(R.id.tv_data)
    public TextView tvData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        ButterKnife.bind(this);

        mData=(MovieEntity) getIntent().getSerializableExtra("movieData");
        tvData.setText(mData.getCount());
    }
}
