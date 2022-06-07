package com.example.doancoso3;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.doancoso3.inteface.LoginInterface;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class LoginActivity extends AppCompatActivity {
    EditText edUserName,edPassword;
    Button btnLogin,btnCanCel;
    CheckBox chkRememberPass;
    String password,username;
//    String strUser,strPass;
//    ThuThuDAO dao;
    ImageView img1,img2,img3,img4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("ĐĂNG NHẬP");
        edUserName = findViewById(R.id.edUserName);
        edPassword = findViewById(R.id.edPassword);

        btnLogin = findViewById(R.id.btnLogin);
        btnCanCel = findViewById(R.id.btnCancel);

        chkRememberPass = findViewById(R.id.chkRememberPass);

//        dao = new ThuThuDAO(this);
        //doc User,pass,trong SharedPreferences

        SharedPreferences pref = getSharedPreferences("USER_FILE",MODE_PRIVATE);
        edUserName.setText(pref.getString("USERNAME",""));
        edPassword.setText(pref.getString("PASSWORD",""));
        chkRememberPass.setChecked(pref.getBoolean("REMEMBER",false));

        edUserName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edUserName.setText("");
            }
        });
        edPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edPassword.setText("");
            }
        });
        btnCanCel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edUserName.setText("");
                edPassword.setText("");
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkLogin();
            }
        });

    }



    public void checkLogin(){
//        strUser  = edUserName.getText().toString().trim();
//        strPass = edPassword.getText().toString().trim();
        username = edUserName.getText().toString().trim();
        password = edPassword.getText().toString().trim();

        Log.e(TAG,username);
        Log.e(TAG,password);

        if (username.isEmpty()||password.isEmpty()){
            Toast.makeText(getApplicationContext(),"Tên đăng nhập hoặc mật khẩu không được bỏ trống",Toast.LENGTH_SHORT).show();
        }else {
            Log.e(TAG,username);
            Log.e(TAG,password);
//            if(dao.CheckLogin(strUser,strPass)>0 || (strUser.equals("admin") && strPass.equals("123"))){
//                Toast.makeText(getApplicationContext(),"Đăng nhập thành công",Toast.LENGTH_SHORT).show();
//                rememberUser(strUser,strPass,chkRememberPass.isChecked());
//
//                Intent i  = new Intent(getApplicationContext(),MainActivity.class);
//                i.putExtra("user",strUser);
//                startActivity(i);
//                finish();
//            }else {
//                Toast.makeText(getApplicationContext(),"Tên đăng nhập hoặc mật khẩu không đúng",Toast.LENGTH_SHORT).show();
//            }

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(LoginInterface.URL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .build();
            Log.e(TAG,"Hello response");
            LoginInterface api = retrofit.create(LoginInterface.class);
            Log.e(TAG,"Hello response1");
            Call<String> call = api.getUserLogin(username,password);
            Log.e(TAG,"Hello response2");
            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    Log.e(TAG,"Hello response");
//                    Log.i("Responsestring", response.body().toString());
                    if (response.isSuccessful()){
                        if (response.body()!=null){
//                            Toast.makeText(getApplicationContext(),"Đăng nhập thành công",Toast.LENGTH_SHORT).show();
//                            rememberUser(strUser,strPass,chkRememberPass.isChecked());
//                            Log.i(TAG,"Successfull");
//
////                            Intent i  = new Intent(getApplicationContext(),MainActivity.class);
//                            Intent i = new Intent(LoginActivity.this,MainActivity.class);
//                            i.putExtra("user",strUser);
//
//                            startActivity(i);
//                            finish();
                            Log.i("onSuccess", response.body().toString());
                            String jsonresponse = response.body().toString();
                            parseLoginData(jsonresponse);
                        }else{
                            Log.i("onEmptyResponse", "Returned empty response");
                        }

                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {

                }
            });
        }
    }

    private void parseLoginData(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            if (jsonObject.getString("status").equals("true")){
//                saveInfo(response);
//                rememberUser(username,password,chkRememberPass.isChecked());
//                int admin_id = Integer.parseInt(preferenceHelper.getAdmin_ID());
                JSONArray jsonArray = jsonObject.getJSONArray("data");
                for(int i=0;i<jsonArray.length();i++){
                    JSONObject object = jsonArray.getJSONObject(i);


                    String id = object.getString("id");
                    String name = object.getString("name");
                    String Ho = object.getString("Ho");
                    String Ten = object.getString("Ten");
                    String email = object.getString("email");
                    String password1 = object.getString("password");
                    String SDT = object.getString("SDT");
                    try {
                        String gioitinh = object.getString("gioitinh");
                        String diachi = object.getString("diachi");
                        String cmnd = object.getString("cmnd");
                        String ngay_vao_lam = object.getString("ngay_vao_lam");
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                    String user_id = object.getString("user_id");
                    String password_app = object.getString("Password_app");

                    Toast.makeText(LoginActivity.this, "Login Successfully!", Toast.LENGTH_SHORT).show();
//                Log.i(TAG,"hello" + String.valueOf(admin_id));
                    Log.e(TAG,"Hello data");
//                if (admin_id == 1){
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    intent.putExtra("user",username);
                    intent.putExtra("user_id",user_id);
                    rememberUser(username,password,true);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    Log.e(TAG,"hello");
                    this.finish();
                }


//                }else{
//                    Intent intent = new Intent(LoginActivity.this,WelcomeActivity.class);
//                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//                    startActivity(intent);
//                    this.finish();
//                }

            }
        } catch (JSONException jsonException) {
            jsonException.printStackTrace();
        }
    }

    public void rememberUser(String u,String p,boolean status){
        SharedPreferences pref = getSharedPreferences("USER_FILE",MODE_PRIVATE);
        SharedPreferences.Editor editor= pref.edit();
        if (!status){
            //Xoa tinh trang truoc do
            editor.clear();
        }else {
            // luu du lieu
            editor.putString("USERNAME",u);
            editor.putString("PASSWORD",p);
            editor.putBoolean("REMEMBER",status);
        }
        // luu lai toan bo
        editor.commit();
    }
}
