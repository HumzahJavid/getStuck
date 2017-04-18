package getstuck;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GetStuck {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Grid grid = new Grid(7);
        grid.setupGrid();
        List<Card> deck = grid.cards;
        
        for (int i = 0; i < deck.size(); i++) {
            System.out.println(deck.get(i));
        }

        System.out.println(grid.toString());
        
        System.out.println("\n");
        Card test = new Card();
        System.out.println(test.colour);
        System.out.println(test.isFaceDown);
        System.out.println(test.isGap);
        System.out.println(test.suit);
    }
}
