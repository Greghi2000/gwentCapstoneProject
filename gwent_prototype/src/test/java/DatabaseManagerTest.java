import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DatabaseManagerTest {

    DatabaseManager databaseManager;

    @Before
    public void before(){
        databaseManager = new DatabaseManager();
    }

    @Test
    public void canLoadCards(){
        databaseManager.loadCardsFromJSON("src/main/resources/Cards.json");
        databaseManager.printCards();
        assertEquals(1, 1, 0.0);
    }
}
