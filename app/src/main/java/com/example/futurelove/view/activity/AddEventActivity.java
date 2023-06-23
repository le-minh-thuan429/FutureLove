package com.example.futurelove.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;

import com.example.futurelove.R;
import com.example.futurelove.databinding.ActivityAddEventBinding;
import com.example.futurelove.databinding.ActivityMainBinding;

public class AddEventActivity extends AppCompatActivity {
    private ActivityAddEventBinding activityAddEventBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.transparent));
        activityAddEventBinding = ActivityAddEventBinding.inflate(getLayoutInflater());
        setContentView(activityAddEventBinding.getRoot());



    }
}