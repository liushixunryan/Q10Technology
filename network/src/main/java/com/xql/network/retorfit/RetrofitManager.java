package com.xql.network.retorfit;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @ClassName: RetrofitManager
 * @Description: 网络类
 * @CreateDate: 2022/5/26 14:50
 * @UpdateUser: RyanLiu
 */

public class RetrofitManager {
    // 请求的URL前缀  要以"/"结尾
    private static final String BASEURL = "https://cdnm.shidongvr.com:81/cdnmpi";
    private static Retrofit retrofit;
    private static RetrofitManager retrofitManager;

    //提供共有的方法供外界访问
    public static RetrofitManager newInstance() {
        if (retrofitManager == null) {
            synchronized (RetrofitManager.class) {
                retrofitManager = new RetrofitManager();
            }
        }
        return retrofitManager;
    }

    /**通过动态代理生成相应的Http请求
     *
     * @param t
     * @param <T>
     * @return
     */
    public <T> T creat(Class<T> t) {
        return retrofit.create(t);
    }

    /**构造方法私有化
     *
     */
    private RetrofitManager() {
        retrofit = getRetrofit();
    }

    /**构建Ok请求
     *
     * @return
     */
    private OkHttpClient getOkHttpClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor()
                        //添加打印拦截器
                        .setLevel(HttpLoggingInterceptor.Level.BODY))
                //设置请求超时时间
                .connectTimeout(30, TimeUnit.SECONDS)
                //设置写入超时时间
                .writeTimeout(30, TimeUnit.SECONDS)
                //设置读取超时时间
                .readTimeout(30, TimeUnit.SECONDS)
                //设置出现错误进行重新连接。
                .retryOnConnectionFailure(true)
                //设置token请求头
                .addNetworkInterceptor(new TokenHeaderInterceptor())
                .build();
    }


    /**
     * 构建Retrofit
     *
     * @return Retrofit
     */
    private Retrofit getRetrofit() {
        return new Retrofit.Builder()
                //设置网络请求BaseUrl地址
                .baseUrl(BASEURL)
                //设置数据解析器
                .client(getOkHttpClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
