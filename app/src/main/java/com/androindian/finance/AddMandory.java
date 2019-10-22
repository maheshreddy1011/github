package com.androindian.finance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import com.androindian.finance.databinding.ActivityAddMandoryBinding;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddMandory extends AppCompatActivity {

    ActivityAddMandoryBinding binding;
    String mobile;
    private int mYear, mMonth, mDay,tYear, tMonth, tDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView
                (AddMandory.this,R.layout.activity_add_mandory);

        SharedPreferences sp=getSharedPreferences("Login",MODE_PRIVATE);
        mobile=sp.getString("Mobile",null);

        binding.fromdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar=Calendar.getInstance();
                mYear=calendar.get(Calendar.YEAR);
                mMonth=calendar.get(Calendar.MONTH);
                mDay=calendar.get(Calendar.DATE);

                DatePickerDialog datePickerDialog=new DatePickerDialog(AddMandory.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                binding.to.setText(year+"-"+month+"-"+dayOfMonth);
                            }
                        },
                        mYear, mMonth, mDay);
                datePickerDialog.show();

            }
        });

        binding.todate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar=Calendar.getInstance();
                tYear=calendar.get(Calendar.YEAR);
                tMonth=calendar.get(Calendar.MONTH);
                tDay=calendar.get(Calendar.DATE);

                DatePickerDialog datePickerDialog=new DatePickerDialog(AddMandory.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                binding.from.setText(year+"-"+month+"-"+dayOfMonth);
                            }
                        },
                        tYear, tMonth, tDay);
                datePickerDialog.show();

            }
        });

        binding.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                JSONObject jsonObject=new JSONObject();
                try {
                    jsonObject.put("mobile",mobile);
                    jsonObject.put("stype","S");
                    jsonObject.put("reason",binding.resson.getText().toString().trim());
                    jsonObject.put("amount",binding.amount.getText().toString().trim());
                    jsonObject.put("start_date",binding.to.getText().toString().trim());
                    jsonObject.put("end_date",binding.from.getText().toString().trim());
                    jsonObject.put("action","put_saving");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Retrofit retrofit=new Retrofit.Builder()
                        .baseUrl("http://androindian.com/apps/fm/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                FinanceInterafce interafce=retrofit.create(FinanceInterafce.class);

                JsonObject object=new JsonParser().parse(jsonObject.toString()).getAsJsonObject();

                Call<AddmanResponse> addmanResponseCall=interafce.addman(object);

                addmanResponseCall.enqueue(new Callback<AddmanResponse>() {
                    @Override
                    public void onResponse(Call<AddmanResponse> call, Response<AddmanResponse> response) {

                        if(response.body().getResponse().equalsIgnoreCase("success")){
                            Intent intent=new Intent(AddMandory.this,Mandory.class);
                            startActivity(intent);
                            finish();

                        }else {
                            Toast.makeText(AddMandory.this, "Not Saved", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<AddmanResponse> call, Throwable t) {

                    }
                });

            }
        });
    }
}
