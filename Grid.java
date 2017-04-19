package getstuck;

/**
 *
 * @author humzah
 */
import java.util.ArrayList;
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

    public void checkOrientation(Card selectedCard) {
    }

    public List<Card> getCardsInPath(Card selectedCard, Card gap) {
        return null;
    }

    public List<Card> getDiagonalCards(String colour, Card card) {
        return null;
    }

    private int[] getLocationOfCard(Card card) {
        int[] location = new int[2];

        for (int i = 0; i < this.getSize(); i++) {
            int j = this.getCards().get(i).indexOf(card);
            if (j != -1) {
                location[0] = i;
                location[1] = j;
            }
        }
        return location;
    }

    public List<Card> getHorizontalCards(String colour, Card card) {
        return null;
    }

    public List<Card> getVerticalCards(String colour, Card card) {
        return null;
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

        this.getCards().get(lCard[0]).set(lCard[1], gap);
        this.getCards().get(lGap[0]).set(lGap[1], selectedCard);
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
        deck = this.removeCard(new ArrayList(), deck);
        deck.add((deck.size() / 2), new Card());
        //add gap card in center of deck
        this.fillGrid(deck);
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
