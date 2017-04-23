package getstuck;

import java.util.ArrayList;
import java.util.List;

public class Card {

    protected String colour;
    protected boolean isFaceDown;
    protected boolean isGap = ((this instanceof Card) && !((this instanceof CourtCard) || (this instanceof NumeralCard)));

    enum Suit {
        CLUBS, DIAMONDS, HEARTS, SPADES;
    }

    enum Value {
        ACE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10), JACK(11), QUEEN(12), KING(13);

        private int numericValue;

        private Value(int num) {
            this.numericValue = num;
        }

        public int getNumericValue() {
            return numericValue;
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
        if ((this.getSuit() == Card.Suit.CLUBS) || (this.getSuit() == Card.Suit.SPADES)) {
            this.colour = "Black";
        } else {
            this.colour = "Red";
        }
    }

    public String getColour() {
        return this.colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public boolean getIsFaceDown() {
        return this.isFaceDown;
    }

    public void setIsFaceDown(boolean bool) {
        this.isFaceDown = bool;
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

    @Override
    public String toString() {
        if ((this.getValue() != null) && (this.getSuit() != null)) {
            //if card is not a gap card

            if (this.isFaceDown == false) {
                //if this card is face up
                if (this instanceof CourtCard) {
                    return "[" + this.getValue().toString().substring(0, 1) + " " + this.getSuit().toString().substring(0, 1) + "]";
                } else {
                    return "[" + this.getValue().getNumericValue() + " " + this.getSuit().toString().substring(0, 1) + "]";
                }
            } else {
                //face down 
                return "[XXX]";
            }

        } else {
            //gap card 
            return "[---]";
        }
    }
}//end card class 
