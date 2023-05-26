import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BoardTest {

    Board board1;
    Player player1;
    Card card;

    @Before
    public void before(){
        board1 = new Board();
        player1 = new Player("Paul");
        player1.addToDeck(card);
        player1.addToHand(card);
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
    }

    @Test
    public void canAddAndRemove(){
        player1.selectCard(card);
        Card cardToPlay = player1.playCard();
        board1.addCardToBoard(cardToPlay, 1);
        board1.addCardToBoard(cardToPlay, 1);
        assertEquals("Regis: Higher Vampire", board1.getPlayer1Cards().get("melee").get(0).getName());
        assertEquals(18, board1.getPlayer1MeleeTotal(), 0.0);
        assertEquals(18, board1.getPlayer1TotalScore(), 0.0);
        board1.removeCardFromBoard(card, "player1");
        board1.displayBoard();
        assertEquals(9, board1.getPlayer1TotalScore(), 0.0);
    }

}
