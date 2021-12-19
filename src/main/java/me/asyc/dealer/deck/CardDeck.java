package me.asyc.dealer.deck;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Container class storing all combinations of {@link CardSuite} and {@link CardRank}
 */
public class CardDeck {

    public static final int DECK_SIZE = CardSuite.values().length * CardRank.values().length;

    /**
     * Cached list of all card permutations ordered.
     */
    private static final List<Card> CARD_PERMUTATIONS = new ArrayList<>(CardDeck.DECK_SIZE);

    static {
        for (CardSuite suite : CardSuite.values()) {
            for (CardRank rank : CardRank.values()) {
                CardDeck.CARD_PERMUTATIONS.add(new Card(suite, rank));
            }
        }
    }

    private final long id;
    private final List<Card> cards;
    private final Set<Card> removedCards;

    public CardDeck(long id) {
        this.id = id;
        this.cards = Collections.synchronizedList(new LinkedList<>(CardDeck.CARD_PERMUTATIONS));
        this.removedCards = new HashSet<>();
    }

    /**
     * Removes a card from the top of the deck.
     * @return Returns the removed card. Returns "null" if there is no more cards to remove.
     */
    public synchronized Card pop() {
        if (this.cards.isEmpty()) return null;
        Card card = this.cards.remove(0);
        this.removedCards.add(card);
        return card;
    }

    /**
     * Adds a card to the bottom of the deck.
     *
     * @param card The card to be added to the bottom of the stack
     * @return Returns true if the card was added, false if this card already exists in the deck.
     */
    public synchronized boolean addCard(Card card) {
        if (!this.removedCards.remove(card)) return false;
        this.cards.add(card);
        return true;
    }

    /**
     * Shuffles the deck using the Fisher-Yates algorithm.
     */
    public synchronized void shuffle() {
        Random random = ThreadLocalRandom.current();
        for (int i = this.cards.size() - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            Collections.swap(this.cards, i, index);
        }
    }

    /**
     * @return Returns this decks unique ID.
     */
    public long getId() {
        return this.id;
    }

    public List<Card> getCards() {
        return cards;
    }
}
