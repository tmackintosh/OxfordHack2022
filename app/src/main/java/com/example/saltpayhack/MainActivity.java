package com.example.saltpayhack;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.saltpayhack.cards.Card;
import com.example.saltpayhack.cards.CardManager;
import com.example.saltpayhack.cards.MainFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener {

    // UI Components
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();
        setListener();
        setNavigation();
    }

    private void initUI() {
        mDrawerLayout = findViewById(R.id.fragment_main_dl);
        mNavigationView = findViewById(R.id.activity_main_nv_navView);

        mToolbar = findViewById(R.id.activity_main_tb);
        setSupportActionBar(mToolbar);
    }

    private void setListener() {
        mNavigationView.setNavigationItemSelectedListener(this);
    }

    private void setNavigation() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        System.out.println("HERE");
        switch (item.getItemId()) {
            case R.id.menu_drawer_itm_main:
                getSupportFragmentManager().beginTransaction().replace(R.id.activity_main_fl_container, new MainFragment()).commit();
                break;
            case R.id.menu_drawer_itm_likes:
                getSupportFragmentManager().beginTransaction().replace(R.id.activity_main_fl_container, new LikesFragment()).commit();
                break;
            case R.id.menu_drawer_itm_dislikes:
                getSupportFragmentManager().beginTransaction().replace(R.id.activity_main_fl_container, new DislikesFragment()).commit();
                break;
        }
        return true;
    }
}