package getstuck;

/**
 *
 * @author humzah
 */
import java.util.ArrayList;
import java.util.List;

public class Grid {

    List<Card> cards;
    Card gap;
    int size;

    public Grid() {
        this.cards = null;
        this.gap = null;
        this.size = 0;
    }

    public Grid(int size) {
        this.size = 7;
    }

    public List<Card> getCards() {
        return this.cards;
    }

    public Card getGap() {
        return this.gap;
    }

    public int getSize() {
        return this.size;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public void setGap(Card gap) {
        this.gap = gap;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void checkOrientation(Card selectedCard) {
    }

    public List<Card> getCardsInPath(Card selectedCard, Card gap) {
        return null;
    }

    public List<Card> getDiagonalCards(String colour, Card card) {
        return null;
    }

    public List<Card> getHorizontalCards(String colour, Card card) {
        return null;
    }

    public List<Card> getVerticalCards(String colour, Card card) {
        return null;
    }

    public void moveCard(Card selectedCard) {
    }

    private void moveCard(Card selectedCard, Card gap) {
    }

    public List<Card> removeCard(List<Card> cards) {
        List<Card> deck = this.getCards();

        for (int i = 0; i < deck.size(); i++) {
            if (deck.get(i).getValue() == Card.Value.TEN) {
                cards.add(deck.get(i));
            }
        }

        deck.removeAll(cards);
        return deck;
    }

    public void setupGrid() {
        this.createDeck();
        this.removeCard(new ArrayList());
        this.addGap(this.getCards());
    }

    public void createDeck() {
        ArrayList deck = new ArrayList();
        for (int i = 0; i < 13; i++) {
            if (i < 10) {
                Card.Value value = Card.Value.values()[i];
                for (int j = 0; j < 4; j++) {
                    Card card = new NumeralCard(value, Card.Suit.values()[j]);
                    deck.add(card);
                }
            } else {
                Card.Value value = Card.Value.values()[i];
                for (int j = 0; j < 4; j++) {
                    Card card = new CourtCard(value, Card.Suit.values()[j]);
                    deck.add(card);
                }
            }
        }
        this.cards = deck;
    }

    public void addGap(List<Card> cards) {
        cards.add((cards.size() / 2), new Card());
        this.cards = cards;
    }

    @Override
    public String toString() {
        String grid = "";
        for (int i = 0; i < this.cards.size(); i++) {
            if (i % this.getSize() == 0) {
                grid += "\n";
            }
            grid += this.cards.get(i).toString(false);
        }
        return grid;
    }
}
