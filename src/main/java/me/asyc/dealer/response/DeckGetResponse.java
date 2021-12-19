package me.asyc.dealer.response;

import me.asyc.dealer.deck.Card;
import me.asyc.dealer.deck.CardDeck;

import java.util.List;

/**
 * Represents the proper response for a successful deck information GET request.
 */
public class DeckGetResponse {

    private long deckId;
    private List<Card> cards;

    public DeckGetResponse(long deckId, List<Card> cards) {
        this.deckId = deckId;
        this.cards = cards;
    }

    public DeckGetResponse(CardDeck deck) {
        this(deck.getId(), deck.getCards());
    }

    public long getDeckId() {
        return this.deckId;
    }

    public DeckGetResponse setDeckId(int deckId) {
        this.deckId = deckId;
        return this;
    }

    public List<Card> getCards() {
        return this.cards;
    }

    public DeckGetResponse setCards(List<Card> cards) {
        this.cards = cards;
        return this;
    }
}
