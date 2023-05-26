import java.util.ArrayList;
import java.util.HashMap;

public class Board {

    private HashMap<String, ArrayList<Card>> player1Cards;  // HashMap to hold player 1 cards
    private HashMap<String, ArrayList<Card>> player2Cards;  // HashMap to hold player 2 cards

    private int player1MeleeTotal;
    private int player1RangedTotal;
    private int player1SiegeTotal;
    private int player1TotalScore;
    private int player2MeleeTotal;
    private int player2RangedTotal;
    private int player2SiegeTotal;
    private int player2TotalScore;

    public Board() {
        player1Cards = new HashMap<>();
        player1Cards.put("melee", new ArrayList<>());
        player1Cards.put("ranged", new ArrayList<>());
        player1Cards.put("siege", new ArrayList<>());

        player2Cards = new HashMap<>();
        player2Cards.put("melee", new ArrayList<>());
        player2Cards.put("ranged", new ArrayList<>());
        player2Cards.put("siege", new ArrayList<>());
    }

    public HashMap<String, ArrayList<Card>> getPlayer1Cards() {
        return player1Cards;
    }

    public HashMap<String, ArrayList<Card>> getPlayer2Cards() {
        return player2Cards;
    }

    public int getPlayer1MeleeTotal() {
        return player1MeleeTotal;
    }

    public void setPlayer1MeleeTotal(int player1MeleeTotal) {
        this.player1MeleeTotal = player1MeleeTotal;
    }

    public int getPlayer1RangedTotal() {
        return player1RangedTotal;
    }

    public void setPlayer1RangedTotal(int player1RangedTotal) {
        this.player1RangedTotal = player1RangedTotal;
    }

    public int getPlayer1SiegeTotal() {
        return player1SiegeTotal;
    }

    public void setPlayer1SiegeTotal(int player1SiegeTotal) {
        this.player1SiegeTotal = player1SiegeTotal;
    }

    public int getPlayer1TotalScore() {
        return player1TotalScore;
    }

    public void setPlayer1TotalScore(int player1TotalScore) {
        this.player1TotalScore = player1TotalScore;
    }

    public int getPlayer2MeleeTotal() {
        return player2MeleeTotal;
    }

    public void setPlayer2MeleeTotal(int player2MeleeTotal) {
        this.player2MeleeTotal = player2MeleeTotal;
    }

    public int getPlayer2RangedTotal() {
        return player2RangedTotal;
    }

    public void setPlayer2RangedTotal(int player2RangedTotal) {
        this.player2RangedTotal = player2RangedTotal;
    }

    public int getPlayer2SiegeTotal() {
        return player2SiegeTotal;
    }

    public void setPlayer2SiegeTotal(int player2SiegeTotal) {
        this.player2SiegeTotal = player2SiegeTotal;
    }

    public int getPlayer2TotalScore() {
        return player2TotalScore;
    }

    public void setPlayer2TotalScore(int player2TotalScore) {
        this.player2TotalScore = player2TotalScore;
    }

    public void addCardToBoard(Card card, int player) {
        if (player == 1) {
            if (card.getBoardType().equals("melee")){
                player1Cards.get("melee").add(card);
                setPlayer1MeleeTotal(calculateRowTotal(getPlayer1Cards().get("melee")));
            }
            else if (card.getBoardType().equals("ranged")){
                player1Cards.get("ranged").add(card);
                setPlayer1RangedTotal(calculateRowTotal(getPlayer1Cards().get("ranged")));
            }
            else if (card.getBoardType().equals("siege")){
                player1Cards.get("siege").add(card);
                setPlayer1SiegeTotal(calculateRowTotal(getPlayer1Cards().get("siege")));
            }
            setPlayer1TotalScore(getPlayer1MeleeTotal() + getPlayer1RangedTotal() + getPlayer1SiegeTotal());
        } else if (player == 2) {
            if (card.getBoardType().equals("melee")){
                player2Cards.get("melee").add(card);
                setPlayer2MeleeTotal(calculateRowTotal(getPlayer2Cards().get("melee")));
            }
            else if (card.getBoardType().equals("ranged")){
                player2Cards.get("ranged").add(card);
                setPlayer2RangedTotal(calculateRowTotal(getPlayer2Cards().get("ranged")));

            }
            else if (card.getBoardType().equals("siege")){
                player2Cards.get("siege").add(card);
                setPlayer2SiegeTotal(calculateRowTotal(getPlayer2Cards().get("siege")));
            }
            setPlayer2TotalScore(getPlayer2MeleeTotal() + getPlayer2RangedTotal() + getPlayer2SiegeTotal());
        } else {
            System.out.println("Invalid player!");
        }
    }

