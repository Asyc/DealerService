package me.asyc.dealer.services;

import me.asyc.dealer.deck.Card;
import me.asyc.dealer.deck.CardDeck;
import me.asyc.dealer.response.CardDeleteResponse;
import me.asyc.dealer.response.DeckCreateResponse;
import me.asyc.dealer.response.DeckGetResponse;

import javax.ejb.Singleton;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Implementation class for {@link DeckService}
 */
@Singleton
public class DeckServiceImpl implements DeckService {

    private final AtomicLong idCounter = new AtomicLong(0);
    private final Map<Long, CardDeck> cardDecks = Collections.synchronizedMap(new HashMap<>());

    @Override
    public Response createDeck() throws WebApplicationException {
        CardDeck deck = new CardDeck(this.idCounter.getAndIncrement());
        this.cardDecks.put(deck.getId(), deck);
        return Response.status(Response.Status.OK).entity(new DeckCreateResponse(deck)).build();
    }

    @Override
    public Response getDeck(long id) throws WebApplicationException {
        CardDeck deck = this.cardDecks.get(id);
        if (deck == null) return Response.status(Response.Status.NOT_FOUND.getStatusCode()).build();
        return Response.status(Response.Status.OK).entity(new DeckGetResponse(deck)).build();
    }

    @Override
    public Response deleteDeck(long id) throws WebApplicationException {
        CardDeck deck = this.cardDecks.remove(id);
        if (deck == null) return Response.status(Response.Status.NOT_FOUND.getStatusCode()).build();
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @Override
    public Response popCard(long id) throws WebApplicationException {
        CardDeck deck = this.cardDecks.get(id);
        if (deck == null) return Response.status(Response.Status.NOT_FOUND.getStatusCode()).build();
        Card deleted = deck.pop();
        if (deleted == null) {
            return Response.status(Response.Status.GONE).build();
        }

        return Response.status(Response.Status.OK).entity(new CardDeleteResponse(deleted)).build();
    }

    @Override
    public Response returnCard(long id, Card card) throws WebApplicationException {
        CardDeck deck = this.cardDecks.get(id);
        if (deck == null) return Response.status(Response.Status.NOT_FOUND.getStatusCode()).build();

        if (!deck.addCard(card)) {
            return Response.status(Response.Status.CONFLICT).build();
        }

        return Response.status(Response.Status.OK).build();
    }

    @Override
    public Response shuffleDeck(long id) {
        CardDeck deck = this.cardDecks.get(id);
        if (deck == null) return Response.status(Response.Status.NOT_FOUND.getStatusCode()).build();
        deck.shuffle();
        return Response.status(Response.Status.OK).build();
    }
}
