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
        Turn turn;
        Grid grid;
        List<Player> players;
        Player currentPlayer;
        Player player1;
        Player player2;
        boolean playerIsStuck;

        turn = getDetails(input, game);
        grid = new Grid(7);
        game.setGrid(grid);
        grid.setupGrid();

        System.out.println("Note: To choose a card enter a number between 0 and " + grid.getSize() + " for the x and y co-ordinates");
        System.out.println("Key: [XXX] = face down card, [---] = gap in  the grid, [A H] = Ace of Hearts");

        players = game.getPlayers();
        player1 = players.get(0);
        currentPlayer = player1;
        player2 = players.get(1);

        while (game.getNumberOfDeals() < 3) {
            while ((!player1.getIsStuck()) && (!player2.getIsStuck())) {
                //no players are stuck
                System.out.println(grid);
                playerIsStuck = game.checkForStuckPlayer(grid);
                if (playerIsStuck) {
                    currentPlayer.setIsStuck(true);
                    System.out.println("current player is stuck " + currentPlayer.getName());
                } else {

                    System.out.println("Current player: " + currentPlayer.getName() + " colour = " + currentPlayer.getColour());
                    System.out.println("Please choose a card on the grid (using x y)");

                    int x = input.nextInt();
                    int y = input.nextInt();
                    Card selectedCard = grid.getCards().get(y).get(x);

                    while (selectedCard.isGap) {
                        System.out.println("Please choose a card (x y) (not the gap [---]) ");
                        x = input.nextInt();
                        y = input.nextInt();
                        selectedCard = grid.getCards().get(y).get(x);
                    }

                    while (!selectedCard.getColour().equalsIgnoreCase(turn.getCurrentTurnColour())) {
                        //player picks wrong card colour or selects gap card 
                        System.out.println("Please choose a card of your own colour (x y)");
                        x = input.nextInt();
                        y = input.nextInt();
                        selectedCard = grid.getCards().get(y).get(x);
                    }
                    grid.moveCard(selectedCard);

                    if (turn.getCurrentTurnColour().equalsIgnoreCase(player1.getColour())) {
                        currentPlayer = player2;
                    } else {
                        currentPlayer = player1;
                    }
                    turn.setCurrentTurnColour(currentPlayer.getColour());
                }
            }
            game.calculateScore();
            game.setNumberOfDeals(game.getNumberOfDeals() + 1);

            //swap player who starts 
            grid = new Grid(7);
            game.setGrid(grid);
            grid.setupGrid();
            //create a new grid 
            //carry scores forward 

            player1.setIsStuck(false);
            player2.setIsStuck(false);
            System.out.println("\n--------------------------------------------------");
            System.out.println("current deal number: " + game.getNumberOfDeals());
        }

        System.out.println("\n--------------------------------------------------");
        System.out.println("Results of the match are ");

        if (player1.getIsWinner()) {
            System.out.println("The winner:");
            System.out.println(player1);

            System.out.println("\nThe loser:");
            System.out.println(player2);
        } else {
            System.out.println("The winner:");
            System.out.println(player2);

            System.out.println("\nThe loser:");
            System.out.println(player1);
        }

    }

    public static Turn getDetails(Scanner input, Game game) {
        Turn turn;
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
            p2Colour = "Black";
        } else {
            p1Colour = "Black";
            p2Colour = "Red";

        }
        System.out.println("Player Twos colour is " + p2Colour);
        turn = new Turn(p1Colour);
        game.register(p1Name, p1Colour, p2Name, p2Colour);
        return turn;
    }
}
