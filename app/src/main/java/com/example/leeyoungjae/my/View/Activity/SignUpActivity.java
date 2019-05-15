package com.example.leeyoungjae.my.View.Activity;

import android.content.Intent;
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

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignUpActivity extends AppCompatActivity {
    private EditText t1;
    private EditText t2;
    private EditText t3;
    private Button b0;//validate
    private Button b1;//signup
    private Button b2;//cancel

    private Retrofit retrofit;
    private UserService userService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        t1=findViewById(R.id.userid);
        t2=findViewById(R.id.userpwd1);
        t3=findViewById(R.id.userpwd2);
        b0=findViewById(R.id.checkvalidate);
        b0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String u=t1.getText().toString();
                if(u.length()==0||u==null){
                    Toast.makeText(SignUpActivity.this,"ID를 입력해주세요",Toast.LENGTH_SHORT).show();
                }else {
                    retrofit = new Retrofit.Builder()
                            .baseUrl("http://10.0.2.2/develop/Travel/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    userService = retrofit.create(UserService.class);
                    checkValidate(u);
                }
            }
        });
        b1=findViewById(R.id.signup);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String u_id=t1.getText().toString();
                String pw1=t2.getText().toString();
                String pw2=t3.getText().toString();
                if(u_id.length()==0||pw1.length()==0||pw2.length()==0)
                    Toast.makeText(SignUpActivity.this,"ID 혹은 Password 정보를 입력해주십시오.",Toast.LENGTH_SHORT).show();
                else if(!pw1.equals(pw2))
                    Toast.makeText(SignUpActivity.this,"Password 정보가 일치하지 않습니다.",Toast.LENGTH_SHORT).show();
                else{
                    Map<String,String>userinfo=new HashMap<>();
                    userinfo.put("userId",u_id);
                    userinfo.put("password",pw1);
                    Call<User>call=userService.UserSignUp(userinfo);
                    call.enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {
                            if(response.isSuccessful()){
                                if(response.body().isValidate()==true){
                                    String id=response.body().getId();
                                    String password=response.body().getPassword();
                                    Intent intent=new Intent(SignUpActivity.this,AnotherActivity.class);
                                    intent.putExtra("id",id);
                                    intent.putExtra("password",password);
                                    startActivity(intent);
                                }
                            }else{
                                Toast.makeText(SignUpActivity.this,response.code(),Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {

                        }
                    });
                }
            }
        });
        b2=findViewById(R.id.cancel);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignUpActivity.super.onBackPressed();
            }
        });
    }
    public void checkValidate(final String id){
        Call<User> call= userService.CheckUserIdValidate(id);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()&&response.body()!=null){
                    if(response.body().isValidate()==false){
                        Toast.makeText(SignUpActivity.this,"사용 가능 ID 입니다",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(SignUpActivity.this,"이미 사용중인 ID 입니다",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(SignUpActivity.this,response.code(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("Not Response", t.getLocalizedMessage());
            }
        });
    }
}
