package com.example.leeyoungjae.my;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkStatus {
    public static final int TYPE_WIFI=1;
    public static final int TYPE_MOBILE=2;
    public static final int TYPE_UNCONNECTED=3;
    public static int getConnectivityStatus(Context context){
        //Context객체를 전달 받는 이유는 getSystemService를 호출해야하기 때문
        ConnectivityManager manager=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=manager.getActiveNetworkInfo();
        if(networkInfo!=null&&networkInfo.isConnected()){
            return 0;
            //int type=networkInfo.getType();
            //if(type==ConnectivityManager.TYPE_WIFI){
            //    return TYPE_WIFI;
            //}else if(type==ConnectivityManager.TYPE_MOBILE){
            //    return TYPE_MOBILE;
            //}
        }
        return TYPE_UNCONNECTED;
    }
}
