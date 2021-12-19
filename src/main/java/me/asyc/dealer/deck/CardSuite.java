package me.asyc.dealer.deck;

/**
 * Enumeration specifying all possible card suites.
 */
public enum CardSuite {
    HEART,
    DIAMOND,
    CLOVE,
    SPADE;

    /**
     * Case-insensitive valueOf parse.
     *
     * @param value String to be parsed
     * @return Returns the enum representation of the passed string.
     */
    public static CardSuite parseSuite(String value) {
        try {
            return CardSuite.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