    public int calculateRowTotal(ArrayList<Card> row){
        int total = 0;
        for (Card card : row){
            total += card.getProvision();
        }
        return total;
    }

    public void removeCardFromBoard(Card card, String player) {
        if (player.equals("player1")) {
            if (card.getBoardType().equals("melee")) {
                player1Cards.get("melee").remove(card);
                setPlayer1MeleeTotal(calculateRowTotal(getPlayer1Cards().get("melee")));
            } else if (card.getBoardType().equals("ranged")) {
                player1Cards.get("ranged").remove(card);
                setPlayer1RangedTotal(calculateRowTotal(getPlayer1Cards().get("ranged")));
            } else if (card.getBoardType().equals("siege")) {
                player1Cards.get("siege").remove(card);
                setPlayer1SiegeTotal(calculateRowTotal(getPlayer1Cards().get("siege")));
            }
            setPlayer1TotalScore(getPlayer1MeleeTotal() + getPlayer1RangedTotal() + getPlayer1SiegeTotal());
        } else if (player.equals("player2")) {
            if (card.getBoardType().equals("melee")) {
                player2Cards.get("melee").remove(card);
                setPlayer2MeleeTotal(calculateRowTotal(getPlayer2Cards().get("melee")));
            } else if (card.getBoardType().equals("ranged")) {
                player2Cards.get("ranged").remove(card);
                setPlayer2RangedTotal(calculateRowTotal(getPlayer2Cards().get("ranged")));
            } else if (card.getBoardType().equals("siege")) {
                player2Cards.get("siege").remove(card);
                setPlayer2SiegeTotal(calculateRowTotal(getPlayer2Cards().get("siege")));
            }
            setPlayer2TotalScore(getPlayer2MeleeTotal() + getPlayer2RangedTotal() + getPlayer2SiegeTotal());
        } else {
            System.out.println("Invalid player!");
        }
    }

    public void displayBoard() {
        System.out.println("Player 1: ");
        System.out.print("Melee: ");
        System.out.println(getPlayer1Cards().get("melee") + " Score: " + getPlayer1MeleeTotal());
        System.out.print("Ranged: ");
        System.out.println(getPlayer1Cards().get("ranged") + " Score: " + getPlayer1RangedTotal());
        System.out.print("Siege: ");
        System.out.println(getPlayer1Cards().get("siege") + " Score: " + getPlayer1SiegeTotal());

        System.out.print("Total Score: ");
        System.out.println(getPlayer1TotalScore());
        System.out.println();

        System.out.println("Player 2: ");
        System.out.print("Melee: ");
        System.out.println(getPlayer2Cards().get("melee") + " Score: " + getPlayer2MeleeTotal());
        System.out.print("Ranged: ");
        System.out.println(getPlayer2Cards().get("ranged") + " Score: " + getPlayer2RangedTotal());
        System.out.print("Siege: ");
        System.out.println(getPlayer2Cards().get("siege") + " Score: " + getPlayer2SiegeTotal());

        System.out.print("Total Score: ");
        System.out.println(getPlayer2TotalScore());
    }

    public void resetBoard() {
        // Clear player1Cards and player2Cards
        player1Cards.get("melee").clear();
        player1Cards.get("ranged").clear();
        player1Cards.get("siege").clear();

        player2Cards.get("melee").clear();
        player2Cards.get("ranged").clear();
        player2Cards.get("siege").clear();

        // Reset scores to zero
        setPlayer1MeleeTotal(0);
        setPlayer1RangedTotal(0);
        setPlayer1SiegeTotal(0);
        setPlayer1TotalScore(0);

        setPlayer2MeleeTotal(0);
        setPlayer2RangedTotal(0);
        setPlayer2SiegeTotal(0);
        setPlayer2TotalScore(0);
    }






    // Other methods and functionality of the Board class
}
