package getstuck;

import java.util.ArrayList;
import java.util.List;

public class Card {

    String colour;
    boolean isFaceDown;
    boolean isGap = ((this instanceof Card) && !((this instanceof CourtCard) || (this instanceof NumeralCard)));

    enum Suit {
        CLUBS, DIAMONDS, HEARTS, SPADES;
    }

    enum Value {
        ACE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10), JACK(11), QUEEN(12), KING(13);

        private int value;

        private Value(int num) {
            this.value = num;
        }

        public int getValue() {
            return value;
        }
    }

    protected Suit suit;
    protected Value value;

    public Card() {
    }

    public Card(Value value, Suit suit) {
        this.isFaceDown = false;
        this.value = value;
        this.suit = suit;
    }

    public Suit getSuit() {
        return suit;
    }

    public Value getValue() {
        return value;
    }

    public void setSuit(Suit suit) {
        this.suit = suit;
    }

    public void setValue(Value value) {
        this.value = value;
    }

    public String toString() {
        if (this.getValue() != null) {
            return "[" + this.getValue() + "(" + this.getValue().value + ") of " + this.getSuit() + "]";
        } else {
            return "[GAPCARD]";
        }
    }

    public String toString(boolean bool) {
        if ((this.getValue() != null) && (this.getSuit() != null)) {
            return "[" + this.getValue() + " " + this.getSuit() + "]";
        } else {
            return "[GAPCARD]";
        }
    }
}//end card class 
