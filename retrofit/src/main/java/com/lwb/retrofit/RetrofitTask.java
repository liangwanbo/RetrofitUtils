package com.lwb.retrofit;

import android.annotation.SuppressLint;
import android.app.Application;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

public class RetrofitTask {

    private OkHttpClient okHttpClient;
    private String baseUrl;

    @SuppressLint("StaticFieldLeak")
    private static RetrofitTask instance;
    @SuppressLint("StaticFieldLeak")
    private static Application context;

    public static RetrofitTask getInstance() {
        if (instance == null) {
            synchronized (RetrofitTask.class) {
                if (instance == null) {
                    instance = new RetrofitTask();
                }
            }

        }
        return instance;
    }

    /**
     * 必须在全局Application先调用，获取context上下文，否则缓存无法使用
     *
     * @param app Application
     */
    public RetrofitTask init(Application app) {
        context = app;
        return this;
    }

    /**
     * 检测是否调用初始化方法
     */
    private static void checkInitialize() {
        if (context == null) {
            throw new ExceptionInInitializerError("请先在全局Application中调用 RxHttpUtils.getInstance().init(this) 初始化！");
        }
    }


    public RetrofitTask config() {
        checkInitialize();
        return RetrofitTask.getInstance();
    }

    /**
     * 使用全局参数创建请求
     *
     * @param <K> K
     * @param url
     * @param cls Class
     * @return 返回
     */
    public <K> K createApi( Class<K> cls) {
        return createApis("",cls);
    }

    public RetrofitTask setOkClient(OkHttpClient okHttpClient) {
        this.okHttpClient = okHttpClient;
        return this;
    }

    public RetrofitTask setBaseUrl(String baseUrl) {
        this.baseUrl=baseUrl;
        return this;
    }

    public <K> K createApis(String url,Class<K> apiClass){
        Retrofit retrofit = new RetrofitBuilder()
                .setBaseUrl(baseUrl)
                .setOkHttpClient(okHttpClient)
                .build();
        return retrofit.create(apiClass);
    }




}
