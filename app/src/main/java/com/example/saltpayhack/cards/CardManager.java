package com.example.saltpayhack.cards;

import java.util.ArrayList;

public class CardManager {

    public ArrayList<Card> cards, likes, dislikes, favourites;
    public int currentCardIndex;

    private boolean rightCardPresent, leftCardPresent;

    public CardManager() {
        cards = new ArrayList<>();
        likes = new ArrayList<>();
        dislikes = new ArrayList<>();
        favourites = new ArrayList<>();
        rightCardPresent = true;
        leftCardPresent = false;
    }

    public void swipeRight() {
        if (!rightCardPresent) return;
        Card cardToShow = cards.get(currentCardIndex++);
        if (currentCardIndex == cards.size()-1) rightCardPresent = false;
        if (!leftCardPresent) leftCardPresent = true;

        //TODO show card
    }

    public void swipeLeft() {
        Card cardToShow = cards.get(currentCardIndex++);
        if (currentCardIndex == 0) leftCardPresent = false;
        if (!rightCardPresent) rightCardPresent = true;
        //TODO show card
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

}
