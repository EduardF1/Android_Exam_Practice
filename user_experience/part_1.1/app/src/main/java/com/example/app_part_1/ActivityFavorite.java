package com.example.app_part_1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class ActivityFavorite extends AppCompatActivity {

    @Override
    public void setSupportActionBar(@Nullable Toolbar toolbar) {
        super.setSupportActionBar(toolbar);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_info:
                Intent intent_info = new Intent(this, ActivityInfo.class);
                startActivity(intent_info);
                return true;
            case R.id.action_favorite:
                Toast.makeText(getApplicationContext(), "Already in screen", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_profiles:
                Intent intent_profiles = new Intent(this, ActivityProfiles.class);
                startActivity(intent_profiles);
                return true;
            case R.id.action_my_other:
                Intent intent_my_other = new Intent(this, ActivityOther.class);
                startActivity(intent_my_other);
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