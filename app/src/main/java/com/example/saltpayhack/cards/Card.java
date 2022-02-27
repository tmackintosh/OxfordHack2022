package com.example.saltpayhack.cards;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.appcompat.app.AppCompatActivity;

import com.example.saltpayhack.MainActivity;
import com.example.saltpayhack.R;

import java.util.ArrayList;
import java.util.Arrays;

public class Card extends AppCompatActivity {

    private static ViewGroup mCardViewGroup;

    public static class CardInfo {
        public double rating;
        public String name;
        public int critOne, critTwo, critThree;
        public ArrayList<String> rearText;


        public CardInfo() {
            this.rearText = new ArrayList<>();
        }
    }

    public static Card getTestCard() {
        CardInfo info = new CardInfo();
        info.name = "Mansat";
        info.rating = 0.0;
        info.critOne = 1;
        info.critTwo = 2;
        info.critThree = 3;
        info.rearText.add("Test");
        return new Card(info);
    }

    public CardInfo cardInfo;

    public Card(CardInfo cardInfo) {
        this.cardInfo = cardInfo;
    }

    public void showOnScreen() {

    }

    public void flipCard() {

    }



}
