import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PlayerTest {

    Player player1;
    Card card;

    @Before
    public void before() {
        card = new Card(
                "Regis: Higher Vampire",
                "Vampire",
                "Deploy, Melee: Drain all boosts from an enemy.\n",
                "<span class=\"keyword deploy\">Deploy</span>, <span class=\"keyword melee\">Melee</span>: <span class=\"keyword drain\">Drain</span> all boosts from an enemy.\n",
                "<span class=\"keyword\">Deploy:</span> Trigger this ability when played.\n <span class=\"keyword\">Melee:</span> This ability can only be used while on the melee row.\n <span class=\"keyword\">Drain:</span> Deal damage and boost self by the same amount.",
                "He becomes invisible at will. His glance hypnotizes into a deep sleep. He then drinks his fill, turns into a bat and flies off. Altogether uncouth.",
                1012,
                112106,
                1002,
                "BaseSet",
                "Unit",
                0,
                "Gold",
                3,
                0,
                "Marek Madej",
                "Legendary",
                "Neutral",
                "",
                9,
                "",
                "melee"
        );

        player1 = new Player("Paul");
        player1.addToDeck(card);

    }

    @Test
    public void canAddCardToDeck(){
        player1.addToDeck(card);
        assertEquals("Regis: Higher Vampire", player1.getDeck().get(0).getName());
    }

    @Test
    public void canAddCardToHand(){
        player1.addToHand(card);
        assertEquals("Regis: Higher Vampire", player1.getHand().get(0).getName());
        assertEquals(0, player1.getDeck().size(), 0.0);
    }

    @Test
    public void canSelectCard(){
        player1.addToHand(card);
        player1.selectCard(card);
        assertEquals("Regis: Higher Vampire", player1.getSelectedCard().getName());
    }

    @Test
    public void canPlayCard(){
        player1.playCard();
        assertEquals(null, player1.getSelectedCard());
        assertEquals(0, player1.getHand().size());
    }



}
