import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Game {
    private DatabaseManager databaseManager;
    private Board board;
    private Player player1;
    private Player player2;



    public Game() {
        databaseManager = new DatabaseManager();
        board = new Board();
        player1 = null;
        player2 = null;
    }

    public void start() {
        // Load game data from JSON file
        databaseManager.loadCardsFromJSON("src/main/resources/Cards.json");

        // Initialize the board
        board = new Board();

        // Get player names and initialize players
        String player1Name = getPlayerName(1);
        String player2Name = getPlayerName(2);

        player1 = new Player(player1Name);
        player1.setPlayerNumber(1);

        player2 = new Player(player2Name);
        player2.setPlayerNumber(2);

        // Deal cards to players
        dealCards(player1);
        dealCards(player2);

        // Set up initial game state (scores, lives, etc.)
        player1.setLifes(2);
        player2.setLifes(2);

        // Start the game
        playGame();
    }

    private String getPlayerName(int playerNumber) {
        Scanner scanner = new Scanner(System.in);
        String playerName;

        while (true) {
            System.out.print("Enter Player " + playerNumber + " name: ");
            playerName = scanner.nextLine();

            if (isValidName(playerName)) {
                break;
            } else {
                System.out.println("Invalid name. Please enter a valid name.");
            }
        }

        return playerName;
    }

    private boolean isValidName(String name) {
        return !name.isEmpty() && name.matches("[a-zA-Z]+");
    }


    //deals exact same cards to both players everytime
    private void dealCards(Player player) {
        // deal cards to the players
        List<Card> availableCards = databaseManager.getCardBank();
        ArrayList<Card> playerHand = new ArrayList<>(availableCards.subList(0, 3)); // Deal 10 cards to each player
        player.setHand(playerHand);
    }

    private void playGame() {
        // Implement the main game loop
        boolean gameOver = false;

        while (!gameOver) {
            // Begin the round
            System.out.println("Starting a new round...");



            // Begin the turn
            System.out.println("Player 1's turn:");
            playTurn(player1);

            // Check if the round or game is over
            if (isRoundOver()) {
                endRound();
                if (isGameOver()) {
                    gameOver = true;
                }
            }

            // Begin the turn
            System.out.println("Player 2's turn:");
            playTurn(player2);

            // Check if the round or game is over
            if (isRoundOver()) {
                endRound();
                if (isGameOver()) {
                    gameOver = true;
                }
            }
        }

        // End the game
        endGame();
    }

    private void playTurn(Player player) {
        // Prompt the player for a move, process the move, and update the game state

        Scanner scanner = new Scanner(System.in);
        System.out.println("Select a card from your hand:");
        List<Card> hand = player.getHand();

        while (true) {
            for (int i = 0; i < hand.size(); i++) {
                System.out.println((i + 1) + ". " + hand.get(i).getName() + "(" + hand.get(i).getProvision() + ")");
            }

            if (scanner.hasNextInt()) {
                int selectedIndex = scanner.nextInt();
                if (selectedIndex >= 1 && selectedIndex <= hand.size()) {
                    Card selectedCard = hand.get(selectedIndex - 1);
                    board.addCardToBoard(selectedCard, player.getPlayerNumber());
                    player.removeFromHand(selectedCard);
                    break;
                } else {
                    System.out.println("Invalid card selection. Please choose a valid card option.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // Consume the invalid input to prevent an infinite loop
            }
        }

        board.displayBoard();
    }



    private boolean isRoundOver() {
        // check if the round is over
        // If BOTH players have forfeited the round or if they have BOTH run out of cards then the round is over, or one has forfieted and one is out of cards

        //this could be refactored so that if a player hand is empty then they are auto forfeit
        return (player1.getHand().isEmpty() && player2.getHand().isEmpty()) ||
                (player1.hasForfeited() && player2.hasForfeited()) ||
                (player1.getHand().isEmpty() && player2.hasForfeited()) ||
                (player1.hasForfeited()  && player2.getHand().isEmpty());
    }

    private void endRound() {
        // Reduce player lives if they lose the round
        // Reset the board and player hands for the next round

        int player1Score = board.getPlayer1TotalScore();
        int player2Score = board.getPlayer2TotalScore();

        System.out.println("Player 1 Score: " + player1Score);
        System.out.println("Player 2 Score: " + player2Score);

        if (player1Score > player2Score) {
            player2.setLifes(player2.getLifes() - 1);
            System.out.println("Player 1 wins the round!");
        } else if (player2Score > player1Score) {
            player1.setLifes(player1.getLifes() - 1);
            System.out.println("Player 2 wins the round!");
        } else {
            player1.setLifes(player1.getLifes() - 1);
            player2.setLifes(player2.getLifes() - 1);
            System.out.println("Round ended in a draw!");

        }

        // Reset the board
        board.resetBoard();
    }

    private boolean isGameOver() {
        // game over if a player has no lives
        // Return true if the game is over, false otherwise

        return player1.getLifes() <= 0 || player2.getLifes() <= 0 ||
                player1.getHand().isEmpty() && player2.getHand().isEmpty();
    }

    private void endGame() {
        // Determine the game winner based on the final game state (lives)
        // Display the winner and any other relevant information

        if (player1.getLifes() <= 0) {
            System.out.println("Player 2 wins the game!");
        } else if (player2.getLifes() <= 0) {
            System.out.println("Player 1 wins the game!");
        } else {
            System.out.println("Game ended in a draw!");
        }
    }

}
