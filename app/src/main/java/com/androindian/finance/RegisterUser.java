package com.androindian.finance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.androindian.finance.databinding.ActivityRegisterUserBinding;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterUser extends AppCompatActivity {

    ActivityRegisterUserBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(
                RegisterUser.this,R.layout.activity_register_user);

        binding.reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                JSONObject jsonObject=new JSONObject();
                try {
                    jsonObject.put("name",binding.name.getText().toString().trim());
                    jsonObject.put("mobile",binding.mobile.getText().toString().trim());
                    jsonObject.put("email",binding.email.getText().toString().trim());
                    jsonObject.put("pswrd",binding.pass.getText().toString().trim());
                    jsonObject.put("action","register_user");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Retrofit retrofit=new Retrofit.Builder()
                        .baseUrl("http://androindian.com/apps/fm/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                FinanceInterafce interafce=retrofit.create(FinanceInterafce.class);

                JsonObject object=new JsonParser().parse(jsonObject.toString()).getAsJsonObject();

                Call<RegResponse> regResponseCall=interafce.CreateUser(object);

                regResponseCall.enqueue(new Callback<RegResponse>() {
                    @Override
                    public void onResponse(Call<RegResponse> call, Response<RegResponse> response) {
                        Log.i("Res",response.body().getUser());


                        if (response.body().getResponse().trim().equals("success")) {


                            Toast.makeText(getApplicationContext(), "" + response.body().getUser(), Toast.LENGTH_LONG).show();
                            Intent intent=new Intent(RegisterUser.this,MainActivity.class);
                            startActivity(intent);
                            finish();



                        } else if (response.body().getResponse().trim().equals("failed")) {

                            Toast.makeText(getApplicationContext(), "" + response.body().getUser(), Toast.LENGTH_LONG).show();


                        } else if (response.body().getResponse().trim().equals("error")) {

                            //Toast.makeText(getApplicationContext(), "" + status2, Toast.LENGTH_LONG).show();

                        }

                    }

                    @Override
                    public void onFailure(Call<RegResponse> call, Throwable t) {
                        Toast.makeText(RegisterUser.this, ""+t.toString(), Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });
    }
}
