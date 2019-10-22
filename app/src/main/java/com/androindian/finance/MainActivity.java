package com.androindian.finance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.androindian.finance.databinding.ActivityMainBinding;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(
                MainActivity.this,R.layout.activity_main);

        binding.Reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,RegisterUser.class);
                startActivity(intent);
            }
        });

        binding.Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONObject jsonObject=new JSONObject();
                try {

                    jsonObject.put("mobile",binding.mobile.getText().toString().trim());
                    jsonObject.put("pswrd",binding.pass.getText().toString().trim());
                    jsonObject.put("action","login_user");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Retrofit retrofit=new Retrofit.Builder()
                        .baseUrl("http://androindian.com/apps/fm/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                FinanceInterafce interafce=retrofit.create(FinanceInterafce.class);

                JsonObject object=new JsonParser().parse(jsonObject.toString()).getAsJsonObject();

                Call<LoginResponse> loginResponseCall=interafce.LoginUser(object);

                loginResponseCall.enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        if (response.body().getResponse().trim().equals("success")) {


                            SharedPreferences sp=getSharedPreferences("Login",MODE_PRIVATE);
                            SharedPreferences.Editor edit=sp.edit();
                            edit.putString("Mobile",binding.mobile.getText().toString());
                            edit.putString("Name",response.body().getData().getName());
                            edit.commit();
                            Toast.makeText(getApplicationContext(), "" + response.body().getResponse(), Toast.LENGTH_LONG).show();
                            Intent intent=new Intent(MainActivity.this,MenuPage.class);
                            startActivity(intent);
                            finish();



                        } else if (response.body().getResponse().trim().equals("failed")) {

                            Toast.makeText(getApplicationContext(), "Failed" , Toast.LENGTH_LONG).show();


                        } else if (response.body().getResponse().trim().equals("error")) {

                            Toast.makeText(getApplicationContext(), "Error" ,Toast.LENGTH_LONG).show();

                        }


                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {

                    }
                });
            }
        });
    }
}
