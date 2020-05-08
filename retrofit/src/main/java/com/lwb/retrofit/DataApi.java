package com.lwb.retrofit;


import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * @author liangwanbo
 * @date 2018/3/1
 */
public interface DataApi {

    /**
     * 验证码接口
     */

    @POST("cs_user/sendTextMsg")
    Observable<ResponseBody> Yzm(@Query("phone") String phone);







}
