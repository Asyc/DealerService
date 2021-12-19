package me.asyc.dealer.deck;

import me.asyc.dealer.resource.DeckResource;

/**
 * Enumeration specifying all possible card ranks.
 */
public enum CardRank {
    ACE,
    TWO,
    THREE,
    FOUR,
    FIVE,
    SIX,
    SEVEN,
    EIGHT,
    NINE,
    TEN,
    JACK,
    QUEEN,
    KING;

    /**
     * Case-insensitive valueOf parse.
     *
     * @param value String to be parsed
     * @return Returns the enum representation of the passed string.
     */
    public static CardRank parseRank(String value) {
        try {
            return CardRank.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
