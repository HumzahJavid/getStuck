package getstuck;

/**
 *
 * @author humzah
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Grid {
    private List<List<Card>> cards;
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
        String colour = selectedCard.getColour();
        String orientation = "";
        String lowestValidMove = "";
        List<Card> cardsToRemove = new ArrayList();

        boolean horizontal = false;
        boolean vertical = false;
        boolean diagonal = false;

        boolean validHorizontalMove = false;
        boolean validVerticalMove = false;
        boolean validDiagonalMove = false;

        List<List<Card>> hCards;
        List<List<Card>> vCards;
        List<List<Card>> dCards;

        List<Card> fullPath = new ArrayList();

        hCards = this.getHorizontalCards("", this.getGap());
        vCards = this.getVerticalCards("", this.getGap());
        dCards = this.getDiagonalCards("", this.getGap());

        for (List<Card> innerList : hCards) {
            if (innerList.contains(selectedCard)) {
                horizontal = true;
                fullPath = innerList;
            }
            for (Card card : innerList) {
                if (!card.getColour().equalsIgnoreCase(colour)) {
                    if (card.isFaceDown) {
                    } else {
                        cardsToRemove.add(card);
                    }
                }
            }
            innerList.removeAll(cardsToRemove);
        }

        for (List<Card> innerList : vCards) {
            if (innerList.contains(selectedCard)) {
                vertical = true;
                fullPath = innerList;
            }
            for (Card card : innerList) {
                if (!card.getColour().equalsIgnoreCase(colour)) {
                    if (card.isFaceDown) {
                    } else {
                        cardsToRemove.add(card);
                    }
                }
            }
            innerList.removeAll(cardsToRemove);
        }

        for (List<Card> innerList : dCards) {
            if (innerList.contains(selectedCard)) {
                diagonal = true;
                fullPath = innerList;
            }
            for (Card card : innerList) {
                if (!card.getColour().equalsIgnoreCase(colour)) {
                    if (card.isFaceDown) {
                    } else {
                        cardsToRemove.add(card);
                    }
                }
            }
            innerList.removeAll(cardsToRemove);
        }

        for (List<Card> innerList : hCards) {
            if (this.isValidMove(innerList)) {
                validHorizontalMove = true;
            }
        }

        for (List<Card> innerList : vCards) {
            if (this.isValidMove(innerList)) {
                validVerticalMove = true;
            }
        }

        for (List<Card> innerList : dCards) {
            if (this.isValidMove(innerList)) {
                validDiagonalMove = true;
            }
        }

        if (validHorizontalMove || validVerticalMove) {
            lowestValidMove = "orthagonal";
        } else if (validDiagonalMove) {
            lowestValidMove = "diagonal";
        } else {
            lowestValidMove = "invalid";
            System.out.println("This card cannot be moved, it is not orthagonally or diagonally inline with gap");
        }

        if (diagonal) {
            orientation = "diagonal";
        } else if (vertical || horizontal) {
            orientation = "orthagonal";
        } else {
            //if the card is not aligned with the gap, orthagonally or diagonally 
            //then player cannot select this card/dont move card
            System.out.println("The selected card is not orthagonally or diagonally in line with the gap");
            orientation = "invalid";
        }

        System.out.println("---------------------------------------------------------");
        if (orientation.equals(lowestValidMove)) {
            this.getCardsInPath(selectedCard, this.getGap(), fullPath);
        } else {
            System.out.println("The move cannot be made as your card " + selectedCard + " is "
                    + orientation + "ly in line with the gap but it is possible to make a " + lowestValidMove + " move");
        }
    }

    public List<Card> getCardsInPath(Card selectedCard, Card gap, List<Card> fullPath) {
        List<Card> innerPath = new ArrayList();
        innerPath = fullPath.subList(0, (fullPath.indexOf(selectedCard) + 1));
        if (this.isValidMove(fullPath)) {
            this.moveCard(selectedCard, gap);
        } else {
            System.out.println("This is not a valid move please select another card");
        }
        return innerPath;
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

        //for cards on right
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

    public boolean isValidMove(List<Card> cards) {
        if (!cards.isEmpty()) {
            //if this list is not empty 

            if (cards.get(0).isFaceDown == false) {
                return true;
            } else {
                for (Card currentCard : cards) {
                    if ((currentCard instanceof CourtCard) && (currentCard.isFaceDown == false)) {
                        return true;
                    }
                }
                return false;
            }
        } else {
            return false;
        }
    }

    public void moveCard(Card selectedCard) {
        if (selectedCard.getIsFaceDown() == true) {
            System.out.println("Cannot move a face down card, please choose a new Card");
        } else {
            this.checkOrientation(selectedCard);
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
        Collections.shuffle(deck);
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
