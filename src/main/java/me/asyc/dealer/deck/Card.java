package me.asyc.dealer.deck;

/**
 * Data class specifying a playing card.
 */
public class Card {

    private CardSuite suite;
    private CardRank rank;

    public Card(CardSuite suite, CardRank rank) {
        this.suite = suite;
        this.rank = rank;
    }

    @Override
    public boolean equals(Object rhs) {
        if (this.getClass() != rhs.getClass()) return false;
        Card that = (Card) rhs;
        return this.suite.equals(that.suite) && this.rank == that.rank;
    }

    /**
     * Treats the ordinal(s) of this card's suite and rank as one byte integers,
     * shifting one to append to the other and forming a short.
     *
     * @return Hashcode method returning a unique value for this classes suite and rank combination.
     */
    @Override
    public int hashCode() {
        int major = this.suite.ordinal() << 8;
        int minor = this.rank.ordinal();
        return major | minor;
    }

    public CardSuite getSuite() {
        return suite;
    }

    public Card setSuite(CardSuite suite) {
        this.suite = suite;
        return this;
    }

    public CardRank getRank() {
        return this.rank;
    }

    public Card setRank(CardRank rank) {
        this.rank = rank;
        return this;
    }
}
