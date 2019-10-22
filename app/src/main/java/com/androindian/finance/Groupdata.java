package com.androindian.finance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.androindian.finance.databinding.ActivityGroupdataBinding;

public class Groupdata extends AppCompatActivity {

    ActivityGroupdataBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(
                Groupdata.this,R.layout.activity_groupdata);

        binding.addgroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Groupdata.this,AddGroup.class);
                startActivity(intent);
            }
        });
    }
}
