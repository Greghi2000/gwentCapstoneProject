import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CardTest {

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

    }

    @Test
    public void hasSpecialAbility(){
        assertEquals("Deploy, Melee: Drain all boosts from an enemy.\n", card.getAbility());
    }
}
