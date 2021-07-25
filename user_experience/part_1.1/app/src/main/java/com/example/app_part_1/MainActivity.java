package com.example.app_part_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;



public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        floatingActionButton = findViewById(R.id.my_fab);
        floatingActionButton.setOnClickListener(this);


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
                Intent intent_favorite = new Intent(this, ActivityFavorite.class);
                startActivity(intent_favorite);
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

    @Override
    public void onClick(View v) {
        //Toast.makeText(getApplicationContext(), "HELLO FROM TOAST", LENGTH_SHORT).show();
        Snackbar.make(v, "SNACKS",Snackbar.LENGTH_SHORT).show();
    }
}