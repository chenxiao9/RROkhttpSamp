package com.chen.me.rrokhttpsamp.api;

import android.util.Log;

import com.chen.me.rrokhttpsamp.bean.MovieEntity;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Created by Administrator on 2016/10/13 0013.
 */

public class MovieService {
    private static final String MOVIE_URL="https://api.douban.com/v2/movie/";

    private static final String TAG="RROSample";

    private static class LogIntercept implements Interceptor{
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request=chain.request();
            Log.e(TAG, "okhttp: "+request.toString());

            Response response=chain.proceed(request);

            String content=response.body().toString();
            Log.e(TAG, "body: "+content);//输出返回信息


            return response;
        }
    }
    //初始化OkHttp3的客户端
     private static OkHttpClient client=new OkHttpClient.Builder()
            .addInterceptor(new LogIntercept())
             .retryOnConnectionFailure(true).build();

    //初始化Retrofit
    private static final Retrofit sRetrofit=new Retrofit.Builder()
            .baseUrl(MOVIE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())//加入json解析
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())// 使用RxJava作为回调适配器
            .build();

    private final static MovieServiceApi movieServiceApi =sRetrofit.create(MovieServiceApi.class);

    //在访问HttpMethods时创建单例
    private static class SingletonHolder{
        private static final MovieService INSTANCE = new MovieService();
    }

    //获取单例
    public static MovieService getInstance(){
        return SingletonHolder.INSTANCE;
    }

    /**
     * 用于获取豆瓣电影Top250的数据
     * @param subscriber  由调用者传过来的观察者对象
     * @param start 起始位置
     * @param count 获取长度
     */
    public Observable<MovieEntity> getMovie(int start, int count){
        return movieServiceApi.getTopMovie(start, count);

    }

}
