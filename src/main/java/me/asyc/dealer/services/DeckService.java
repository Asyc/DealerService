package me.asyc.dealer.services;

import me.asyc.dealer.deck.Card;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

/**
 * A specification for the dealer's deck functionality
 */
public interface DeckService {

    /**
     * Creates a new deck in the storage device used
     * to cache live-decks.
     *
     * @return Returns a response code specifying whether success or failure has occurred
     */
    Response createDeck();

    /**
     * @param id The target deck's unique identifier returned from {@link DeckService#createDeck()}
     * @return Returns a response code specifying whether success or failure has occurred. On success, returns information about the target deck such as its card order.
     */
    Response getDeck(long id);

    /**
     *
     * @param id The target deck's unique identifier returned from {@link DeckService#createDeck()}
     * @return Returns a response code specifying whether success or failure has occurred. On success, returns the card at the top of the stack, and removes it.
     */
    Response popCard(long id);

    /**
     * Adds a removed card to the bottom of the card stack of the target deck.
     *
     * @param id The target deck's unique identifier returned from {@link DeckService#createDeck()}
     * @return Returns a response code specifying whether success or failure has occurred.
     */
    Response returnCard(long id, Card card);

    /**
     * Shuffles the target deck using the Fisher-Yates randomization algorithm.
     *
     * @param id The target deck's unique identifier returned from {@link DeckService#createDeck()}
     * @return Returns a response code specifying whether success or failure has occurred.
     */
    Response shuffleDeck(long id);

    /**
     * Deletes a deck from the storage unit storing live-decks. The id of the deck will no longer be valid
     * for future operations.
     *
     * @param id The target deck's unique identifier returned from {@link DeckService#createDeck()}
     * @return Returns a response code specifying whether success or failure has occurred.
     */
    Response deleteDeck(long id);
}
