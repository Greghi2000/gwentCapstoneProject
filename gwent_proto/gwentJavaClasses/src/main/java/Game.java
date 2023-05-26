public class Game {
    private DatabaseManager databaseManager;

    public Game() {
        databaseManager = new DatabaseManager();
    }

    public void start() {
        // Load game data from JSON file
        databaseManager.loadCardsFromJSON("src/main/resources/Cards.json");
    }
}