package com.example.saltpayhack.cards;

import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.saltpayhack.MainActivity;
import com.example.saltpayhack.R;

import java.util.ArrayList;
import java.util.HashMap;

public class CardManager extends AppCompatActivity {

    public ViewGroup cardView;
    public ArrayList<Card> cards, likes, dislikes, favourites;
    public HashMap<Card, Fragment> fragmentsMap;
    public int currentCardIndex;

    private boolean rightCardPresent, leftCardPresent;
    private static CardManager instance;

    public static CardManager getInstance() {
        if (instance == null) {
            instance = new CardManager();
        }
        return instance;
    }

    private CardManager() {
        cards = new ArrayList<>();
        likes = new ArrayList<>();
        dislikes = new ArrayList<>();
        favourites = new ArrayList<>();
        rightCardPresent = true;
        leftCardPresent = false;
        fragmentsMap = new HashMap<>();
    }

    public void swipeRight() {
//        if (!rightCardPresent) return;
//        Card cardToShow = cards.get(currentCardIndex++);
//        if (currentCardIndex == cards.size()-1) rightCardPresent = false;
//        if (!leftCardPresent) leftCardPresent = true;

        //TODO show card

        Card card = Card.getTestCard();
        card.cardInfo.name = "Yay it changed";
//        Fragment fg = fragmentsMap.getOrDefault(card, new CardFragment());
//        CardFragment fg = MainActivity.getInstance().getNewFragment();
//        MainActivity.getInstance().changeFragment(fg);
    }

    public void swipeLeft() {
//        Card cardToShow = cards.get(currentCardIndex++);
//        if (currentCardIndex == 0) leftCardPresent = false;
//        if (!rightCardPresent) rightCardPresent = true;
//        //TODO show card

        Card card = Card.getTestCard();
        card.cardInfo.name = "Oh we going back now";
//        Fragment fg = fragmentsMap.getOrDefault(card, new CardFragment());
//        CardFragment fg = MainActivity.getInstance().getNewFragment();
//        MainActivity.getInstance().changeFragment(fg);
    }

    public void addToLikes(Card card) {
        if (likes.contains(card)) {
            likes.remove(card);
            return;
        }
        likes.add(card);
    }

    public void addToDislikes(Card card) {
        if (dislikes.contains(card)) {
            dislikes.remove(card);
            return;
        }
        dislikes.add(card);
    }

    public void addToFavourites(Card card) {
        if (favourites.contains(card)) {
            favourites.remove(card);
            return;
        }
        favourites.add(card);
    }

    public Card getCurrentCard() {
        return cards.get(currentCardIndex);
    }

    public ViewGroup getCardView() {
        View v = findViewById(R.id.activity_main_card_view);
        return (ViewGroup) v;

    }
}
