package me.asyc.dealer.resource;

import me.asyc.dealer.deck.Card;
import me.asyc.dealer.deck.CardRank;
import me.asyc.dealer.deck.CardSuite;
import me.asyc.dealer.services.DeckService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

/**
 * Class defining the various resource paths in the application and forwarding calls to the {@link DeckService}
 */
@Path("/decks")
@Provider
public class DeckResource {

    /**
     * Cached bad response error.
     */
    private static final Response BAD_RESPONSE = Response.status(Response.Status.BAD_REQUEST).build();

    @Inject
    DeckService deckService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response onDeckPost() {
        return this.deckService.createDeck();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response onDeckGet(@PathParam("id") Long id) {
        if (id == null) return DeckResource.BAD_RESPONSE;
        return this.deckService.getDeck(id);
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response onDeckDelete(@PathParam("id") Long id) {
        if (id == null) return DeckResource.BAD_RESPONSE;
        return this.deckService.deleteDeck(id);
    }

    @DELETE
    @Path("{id}/cards")
    @Produces(MediaType.APPLICATION_JSON)
    public Response onCardDelete(@PathParam("id") Long id) {
        if (id == null) return DeckResource.BAD_RESPONSE;
        return this.deckService.popCard(id);
    }

    @PATCH
    @Path("{id}/cards")
    @Produces(MediaType.APPLICATION_JSON)
    public Response onCardPatch(@PathParam("id") Long id, @QueryParam("action") String action) {
        return (action == null || id == null || DeckPatchAction.parseAction(action) != DeckPatchAction.SHUFFLE) ?
                DeckResource.BAD_RESPONSE : this.deckService.shuffleDeck(id);
    }

    @POST
    @Path("{id}/cards")
    @Produces(MediaType.APPLICATION_JSON)
    public Response onCardPost(@PathParam("id") Long id, @QueryParam("suite") String cardSuite, @QueryParam("rank") String cardRank) {
        if (id == null || cardSuite == null || cardRank == null) return DeckResource.BAD_RESPONSE;

        CardSuite suite = CardSuite.parseSuite(cardSuite);
        CardRank rank = CardRank.parseRank(cardRank);
        if (suite == null || rank == null) return DeckResource.BAD_RESPONSE;

        return this.deckService.returnCard(id, new Card(suite, rank));
    }

    /**
     * Enumeration used to represent actions for the card {@link PATCH} action.
     * Currently this serves no use other than to become a more flexible function later on.
     */
    public enum DeckPatchAction {
        SHUFFLE;

        public static DeckPatchAction parseAction(String value) {
            try {
                return DeckPatchAction.valueOf(value.toUpperCase());
            } catch (IllegalArgumentException e) {
                return null;
            }
        }
    }
}
