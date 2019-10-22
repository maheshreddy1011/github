package com.androindian.finance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.androindian.finance.databinding.ActivityAddGroupBinding;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddGroup extends AppCompatActivity {

   ArrayList Names=new ArrayList();
   String mobile,Name;
   ActivityAddGroupBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView
                (AddGroup.this,R.layout.activity_add_group);

        SharedPreferences sp=getSharedPreferences("Login",MODE_PRIVATE);
        mobile=sp.getString("Mobile",null);
        Name=sp.getString("Name",null);

        Names.add(mobile);
        Names.add(Name);

        binding.addmember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Names.add(binding.mobile.getText().toString().trim());
                Names.add(binding.name.getText().toString().trim());
            }
        });

        binding.addgroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONObject jsonObject=new JSONObject();
                try {
                    jsonObject.put("admin_id",mobile);
                    jsonObject.put("members",Names);
                    jsonObject.put("action","create_group");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Retrofit retrofit=new Retrofit.Builder()
                        .baseUrl("http://androindian.com/apps/fm/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                FinanceInterafce interafce=retrofit.create(FinanceInterafce.class);

                JsonObject object=new JsonParser().parse(jsonObject.toString()).getAsJsonObject();

                Call<AddGroupRes> addGroupResCall=interafce.addgroup(object);

                addGroupResCall.enqueue(new Callback<AddGroupRes>() {
                    @Override
                    public void onResponse(Call<AddGroupRes> call, Response<AddGroupRes> response) {

                        if(response.body().getResponse().equalsIgnoreCase("success")){
                            Toast.makeText(AddGroup.this, "Group Created", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(AddGroup.this, "Not Created", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<AddGroupRes> call, Throwable t) {
                        Toast.makeText(AddGroup.this, "Error", Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });




    }
}
