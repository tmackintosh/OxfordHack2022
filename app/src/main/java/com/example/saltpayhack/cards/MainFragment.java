package com.example.saltpayhack.cards;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.saltpayhack.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainFragment extends Fragment implements
        View.OnClickListener {

    // UI Components
    private FloatingActionButton fab_like, fab_dislike, fab_favourite, fab_back, fab_forward;
    private View mCardView;
    private CardManager mCardManager;

    // Variables
    private Context mContext;

    public MainFragment() {

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initUI(view);
        setListeners();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        mContext = container.getContext();
        mCardManager = CardManager.getInstance();

        return view;
    }

    private void initUI(View view) {
        fab_like = view.findViewById(R.id.fragment_main_fab_like);
        fab_dislike = view.findViewById(R.id.fragment_main_fab_dislike);
        fab_favourite = view.findViewById(R.id.fragment_main_fab_favourite);
        fab_back = view.findViewById(R.id.fragment_main_fab_back);
        fab_forward = view.findViewById(R.id.fragment_main_fab_forward);
        mCardView = view.findViewById(R.id.activity_main_card_view);
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
            case R.id.fragment_main_fab_back:
                Toast.makeText(mContext, "Backward", Toast.LENGTH_SHORT).show();
                mCardManager.swipeLeft();
                break;
            case R.id.fragment_main_fab_like:
                Toast.makeText(mContext, "Like", Toast.LENGTH_SHORT).show();
                mCardManager.addToLikes(mCardManager.getCurrentCard());
                break;
            case R.id.fragment_main_fab_favourite:
                Toast.makeText(mContext, "Favourite", Toast.LENGTH_SHORT).show();
                mCardManager.addToFavourites(mCardManager.getCurrentCard());
                break;
            case R.id.fragment_main_fab_dislike:
                Toast.makeText(mContext, "Dislike", Toast.LENGTH_SHORT).show();
                mCardManager.addToDislikes(mCardManager.getCurrentCard());
                break;
            case R.id.fragment_main_fab_forward:
                Toast.makeText(mContext, "Forward", Toast.LENGTH_SHORT).show();
                mCardManager.swipeRight();
                break;
        }
    }
}
