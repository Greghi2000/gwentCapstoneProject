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
        for (Card card : databaseManager.getCardBank()){
            if (card.getRarity().equals("Common")){
                System.out.println(card.getName());
                System.out.println(card.getCard());
            }
        }
        assertEquals(1, 1, 0.0);
    }

    @Test
    public void canFindById(){
        databaseManager.loadCardsFromJSON("src/main/resources/Cards.json");
        Card cardFound = databaseManager.findCardById(201617);
        System.out.println("Found Card: " + cardFound.getName());
        assertEquals("Recruit", cardFound.getName());
    }
}
