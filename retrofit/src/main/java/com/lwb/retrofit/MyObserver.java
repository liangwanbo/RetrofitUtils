package com.lwb.retrofit;



import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;
import retrofit2.adapter.rxjava2.HttpException;


public abstract class MyObserver implements Observer<ResponseBody> {

    /**
     * 失败回调
     *
     * @param errorMsg
     */
    protected abstract void onError(String code,String errorMsg);

    /**
     * 成功回调
     *
     * @param
     */
    protected abstract void onSuccess(ResponseBody responseBody);

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(ResponseBody responseBody) {
        onSuccess(responseBody);
    }

    @Override
    public void onError(Throwable e) {

        if(e instanceof HttpException){
            HttpException httpException = (HttpException) e;
            try {
                String responseString=httpException.response().errorBody().string();
                JSONObject jsonObject=new JSONObject(responseString);
                onError(jsonObject.getString("code"),jsonObject.getString("message"));
                if(jsonObject.getString("code").equals("403")){
                }else{
                }

            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (JSONException ex) {
                ex.printStackTrace();
            }
        }

    }

    @Override
    public void onComplete() {

    }
}
