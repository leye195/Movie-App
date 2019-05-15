package com.example.leeyoungjae.my.Controller;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class LogInterceptor implements Interceptor {
    private static final String TAG = LogInterceptor.class.getSimpleName();
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request=chain.request();
        Log.d(TAG,"Intercept:request method "+request.method());
        Log.d(TAG,"Intercept:request url "+ request.headers());
        Log.d(TAG,"Intercept:connection "+chain.connection());

        Response response=chain.proceed(request);
        Log.d(TAG,"////////////////////////////////////////////////");
        Log.d(TAG,"Intercept: response requested url"+response.request().url());
        Log.d(TAG,"Intercept: response header "+response.headers());
        return null;
    }
}
