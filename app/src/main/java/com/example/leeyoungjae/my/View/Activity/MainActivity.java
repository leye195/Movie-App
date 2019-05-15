package com.example.leeyoungjae.my.View.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.leeyoungjae.my.Controller.UserService;
import com.example.leeyoungjae.my.Model.User;
import com.example.leeyoungjae.my.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private EditText idtxt;
    private EditText pwdtxt;
    private Button btn1;
    private Button btn2;

    private Retrofit retrofit;
    private UserService userService;
    private SharedPreferences sp;

    private String u_id=null;
    private String u_pwd=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadLoginInfo();
        idtxt=findViewById(R.id.userid);
        pwdtxt=findViewById(R.id.pwd);
        btn1=findViewById(R.id.login);
        btn2=findViewById(R.id.get);

        //객체 생성
        retrofit=new Retrofit.Builder()
                .baseUrl("http://10.0.2.2/develop/Travel/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        userService=retrofit.create(UserService.class);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uid,pwd;
                uid=idtxt.getText().toString();
                pwd=pwdtxt.getText().toString();
                PostDate(new User(uid,pwd));
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,SignUpActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
    public void loadLoginInfo(){
        sp=getSharedPreferences("config",0);
        u_id=sp.getString("id","");
        u_pwd=sp.getString("password","");
        if(!u_id.equals("")&&!u_pwd.equals("")){
            Intent intent=new Intent(this,MovieListActivity.class);
            intent.putExtra("id",u_id);
            startActivity(intent);
        }
    }
    public void saveLoginInfo(Context context,String id,String password){
        sp=getSharedPreferences("config",0);
        SharedPreferences.Editor editor=sp.edit();
        editor.putString("id",id);
        editor.putString("password",password);
        editor.commit();
    }

    public void PostDate(final User user){
        Call<User>call=userService.postRespos(user.getId(),user.getPassword());
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                //Toast.makeText(MainActivity.this,"???",Toast.LENGTH_SHORT).show();
                if(response.isSuccessful()){
                    String str = "response code: " + response.code()+" "+response.body().getId()+" "+response.body().getPassword();
                    //Toast.makeText(MainActivity.this,str,Toast.LENGTH_SHORT).show();
                    if(response.body().getId().equals(user.getId())&&response.body().getPassword().equals(user.getPassword()))
                    {
                        Intent intent=new Intent(MainActivity.this,MovieListActivity.class);
                        intent.putExtra("id",response.body().getId());
                        saveLoginInfo(MainActivity.this,response.body().getId(),response.body().getPassword());
                        startActivity(intent);
                    }
                }else{
                    String str = "response code: " + response.code();
                    Toast.makeText(MainActivity.this,str,Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("Not Response", t.getLocalizedMessage());
            }
        });
    }
    public void getDatas(String id){
        Call<User>call=userService.getRespos(id);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()&&response.body()!=null){
                    //Toast.makeText(MainActivity.this,response.code(),Toast.LENGTH_SHORT).show();
                    //if(response)
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("Not Response", t.getLocalizedMessage());
            }
        });
    }
}
