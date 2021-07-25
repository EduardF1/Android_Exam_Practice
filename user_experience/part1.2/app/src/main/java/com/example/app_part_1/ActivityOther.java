package com.example.app_part_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class ActivityOther extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);

        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_info:
                Intent intent_info = new Intent(this, ActivityInfo.class);
                startActivity(intent_info);
                return true;
            case R.id.action_favorite:
                Intent intent_favorite = new Intent(this, ActivityFavorite.class);
                startActivity(intent_favorite);
                return true;
            case R.id.action_profiles:
                Intent intent_profiles = new Intent(this, ActivityProfiles.class);
                startActivity(intent_profiles);
                return true;
            case R.id.action_my_other:
                Toast.makeText(getApplicationContext(), "Already in screen", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_settings:
                Intent intent_settings = new Intent(this, ActivitySettings.class);
                startActivity(intent_settings);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}