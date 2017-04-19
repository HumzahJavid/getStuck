package getstuck;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class GetStuck {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Game game = new Game();
        List<Player> players;
        Grid grid;
        getDetails(input, game);

        grid = new Grid(7);
        grid.setupGrid();
        System.out.println(grid);
        
        System.out.println("Note: To choose a card enter a number between 0 and " + grid.getSize() + " for the x and y co-ordinates");
        System.out.println("Key: [XXX] = face down card, [---] = gap in  the grid, [A H] = Ace of Hearts");
        
        players = game.getPlayers();
        
        while (true) {
            System.out.println("Please choose a card on the grid (using x y)");
            int x = input.nextInt();
            int y = input.nextInt();
            Card selectedCard = grid.getCards().get(y).get(x);
            grid.moveCard(selectedCard);
            System.out.println("\n" + grid);
        }
    }

    public static void getDetails(Scanner input, Game game) {
        String p1Name = "";
        String p2Name = "";
        String p1Colour = "";
        String p2Colour = "";
        System.out.println("Please enter Player Ones name");
        p1Name = input.next();

        while (!(p1Colour.equalsIgnoreCase("R")) && !(p1Colour.equalsIgnoreCase("B"))) {
            System.out.println("Please enter Player Ones colour (R for red or B for black)");
            p1Colour = input.next();
        }

        System.out.println("Please enter Player Twos name");
        p2Name = input.next();

        if (p1Colour.equalsIgnoreCase("R")) {
            p1Colour = "Red";
            p2Colour = "Blue";
        } else {
            p1Colour = "Blue";
            p2Colour = "Red";

        }
        System.out.println("Player Twos colour is " + p2Colour);

        game.register(p1Name, p1Colour, p2Name, p2Colour);
    }
}
