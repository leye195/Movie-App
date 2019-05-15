package com.example.leeyoungjae.my.View.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.leeyoungjae.my.Controller.MovieService;
import com.example.leeyoungjae.my.Controller.netInterceptor;
import com.example.leeyoungjae.my.Model.Movie;
import com.example.leeyoungjae.my.R;
import com.example.leeyoungjae.my.View.Adapter.MovieListAdapter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AnotherActivity extends AppCompatActivity {
    private SearchView searchView;
    private ListView m_list;
    private List<Movie.itemInfo> list;
    private MovieListAdapter adapter;

    private Retrofit retrofit;
    private MovieService movieService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_another);
        list=new ArrayList<>();
        m_list=findViewById(R.id.movielist);

        LayoutInflater inflater= (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        m_list.addHeaderView(inflater.inflate(R.layout.item_list_top,null));
        m_list.addFooterView(inflater.inflate(R.layout.item_list_bottom,null));
        //searchView=findViewById(R.id.search);
        //searchView.setOnSearchClickListener(new OnSear);
        /*File cacheFile=new File(getApplication().getCacheDir(),"responses");
        int cacheSize=10*1024*1024;
        Cache cache=new Cache(cacheFile,cacheSize);
        OkHttpClient client=new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .connectTimeout(15,TimeUnit.SECONDS)
                .addInterceptor(new netInterceptor(getApplicationContext()))
                .addNetworkInterceptor(new netInterceptor(getApplicationContext()))
                .cache(cache)
                .build();
        retrofit=new Retrofit.Builder()
                .baseUrl("https://openapi.naver.com/v1/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        movieService=retrofit.create(MovieService.class);
        GetMovies(list," ");*/
        if(list.isEmpty()){
            LinearLayout linearLayout=findViewById(R.id.list_empty);
            linearLayout.setVisibility(View.VISIBLE);
            m_list.setEmptyView(linearLayout);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menu,menu);
        android.support.v7.widget.SearchView searchView= (android.support.v7.widget.SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setQueryHint("영화 검색");
        searchView.setOnQueryTextListener(new android.support.v7.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                LinearLayout linearLayout=findViewById(R.id.list_empty);
                linearLayout.setVisibility(View.GONE);
                File cacheFile=new File(getApplication().getCacheDir(),"responses");
                int cacheSize=10*1024*1024;
                Cache cache=new Cache(cacheFile,cacheSize);
                OkHttpClient client=new OkHttpClient.Builder()
                        .retryOnConnectionFailure(true)
                        .connectTimeout(15,TimeUnit.SECONDS)
                        .addInterceptor(new netInterceptor(getApplicationContext()))
                        .addNetworkInterceptor(new netInterceptor(getApplicationContext()))
                        .cache(cache)
                        .build();
                retrofit=new Retrofit.Builder()
                        .baseUrl("https://openapi.naver.com/v1/")
                        .client(client)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                movieService=retrofit.create(MovieService.class);
                list=new ArrayList<>();
                GetMovies(list,s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        MenuItem item1=menu.add(0,0,0,"게시판");
        item1.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(item.getItemId()==0) {
                    Intent intent = new Intent(AnotherActivity.this,MainInfoActivity.class);
                    startActivity(intent);
                }
                return true;
            }
        });
        MenuItem item2=menu.add(0,1,1,"로그아웃");
        item2.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                SharedPreferences sp=getSharedPreferences("config",0);
                SharedPreferences.Editor editor = sp.edit();
                editor.clear();
                editor.commit();
                onBackPressed();
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.action_search){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void GetMovies(final List<Movie.itemInfo>list, String query){

        Call<Movie>call=movieService.getSearch("PaYQH5JdW4zf5Ni1ZzP2","Vl2noxltXw","movie.json",query);
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                m_list=findViewById(R.id.movielist);
                //Toast.makeText(AnotherActivity.this,response.code(),Toast.LENGTH_SHORT).show();
                if(response.isSuccessful()&&response.body()!=null){
                    String str = "response code: " + response.code();
                    Toast.makeText(AnotherActivity.this,str,Toast.LENGTH_LONG).show();
                    List<Movie.itemInfo>tmp=response.body().getItems();
                    System.out.println(response.body().toString());
                    //list.add(null);
                    for(Movie.itemInfo item: tmp){
                        list.add(item);
                    }
                    //list.add(null);
                    for (Movie.itemInfo itemInfo : list) {
                        if(itemInfo!=null)
                            System.out.println(itemInfo.toString());
                    }
                    adapter=new MovieListAdapter(getApplicationContext(),list);
                    //adapter.notifyDataSetChanged();
                    m_list.setAdapter(adapter);
                }else{
                    String str = "response code: " + response.code();
                    Toast.makeText(AnotherActivity.this,str,Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                Log.e("Not Response", t.getLocalizedMessage());
            }
        });
    }
}
