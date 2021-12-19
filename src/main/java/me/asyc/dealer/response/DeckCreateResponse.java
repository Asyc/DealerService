package me.asyc.dealer.response;

import me.asyc.dealer.deck.CardDeck;

/**
 * Represents the proper response for a successful creation of a deck.
 */
public class DeckCreateResponse {

    private long id;

    public DeckCreateResponse(long id) {
        this.id = id;
    }

    public DeckCreateResponse(CardDeck deck) {
        this(deck.getId());
    }

    public long getId() {
        return this.id;
    }

    public DeckCreateResponse setId(long id) {
        this.id = id;
        return this;
    }
}
