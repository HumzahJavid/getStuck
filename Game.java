package getstuck;

/**
 *
 * @author humzah
 */
import java.util.ArrayList;
import java.util.List;

public class Game {

    private int numberOfDeals;
    private int numberOfMatches;
    private List<Player> players;
    private Grid grid;

    public Game() {
        this.numberOfDeals = 1;
        this.numberOfMatches = 0;
        this.players = null;
    }

    public Grid getGrid() {
        return this.grid;
    }

    public int getNumberOfDeals() {
        return this.numberOfDeals;
    }

    public int getNumberOfMatches() {
        return this.numberOfMatches;
    }

    public List<Player> getPlayers() {
        return this.players;
    }

    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    public void setNumberOfDeals(int n) {
        this.numberOfDeals = n;
    }

    public void setNumberOfMatches(int n) {
        this.numberOfMatches = n;
    }

    public void setPlayers(List<Player> n) {
        this.players = n;
    }

    public void calculateScore() {
        int stuckPlayerScore;
        int unstuckPlayerScore;
        List<Player> players = this.getPlayers();
        Grid grid = this.getGrid();
        List<List<Card>> cards = grid.getCards();
        List<Card> cardsToRemove = new ArrayList<Card>();
        Player stuckPlayer = this.getStuckPlayer(players);
        Player unstuckPlayer = null;
        
        for (Player tempUnstuckPlayer : players) {
            if (tempUnstuckPlayer.equals(stuckPlayer)) { 
            } else {
                unstuckPlayer = tempUnstuckPlayer;
            }
        } 
        //might want to look into list of unstuck players for reuseable code

        
        for (List<Card> row : cards) {
            for (Card card : row) {
                if (card.getIsFaceDown() == true) {
                    cardsToRemove.add(card);
                }
            }
        }
        //collect all face down cards 

        cardsToRemove.add(grid.getGap());
        //add the gap card to cardToRemove

        for (List<Card> row : cards) {
            row.removeAll(cardsToRemove);
            //remove all facedown cards and the gap card
        }
        //cards now contains all the faceup cards on the grid
        
        stuckPlayerScore = stuckPlayer.calculateStuckPlayerScore(cards);
        unstuckPlayerScore = unstuckPlayer.calculateUnstuckPlayerScore(cards);
        //each player is given the same set of cards which filter out their respective cards 

        System.out.println(stuckPlayerScore);
        System.out.println(unstuckPlayerScore);

        if ((this.getNumberOfDeals() % 2) == 0) {
            //if it is the end of the match
            if (stuckPlayerScore > unstuckPlayerScore) {
                stuckPlayer.setIsWinner(true);
            } else {
                unstuckPlayer.setIsWinner(true);
            }
            //assign a winner and loser
        }
        System.out.println("The stuck player:");
        System.out.println(stuckPlayer);
        
        System.out.println("\nThe unstuck player:");
        System.out.println(unstuckPlayer);
    }

    public boolean checkForStuckPlayer(Grid grid, Turn turn) {
        boolean validHorizontalMove;
        boolean validVerticalMove;
        boolean validDiagonalMove;
        List<List<Card>> hCards = grid.getHorizontalCards("Black", grid.getGap());
        List<List<Card>> vCards = grid.getVerticalCards("Black", grid.getGap());
        List<List<Card>> dCards = grid.getDiagonalCards("Black", grid.getGap());

        validHorizontalMove = (turn.isValidMove(hCards.get(0)) || turn.isValidMove(hCards.get(1)));
        validVerticalMove = (turn.isValidMove((vCards.get(0))) || turn.isValidMove(vCards.get(1)));
        validDiagonalMove = (turn.isValidMove(dCards.get(0)) || turn.isValidMove(dCards.get(1))
                || turn.isValidMove(dCards.get(2)) || turn.isValidMove(dCards.get(3)));

        if (validHorizontalMove) {
            return false;
        } else if (validVerticalMove) {
            return false;
        } else if (validDiagonalMove) {
            return false;
        } else {
            return true;
        }
    }

    public Player getHighestScoringPlayer(List<Player> players) {
        Player highestScoringPlayer = players.get(0);
        for (Player potentialPlayer : players) {
            if (potentialPlayer.getScore() > highestScoringPlayer.getScore()) {
                highestScoringPlayer = potentialPlayer;
            }       
        }
        return highestScoringPlayer;
    }

    public Player getStuckPlayer(List<Player> players) {
        for (Player player : players) {
            if (player.getIsStuck()) {
                return player;
            }
        }
        return null;
    }

    public void register(String name1, String col1, String name2, String col2) {
        Player player1 = new Player(name1, col1, 1);
        Player player2 = new Player(name2, col2, 2);
        List<Player> players = new ArrayList();
        players.add(player1);
        players.add(player2);
        this.setPlayers(players);
    }
}//end game 

