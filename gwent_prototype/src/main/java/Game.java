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
        player1 = null;
        player2 = null;
        board = new Board(null, null);

    }

    public void start() {
        // Load game data from JSON file
        databaseManager.loadCardsFromJSON("src/main/resources/Cards.json");



        // Get player names and initialize players
        String player1Name = getPlayerName(1);
        String player2Name = getPlayerName(2);

        player1 = new Player(player1Name);
        player1.setPlayerNumber(1);

        player2 = new Player(player2Name);
        player2.setPlayerNumber(2);

        // Initialize the board
        board = new Board(player1, player2);

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
        List<Card> availableCards = databaseManager.getCardBank();
        int max = availableCards.size();

        // Generate a random number
        RandomNumberGenerator rng = new RandomNumberGenerator(max);
        int randomNumber = rng.getRandomNumber();

        // Select a random subset of cards from the available cards
        int numCardsToDeal = 5; // Number of cards to deal
        ArrayList<Card> playerHand = new ArrayList<>();

        for (int i = 0; i < numCardsToDeal; i++) {
            // Generate a new random number for each card
            randomNumber = rng.getRandomNumber();

            // Add the randomly selected card to the player's hand
            Card selectedCard = availableCards.get(randomNumber);
            playerHand.add(selectedCard);

            // Remove the selected card from the available cards
            availableCards.remove(randomNumber);
            max--;
        }

        player.setHand(playerHand);
    }

    private void playGame() {
        // Implement the main game loop
        boolean gameOver = false;

        while (!gameOver) {
            // Begin the round
            System.out.println("Starting a new round...");
            player1.setHasForfeit(false);
            player2.setHasForfeit(false);

            // Begin the turn
            //need to check here that player has not forfeit or ran out of cards, as you can keep placing cards down even if your opponent is out
            System.out.println();
            System.out.println(ConsoleColours.BLUE + "Player 1's turn:" + ConsoleColours.RESET);
            playTurn(player1);

            // Check if the round or game is over
            if (isRoundOver()) {
                endRound();
                if (isGameOver()) {
                    gameOver = true;
                }
            }

            // Begin the turn
            System.out.println();
            System.out.println(ConsoleColours.GREEN + "Player 2's turn:" + ConsoleColours.RESET);
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
        System.out.println();
        System.out.println("Select a card from your hand:");
        List<Card> hand = player.getHand();

        while (true) {
            board.displayBoard();

            for (int i = 0; i < hand.size(); i++) {
                System.out.print((i + 1) + ". " + hand.get(i).getName() + "(" + ConsoleColours.YELLOW + hand.get(i).getProvision() + ConsoleColours.RESET + ")  ");
            }
            System.out.println(ConsoleColours.RED + "0. Forfeit round" + ConsoleColours.RESET);

            if (scanner.hasNextInt()) {
                int selectedIndex = scanner.nextInt();
                if (selectedIndex >= 1 && selectedIndex <= hand.size()) {
                    Card selectedCard = hand.get(selectedIndex - 1);
                    board.addCardToBoard(selectedCard, player.getPlayerNumber());
                    player.removeFromHand(selectedCard);
                    break;
                } else if (selectedIndex == 0) {
                    player.setHasForfeit(true);
                    break;
                } else {
                    System.out.println("Invalid card selection. Please choose a valid card option.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); //fix to prevent infinite loop
            }
        }

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

        if (player1.getLifes() <= 0 || player1.getLifes() < player2.getLifes()) {
            System.out.println("Player 2 wins the game!");
        } else if (player2.getLifes() <= 0 || player2.getLifes() < player1.getLifes()) {
            System.out.println("Player 1 wins the game!");
        } else {
            System.out.println("Game ended in a draw!");
        }
    }

}
