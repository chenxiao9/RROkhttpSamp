package com.chen.me.rrokhttpsamp.api;

import com.chen.me.rrokhttpsamp.bean.MovieEntity;



import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2016/10/13 0013.
 */

public interface MovieServiceApi {
    @GET("top250")
    Observable<MovieEntity> getTopMovie(@Query("start") int start, @Query("count") int count);
}
