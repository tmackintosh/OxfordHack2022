package com.example.saltpayhack.cards;

import java.util.ArrayList;

public class Card {

    public class CardInfo {
        public int rating;
        public String name;
        public int critOne, critTwo, critThree;
        public ArrayList<String> rearText;
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
