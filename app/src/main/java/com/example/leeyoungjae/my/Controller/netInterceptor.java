package com.example.leeyoungjae.my.Controller;

import android.content.Context;
import android.util.Log;

import com.example.leeyoungjae.my.NetworkUtils;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class netInterceptor implements Interceptor {
    private  Context context;
    public netInterceptor(Context context ){
        this.context=context;
    }
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request=chain.request();
        if(!NetworkUtils.isConnected(context.getApplicationContext())){
            request=request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build();
            Log.d("CacheInterceptor","no network");
        }
        Response response=chain.proceed(request);
        if(NetworkUtils.isConnected(context.getApplicationContext())){
            String cacheControl=request.cacheControl().toString();
            int maxAge=120*5;
            return response.newBuilder()
                    .header("Cache-Control","public, max-age="+maxAge)
                    .removeHeader("Pragma")
                    .build();
        }else{
            int maxTime=4*24*60*60;
            return response.newBuilder()
                    .header("Cache-Control","public, only-if-cached, max-stale="+maxTime)
                    .removeHeader("Pragma")
                    .build();
        }
    }
}
