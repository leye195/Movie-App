package com.example.leeyoungjae.my.View.Activity;

import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Outline;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.leeyoungjae.my.DB.DAO.outlineDAO;
import com.example.leeyoungjae.my.DB.DBHelper;
import com.example.leeyoungjae.my.Controller.MovieListService;
import com.example.leeyoungjae.my.Controller.onClickBtnCallBack;
import com.example.leeyoungjae.my.DB.Entity.outline;
import com.example.leeyoungjae.my.DB.MovieDBHelper;
import com.example.leeyoungjae.my.Model.MovieInfo;
import com.example.leeyoungjae.my.Model.MovieList;
import com.example.leeyoungjae.my.NetworkStatus;
import com.example.leeyoungjae.my.R;
import com.example.leeyoungjae.my.View.Adapter.MovieViewPagerAdapter;
import com.example.leeyoungjae.my.View.Fragment.DetailFragment;
import com.example.leeyoungjae.my.View.Fragment.MovieFragment;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieListActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, onClickBtnCallBack {
    private ViewPager pager;
    private TextView user_id;
    private MovieViewPagerAdapter adapter;
    private MovieFragment f1;
    private MovieFragment f2;
    private MovieFragment f3;
    private MovieFragment f4;
    private MovieFragment f5;
    private DetailFragment detailFragment;
    private Retrofit retrofit;
    private MovieListService movieService;
    private String id="";

    private Animation up;
    private Animation down;
    private ImageView order_img;
    private ImageView ord_1;
    private ImageView ord_2;
    private ImageView ord_3;
    private LinearLayout order_container;
    private boolean isShown=false;

    private  MovieDBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        user_id=navigationView.getHeaderView(0).findViewById(R.id.user_id);
        CheckUser();

        adapter=new MovieViewPagerAdapter(getSupportFragmentManager());
        pager=findViewById(R.id.pager);
        pager.setOffscreenPageLimit(3);
        retrofit=new Retrofit.Builder()
                .baseUrl("http://boostcourse-appapi.connect.or.kr:10000/movie/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        movieService=retrofit.create(MovieListService.class);

        order_img=findViewById(R.id.order_img);
        order_container=findViewById(R.id.order_container);
        up= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.translate_up);
        up.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                order_container.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        down= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.translate_down);
        order_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isShown){
                    order_container.startAnimation(up);
                }else{
                    order_container.setVisibility(View.VISIBLE);
                    order_container.startAnimation(down);
                }
                isShown=!isShown;
            }
        });
        ord_1=findViewById(R.id.order1);
        ord_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                order_img.setImageResource(R.drawable.order11);
                order_container.startAnimation(up);
                isShown=!isShown;
                adapter=new MovieViewPagerAdapter(getSupportFragmentManager());
                if(NetworkStatus.getConnectivityStatus(getApplicationContext())==0) {
                    getMovieList("1");
                }else{
                    Cursor c=DBHelper.selectData("outline",1);
                    while(c.moveToNext()){
                        String id=String.valueOf(c.getInt(0));
                        String title=c.getString(1);
                        String grade=String.valueOf(c.getInt(9));
                        String reservation_rate=String.valueOf(c.getFloat(7));
                        String img=c.getString(11);
                        MovieFragment ff=initFragment(id,title,grade,reservation_rate,img);
                        adapter.addItem(ff);
                    }
                    pager.setAdapter(adapter);
                    c.close();
                }
            }
        });
        ord_2=findViewById(R.id.order2);
        ord_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                order_img.setImageResource(R.drawable.order22);
                order_container.startAnimation(up);
                isShown=!isShown;
                adapter=new MovieViewPagerAdapter(getSupportFragmentManager());
                if(NetworkStatus.getConnectivityStatus(getApplicationContext())==0) {
                    getMovieList("2");
                }else{
                    Cursor c=DBHelper.selectData("outline",2);
                    while(c.moveToNext()){
                        String id=String.valueOf(c.getInt(0));
                        String title=c.getString(1);
                        String grade=String.valueOf(c.getInt(9));
                        String reservation_rate=String.valueOf(c.getFloat(7));
                        String img=c.getString(11);
                        MovieFragment ff=initFragment(id,title,grade,reservation_rate,img);
                        adapter.addItem(ff);
                    }
                    pager.setAdapter(adapter);
                    c.close();

                }
            }
        });
        ord_3=findViewById(R.id.order3);
        ord_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                order_img.setImageResource(R.drawable.order33);
                order_container.startAnimation(up);
                isShown=!isShown;
                adapter=new MovieViewPagerAdapter(getSupportFragmentManager());
                if(NetworkStatus.getConnectivityStatus(getApplicationContext())==0) {
                    getMovieList("3");
                }else{
                    Cursor c=DBHelper.selectData("outline",3);
                    while(c.moveToNext()){
                        String id=String.valueOf(c.getInt(0));
                        String title=c.getString(1);
                        String grade=String.valueOf(c.getInt(9));
                        String reservation_rate=String.valueOf(c.getFloat(7));
                        String img=c.getString(11);
                        MovieFragment ff=initFragment(id,title,grade,reservation_rate,img);
                        adapter.addItem(ff);
                    }
                    pager.setAdapter(adapter);
                    c.close();
                }
            }
        });

        //DBHelper.openDB(getApplicationContext(),"outline");
        db=MovieDBHelper.getDatabase(getApplicationContext());
        if(NetworkStatus.getConnectivityStatus(getApplicationContext())==0){
            //DBHelper.createTable("outline");
            getMovieList("1");
        }else{
            backgroundTask task=new backgroundTask();
            task.execute();
            //DBHelper.DropTable("outline");

            /*Cursor c=DBHelper.selectData("outline",1);
            while(c.moveToNext()){
                String id=String.valueOf(c.getInt(0));
                String title=c.getString(1);
                String grade=String.valueOf(c.getInt(9));
                String reservation_rate=String.valueOf(c.getFloat(7));
                String img=c.getString(11);
                MovieFragment ff=initFragment(id,title,grade,reservation_rate,img);
                adapter.addItem(ff);
            }
            pager.setAdapter(adapter);
            //c.close();*/
        }

    }
    public void getMovieList(String type){
        Call<MovieList>call=movieService.readMovieList(type);
        call.enqueue(new Callback<MovieList>() {
            @Override
            public void onResponse(Call<MovieList> call, Response<MovieList> response) {
                Log.d("From API", String.valueOf(response.code()));
                if(response.code()==200){
                    ArrayList<MovieInfo>list=response.body().getResult();
                    //Log.d("From API", String.valueOf(list.size()));

                    for(MovieInfo item:list){
                        MovieFragment ff=initFragment(item.getId(),item.getTitle(),item.getGrade(),item.getReservation_rate(),item.getImage());
                        if(ff!=null) {
                            outline obj=new outline(Integer.parseInt(item.getId()),item.getTitle(),item.getTitle_eng(),item.getDate(),Float.parseFloat(item.getUser_rating()),
                                    Float.parseFloat(item.getAudience_rating()),Float.parseFloat(item.getReviewer_rating()),Float.parseFloat(item.getReservation_rate()),
                                    Integer.parseInt(item.getReservation_grade()),Integer.parseInt(item.getGrade()),item.getThumb(),item.getImage());
                            AsyncInsert insertOutline=new AsyncInsert();
                            insertOutline.execute(obj);
                            //Object obj=item;
                            //DBHelper.insertData("outline",obj);
                            Log.d("From API", "Not Null");
                            adapter.addItem(ff);
                        }
                    }
                    Log.d("From AP", String.valueOf(adapter.getCount()));
                    pager.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<MovieList> call, Throwable t) {

            }
        });
    }
    public MovieFragment initFragment(String m_id,String title,String age,String rate,String img){
        MovieFragment f=new MovieFragment();
        Bundle bundle=new Bundle();
        bundle.putString("movieid",m_id);
        bundle.putString("title",title);
        bundle.putString("age",age);
        bundle.putString("rate",rate);
        //bundle.putInt("idx",idx);
        bundle.putString("img",img);
        f.setArguments(bundle);
        return f;
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.movie_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_movie_list) {
            Intent intent=new Intent(MovieListActivity.this,MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP|
                    Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
        } else if (id == R.id.nav_movie_search) {
            Intent intent=new Intent(MovieListActivity.this,AnotherActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP|
                    Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
        } else if (id == R.id.nav_movie_api) {

        } else if (id == R.id.nav_buy) {

        } else if (id == R.id.nav_setting) {

        } else if (id == R.id.nav_logout){

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void CheckUser(){
        SharedPreferences sp=getSharedPreferences("config", 0);
        if(sp!=null&&sp.contains("id")){
            id=sp.getString("id","");
            user_id.setText(id);
        }
    }
    @Override
    public void onChangeFragment(String title,String age,String rate,int idx) {
        detailFragment=new DetailFragment();
        Bundle bundle=new Bundle();
        bundle.putString("title",title);
        bundle.putString("age",age);
        bundle.putString("rate",rate);
        bundle.putInt("img",idx);
        detailFragment.setArguments(bundle);
        FragmentManager manager=getSupportFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction();
        transaction.replace(R.id.container,detailFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    public class AsyncInsert extends AsyncTask<Object,Object,Void>{

        @Override
        protected Void doInBackground(Object... objects) {
            Object obj=objects[0];
            if(obj instanceof outline){
                outlineDAO outline=db.getOutlineDAO();
                outline.InsertOutline((com.example.leeyoungjae.my.DB.Entity.outline) obj);
            }
            return null;
        }
    }
    public class backgroundTask extends AsyncTask<Void, Void, Void>{
        List<outline> out_list;
        ArrayList<Fragment>list;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            out_list=new ArrayList<>();
            list=new ArrayList<>();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            outlineDAO outlineDAO=db.getOutlineDAO();
            List<outline> out_list=outlineDAO.getoutline_ord1();
            //Log.d("From Background",String.valueOf(outlineDAO.getoutline_ord1().size()));
            for(outline item:out_list) {
                MovieFragment ff = initFragment(String.valueOf(item.getId()), item.getTitle(), String.valueOf(item.getGrade()), String.valueOf(item.getReservation_rate()), item.getImage());
                list.add(ff);
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            for(int i=0;i<list.size();i++)
                adapter.addItem(list.get(i));
            pager.setAdapter(adapter);
        }
    }
}
