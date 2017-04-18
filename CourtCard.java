package getstuck;

/**
 *
 * @author humzah
 */
public class CourtCard extends Card {
    
    public CourtCard(Value value, Suit suit) {
        this.isFaceDown = false;
        this.value = value;
        this.suit = suit;
    }
}
