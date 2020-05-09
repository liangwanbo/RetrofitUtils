package com.lwb.retrofitutils;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.lwb.retrofit.DataApi;
import com.lwb.retrofit.MyObserver;
import com.lwb.retrofit.RetrofitTask;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        RetrofitTask.getInstance()
                .createApi(DataApi.class)
                .Yzm("phone")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new MyObserver() {
                    @Override
                    protected void onError(String code, String errorMsg) {

                    }

                    @Override
                    protected void onSuccess(ResponseBody responseBody) {

                    }
                });



    }



}
