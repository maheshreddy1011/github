package com.androindian.finance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.androindian.finance.databinding.ActivityMandoryBinding;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Mandory extends AppCompatActivity {

    ActivityMandoryBinding binding;
    String mobile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(
                Mandory.this,R.layout.activity_mandory);

        binding.addmandorty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Mandory.this,AddMandory.class);
                startActivity(intent);
            }
        });


        SharedPreferences sp=getSharedPreferences("Login",MODE_PRIVATE);
        mobile=sp.getString("Mobile",null);

        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("mobile",mobile);
            jsonObject.put("stype","S");
            jsonObject.put("action","get_savings");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("http://androindian.com/apps/fm/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        FinanceInterafce interafce=retrofit.create(FinanceInterafce.class);

        JsonObject object=new JsonParser().parse(jsonObject.toString()).getAsJsonObject();

        Call<ManlistRes> manlistResCall=interafce.manlist(object);

        manlistResCall.enqueue(new Callback<ManlistRes>() {
            @Override
            public void onResponse(Call<ManlistRes> call, Response<ManlistRes> response) {

                if(response.body().getResponse().equalsIgnoreCase("success")){
                    LinearLayoutManager linearLayoutManager=new LinearLayoutManager(Mandory.this,
                            LinearLayoutManager.VERTICAL,false);
                    binding.manlist.setLayoutManager(linearLayoutManager);
                    binding.manlist.setAdapter(new MadororyADP(Mandory.this,response.body().getData()));
                }else {
                    Toast.makeText(Mandory.this, "NO Data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ManlistRes> call, Throwable t) {

            }
        });

    }
}
