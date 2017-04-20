package getstuck;
//remove line 255 grid.stuckGrid(), made for testing purposes. 
//Implement line 85 grid.checkOrientaion()  and line 88 grid.getCardsInPath
//should be v.similar to game.checkForStuckPlayer()

/**
 *
 * @author humzah
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Grid {

    private List<List<Card>> cards;
    //private List<Card> cards;
    private Card gap;
    private int size;

    public Grid() {
        this.cards = null;
        this.gap = null;
        this.size = 0;
    }

    public Grid(int size) {
        this.size = 7;
    }

    public List<List<Card>> getCards() {
        return this.cards;
    }

    public Card getGap() {
        return this.gap;
    }

    public int getSize() {
        return this.size;
    }

    public void setCards(List<List<Card>> cards) {
        this.cards = cards;
    }

    private void setGap(Card gap) {
        this.gap = gap;
    }

    public void setSize(int size) {
        this.size = size;
    }

    private List<Card> createDeck() {
        ArrayList deck = new ArrayList();
        for (int i = 0; i < 13; i++) {
            Card.Value value = Card.Value.values()[i];
            for (int j = 0; j < 4; j++) {
                Card card = null;
                if (i < 10) {
                    card = new NumeralCard(value, Card.Suit.values()[j]);
                } else {
                    card = new CourtCard(value, Card.Suit.values()[j]);
                }
                deck.add(card);
            }
        }
        return deck;
    }

    private List<Card> createColours(List<Card> cards) {

        for (Card card : cards) {
            if ((card.getSuit() == card.suit.CLUBS) || (card.getSuit() == card.suit.SPADES)) {
                card.colour = "Black";
            } else {
                card.colour = "Red";
            }
        }

        return cards;
    }

    public void checkOrientation(Card selectedCard) {
    }

    public List<Card> getCardsInPath(Card selectedCard, Card gap) {
        return null;
    }

    public List<List<Card>> getDiagonalCards(String colour, Card card) {
        //reverse each inner diagonal list at the end 
        List<List<Card>> dCards = new ArrayList<List<Card>>(4);
        for (int i = 0; i < 4; i++) {
            dCards.add(new ArrayList<Card>());
        }

        int[] locationOfGap = this.getLocationOfCard(card);

        //for cards on top left
        int cardX = locationOfGap[0] - 1;
        int cardY = locationOfGap[1] - 1;

        while (cardX > -1 && cardY > -1) {
            dCards.get(0).add(this.getCards().get(cardY).get(cardX));
            cardX -= 1;
            cardY -= 1;

        }

        //for cards on top right
        cardX = locationOfGap[0] + 1;
        cardY = locationOfGap[1] - 1;

        while (cardX < 7 && cardY > -1) {
            dCards.get(1).add(this.getCards().get(cardY).get(cardX));
            cardX += 1;
            cardY -= 1;
        }

        //for cards on bottom left
        cardX = locationOfGap[0] - 1;
        cardY = locationOfGap[1] + 1;

        while (cardX > -1 && cardY < 7) {
            dCards.get(2).add(this.getCards().get(cardY).get(cardX));
            cardX -= 1;
            cardY += 1;
        }

        //for cards on bottom right
        cardX = locationOfGap[0] + 1;
        cardY = locationOfGap[1] + 1;

        while (cardX < 7 && cardY < 7) {
            dCards.get(3).add(this.getCards().get(cardY).get(cardX));
            cardX += 1;
            cardY += 1;
        }
        return dCards;
    }

    private int[] getLocationOfCard(Card card) {
        int[] location = new int[2];
        //location[0] = x, location[1] = y (but conceptually the position of a card in the grid is y x)

        for (int i = 0; i < this.getSize(); i++) {
            int j = this.getCards().get(i).indexOf(card);
            if (j != -1) {
                location[0] = j;
                location[1] = i;
            }
        }
        return location;
    }

    public List<List<Card>> getHorizontalCards(String colour, Card card) {
        List<List<Card>> hCards = new ArrayList<List<Card>>(2);
        hCards.add(new ArrayList<Card>());
        hCards.add(new ArrayList<Card>());

        int[] locationOfGap = this.getLocationOfCard(card);

        //for cards on left 
        for (int i = 0; i < locationOfGap[0]; i++) {
            hCards.get(0).add(this.getCards().get(locationOfGap[1]).get(i));
        }

        for (int i = 6; i > locationOfGap[0]; i--) {
            hCards.get(1).add(this.getCards().get(locationOfGap[1]).get(i));
        }

        for (List<Card> innerList : hCards) {
            Collections.reverse(innerList);
        }
        return hCards;
    }

    public List<List<Card>> getVerticalCards(String colour, Card card) {
        List<List<Card>> vCards = new ArrayList<List<Card>>(2);
        vCards.add(new ArrayList<Card>());
        vCards.add(new ArrayList<Card>());

        int[] locationOfGap = this.getLocationOfCard(card);

        //for cards on top
        for (int i = 0; i < locationOfGap[1]; i++) {
            vCards.get(0).add(this.getCards().get(i).get(locationOfGap[0]));
        }

        //for cards on bottom
        for (int i = 6; i > locationOfGap[1]; i--) {
            vCards.get(1).add(this.getCards().get(i).get(locationOfGap[0]));
        }

        for (List<Card> innerList : vCards) {
            Collections.reverse(innerList);
        }
        return vCards;
    }

    private void fillGrid(List<Card> cards) {
        List<List<Card>> grid = new ArrayList<List<Card>>(this.getSize());
        for (int i = 0; i < 7; i++) {
            grid.add(new ArrayList<Card>(this.getSize()));
        }

        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                grid.get(i).add(cards.get((i * 7) + j));
            }
        }

        this.setCards(grid);
        this.setGap(grid.get(3).get(3));
    }

    public void moveCard(Card selectedCard) {
        if (selectedCard.getIsFaceDown() == true) {
            System.out.println("Cannot move a face down card, please choose a new Card");
        } else {
            moveCard(selectedCard, this.getGap());
        }
    }

    private void moveCard(Card selectedCard, Card gap) {
        int[] lCard = this.getLocationOfCard(selectedCard);
        int[] lGap = this.getLocationOfCard(gap);

        this.getCards().get(lCard[1]).set(lCard[0], gap);
        this.getCards().get(lGap[1]).set(lGap[0], selectedCard);
        selectedCard.setIsFaceDown(true);
    }

    public List<Card> removeCard(List<Card> cardsToRemove, List<Card> deck) {
        for (int i = 0; i < deck.size(); i++) {
            if (deck.get(i).getValue() == Card.Value.TEN) {
                cardsToRemove.add(deck.get(i));
            }
        }
        deck.removeAll(cardsToRemove);
        return deck;
    }

    public void setupGrid() {
        List<Card> deck = this.createDeck();
        deck = this.createColours(deck);
        deck = this.removeCard(new ArrayList(), deck);
        deck.add((deck.size() / 2), new Card());
        //add gap card in center of deck
        this.fillGrid(deck);
    }

    public void stuckGrid() {
        List<List<Card>> dCards = this.getDiagonalCards("Black", this.getGap());
        List<List<Card>> hCards = this.getHorizontalCards("Black", this.getGap());
        List<List<Card>> vCards = this.getVerticalCards("Black", this.getGap());

        for (List<Card> list : dCards) {
            for (Card card : list) {
                card.setIsFaceDown(true);
            }
        }
        for (List<Card> list : hCards) {
            for (Card card : list) {
                card.setIsFaceDown(true);
            }
        }
        for (List<Card> list : vCards) {
            for (Card card : list) {
                card.setIsFaceDown(true);
            }
        }
    }

    @Override
    public String toString() {
        String grid = "";
        for (int i = 0; i < this.cards.size(); i++) {
            grid += "\n";
            grid += this.cards.get(i).toString();
        }
        return grid;
    }
}
