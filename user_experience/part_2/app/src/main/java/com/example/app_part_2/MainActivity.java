package com.example.app_part_2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

//  reference: https://guides.codepath.com/android/fragment-navigation-drawer
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private NavigationView navigationView;

    // toggle button
    private ActionBarDrawerToggle drawerToggle;

    //  declare fragments
    private SixthFragment sixthFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //  Setup the toolbar we use to replace the ActionBar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //  Find the drawer layout
        drawerLayout = findViewById(R.id.drawer_layout);
        drawerToggle = setupDrawerToggle();

        //  Setup toggle to display hamburger icon with animation
        drawerToggle.setDrawerIndicatorEnabled(true);
        drawerToggle.syncState();

        //  Tie the DrawerLayout to the ActionBarToggle
        drawerLayout.addDrawerListener(drawerToggle);

        //  Find and setup the navigation view
        navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        //  Define fragments
        sixthFragment = new SixthFragment();
    }

    private ActionBarDrawerToggle setupDrawerToggle() {
        return new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open,R.string.drawer_close);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        Class fragmentClass;
        switch (item.getItemId()){
            case R.id.action_favorite:
                setFragment(sixthFragment);
                break;
            case R.id.action_settings:

            default:
                return true;
        }

        //  Highlight the selected item has been done on the NavigationView
        item.setChecked(true);
        //  Set toolbar title
        setTitle(item.getTitle());

        //  Make the action bar home/up action open or close the drawer
       if(drawerToggle.onOptionsItemSelected(item)) {
           return true;
       }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        Class fragmentClass;
        switch (item.getItemId()){
            case R.id.nav_first_fragment:
                fragmentClass = FirstFragment.class;
                break;
            case R.id.nav_second_fragment:
                fragmentClass = SecondFragment.class;
                break;
            case R.id.nav_third_fragment:
                fragmentClass = ThirdFragment.class;
                break;
            default:
                return true;
        }

        try {
            fragment = (Fragment)fragmentClass.newInstance();
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        //  Insert the fragment by replacing an existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frame_layout_main_content, fragment).commit();

        //  Highlight the selected item has been done on the NavigationView
        item.setChecked(true);
        //  Set toolbar title
        setTitle(item.getTitle());
        //  Close the navigation drawer
        drawerLayout.closeDrawers();

        return true;
    }


    /*
        "onPostCreate" is called when the activity is in Running State.
        Note 1 : The Bundle - savedInstanceState is the key-value mapping object of the Activity
        (should always be the same)

        Note 2: There are 2 signatures of the method, only this version is relevant.
     */
    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    //  Handle configuration changes
    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        //  Pass any configuration change to the drawer toggles
        drawerToggle.onConfigurationChanged(newConfig);
    }

    //  method to set the fragment

    private void setFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout_main_content, fragment);
        fragmentTransaction.commit();
    }
}