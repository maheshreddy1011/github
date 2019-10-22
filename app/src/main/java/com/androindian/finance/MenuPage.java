package com.androindian.finance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.androindian.finance.databinding.ActivityMenuPageBinding;

public class MenuPage extends AppCompatActivity {

    ActivityMenuPageBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView
                (MenuPage.this,R.layout.activity_menu_page);

        binding.Mandotory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MenuPage.this,Mandory.class);
                startActivity(intent);
            }
        });

        binding.creategroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MenuPage.this,Groupdata.class);
                startActivity(intent);
            }
        });

    }
}
