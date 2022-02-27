package com.example.saltpayhack;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.saltpayhack.cards.Card;
import com.example.saltpayhack.cards.CardFragment;
import com.example.saltpayhack.cards.CardManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {



    private FloatingActionButton fab_like, fab_dislike, fab_favourite, fab_back, fab_forward;
    private View mCardView;
    private TextView title, rating;
    private Toolbar mToolbar;
    private CardManager mCardManager;
    private FragmentManager fm;
    private static MainActivity instance;

    public static MainActivity getInstance() {
        if (instance == null) instance = new MainActivity();
        return instance;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCardManager = CardManager.getInstance();

        initUI();
        setListeners();
    }

    private void initUI() {
        fab_like = findViewById(R.id.activity_main_fab_like);
        fab_dislike = findViewById(R.id.activity_main_fab_dislike);
        fab_favourite = findViewById(R.id.activity_main_fab_favourite);
        fab_back = findViewById(R.id.activity_main_fab_back);
        fab_forward = findViewById(R.id.activity_main_fab_forward);
        mCardView = findViewById(R.id.activity_main_card_view);

        title = findViewById(R.id.company_name);
        rating = findViewById(R.id.textView2);
        title.setText(Card.getTestCard().cardInfo.name);
        rating.setText(String.format("%s", Card.getTestCard().cardInfo.rating));
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
        System.out.println(view.getId());
        Toast.makeText(this, "Here", Toast.LENGTH_SHORT).show();

        switch (view.getId()) {
            case R.id.activity_main_fab_back:
                Toast.makeText(this, "Backward", Toast.LENGTH_SHORT).show();
                mCardManager.swipeLeft();
                break;
            case R.id.activity_main_fab_like:
                Toast.makeText(this, "Like", Toast.LENGTH_SHORT).show();
                mCardManager.addToLikes(mCardManager.getCurrentCard());
                break;
            case R.id.activity_main_fab_favourite:
                Toast.makeText(this, "Favourite", Toast.LENGTH_SHORT).show();
                mCardManager.addToFavourites(mCardManager.getCurrentCard());
                break;
            case R.id.activity_main_fab_dislike:
                Toast.makeText(this, "Dislike", Toast.LENGTH_SHORT).show();
                mCardManager.addToDislikes(mCardManager.getCurrentCard());
                break;
            case R.id.activity_main_fab_forward:
                Toast.makeText(this, "Forward", Toast.LENGTH_SHORT).show();
                mCardManager.swipeRight();
                break;

        }
    }

    public void changeFragment (CardFragment fragment) {
//        fm =  getSupportFragmentManager();
        System.out.println(getSupportFragmentManager());
        System.out.println(getFragmentManager());
        fragment = new CardFragment();
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                .replace(R.id.activity_main_card_view, fragment)
                .commit();
    }

    public CardFragment getNewFragment() {
        return new CardFragment();
    }

}