import java.util.ArrayList;

public class Player {

    String name;
    ArrayList<Card> deck;
    ArrayList<Card> hand;
    int lifes;

    Card selectedCard;

    public Player(String name){
        this.name = name;
        this.deck = new ArrayList<>();
        this.hand = new ArrayList<>();
        lifes = 2;
        selectedCard = null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }

    public void setDeck(ArrayList<Card> deck) {
        this.deck = deck;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public void setHand(ArrayList<Card> hand) {
        this.hand = hand;
    }

    public int getLifes() {
        return lifes;
    }

    public void setLifes(int lifes) {
        this.lifes = lifes;
    }

    public Card getSelectedCard() {
        return selectedCard;
    }

    public void setSelectedCard(Card selectedCard) {
        this.selectedCard = selectedCard;
    }

    public void addToHand(Card card){
        hand.add(card);
        removeFromDeck(card);
    }

    public Card removeFromHand(Card card){
        hand.remove(card);
        return card;
    }

    public void addToDeck(Card card){
        deck.add(card);
    }

    public void removeFromDeck(Card card){
        deck.remove(card);
    }

    //first click (make it bigger or show extra info)
    public void selectCard(Card card) {
        selectedCard = card;
    }

    //second click play the card(return the card to the game class)
    public Card playCard() {
        Card playedCard = selectedCard;
        selectedCard = null;
        removeFromHand(getSelectedCard());
        return playedCard;
    }

    //need a forfit round method
}
