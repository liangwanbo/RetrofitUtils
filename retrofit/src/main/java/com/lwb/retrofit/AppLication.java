package com.lwb.retrofit;

import android.app.Application;
import android.content.Context;

import java.util.HashMap;
import java.util.Map;

import okhttp3.OkHttpClient;

public class AppLication extends Application {
    public static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        RXManager();
    }

    public void RXManager(){
        OkHttpClient okHttpClient = new OkHttpConfig
                .Builder(this)
                //全局的请求头信息
                .setHeaders(new BuildHeadersListener() {
                    @Override
                    public Map<String, String> buildHeaders() {
                        HashMap<String, String> hashMap = new HashMap<>();
                        hashMap.put("appVersion", BuildConfig.VERSION_NAME);
                        hashMap.put("client", "android");
                        hashMap.put("Authorization","");
                        return hashMap;
                    }
                })
                //开启缓存策略(默认false)
                //1、在有网络的时候，先去读缓存，缓存时间到了，再去访问网络获取数据；
                //2、在没有网络的时候，去读缓存中的数据。
                .setCache(false)
                .setHasNetCacheTime(10)//默认有网络时候缓存60秒
                .setNoNetCacheTime(3600)//默认有网络时候缓存3600秒
                //全局持久话cookie,保存到内存（new MemoryCookieStore()）或者保存到本地（new SPCookieStore(this)）
                //不设置的话，默认不对cookie做处理
                //.setCookieType(new SPCookieStore(this))
                //可以添加自己的拦截器(比如使用自己熟悉三方的缓存库等等)
                //.setAddInterceptor(null)
                //全局ssl证书认证
                //1、信任所有证书,不安全有风险（默认信任所有证书）
                //.setSslSocketFactory()
                //2、使用预埋证书，校验服务端证书（自签名证书）
                //.setSslSocketFactory(cerInputStream)
                //3、使用bks证书和密码管理客户端证书（双向认证），使用预埋证书，校验服务端证书（自签名证书）
                //.setSslSocketFactory(bksInputStream,"123456",cerInputStream)
                //全局超时配置
                .setReadTimeout(10)
                //全局超时配置
                .setWriteTimeout(10)
                //全局超时配置
                .setConnectTimeout(5)
                //全局是否打开请求log日志
                .setDebug(true)
                .build();


        RetrofitTask.getInstance()
                .init(this)
                .config()
                .setBaseUrl("你的baseurl")
                .setOkClient(okHttpClient);
    }

    public static Context getContext() {
        return mContext;
    }



}
