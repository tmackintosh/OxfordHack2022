package com.example.saltpayhack;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FloatingActionButton fab_like, fab_dislike, fab_favourite, fab_back, fab_forward;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();
        setListeners();
    }

    private void initUI() {
        fab_like = findViewById(R.id.activity_main_fab_like);
        fab_dislike = findViewById(R.id.activity_main_fab_dislike);
        fab_favourite = findViewById(R.id.activity_main_fab_favourite);
        fab_back = findViewById(R.id.activity_main_fab_back);
        fab_forward = findViewById(R.id.activity_main_fab_forward);

        mToolbar = findViewById(R.id.activity_main_tb);
    }

    private void setListeners() {
        fab_like.setOnClickListener(this);
        fab_dislike.setOnClickListener(this);
        fab_favourite.setOnClickListener(this);
        fab_back.setOnClickListener(this);
        fab_forward.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_main_fab_back:
                Toast.makeText(this, "Backward", Toast.LENGTH_SHORT);
                break;
            case R.id.activity_main_fab_like:
                Toast.makeText(this, "Like", Toast.LENGTH_SHORT);
                break;
            case R.id.activity_main_fab_favourite:
                Toast.makeText(this, "Favourite", Toast.LENGTH_SHORT);
                break;
            case R.id.activity_main_fab_dislike:
                Toast.makeText(this, "Dislike", Toast.LENGTH_SHORT);
                break;
            case R.id.activity_main_fab_forward:
                Toast.makeText(this, "Forward", Toast.LENGTH_SHORT);
                break;

        }
    }
}