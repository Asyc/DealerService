package me.asyc.dealer.response;

import me.asyc.dealer.deck.Card;

/**
 * Represents the proper response for a successful card delete.
 */
public class CardDeleteResponse {

    private Card card;

    public CardDeleteResponse(Card card) {
        this.card = card;
    }

    public Card getCard() {
        return this.card;
    }

    public CardDeleteResponse setCard(Card card) {
        this.card = card;
        return this;
    }
}
