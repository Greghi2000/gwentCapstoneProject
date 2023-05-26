import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    private List<Card> cardBank;

    public DatabaseManager() {
        cardBank = new ArrayList<>();
    }

    public void loadCardsFromJSON(String filePath) {
        JSONObject jsonObject;
        try {
            JSONTokener tokener = new JSONTokener(new FileInputStream(filePath));
            jsonObject = new JSONObject(tokener);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }

        JSONObject response = jsonObject.getJSONObject("response");
        for (String key : response.keySet()) {
            if (key.equals("request")) {
                continue;
            }

            JSONObject item = response.getJSONObject(key);
            JSONObject id = item.getJSONObject("id");
            int artId = id.getInt("art");
            int cardId = id.getInt("card");
            int audioId = id.getInt("audio");

            JSONObject attributes = item.getJSONObject("attributes");
            String set = attributes.getString("set");
            String type = attributes.getString("type");
            int armor = attributes.getInt("armor");
            String color = attributes.getString("color");
            int power = attributes.getInt("power");
            int reach = attributes.getInt("reach");
            String artist = attributes.getString("artist");
            String rarity = attributes.getString("rarity");
            String faction = attributes.getString("faction");
            String related = attributes.getString("related");
            int provision = attributes.getInt("provision");
            String factionSecondary = attributes.getString("factionSecondary");

            String name = item.getString("name");
            String category = item.getString("category");
            String ability = item.getString("ability");
            String abilityHtml = item.getString("ability_html");
            String keywordHtml = item.getString("keyword_html");
            String flavor = item.getString("flavor");

            Card card = new Card(name, category, ability, abilityHtml, keywordHtml, flavor,
                    artId, cardId, audioId, set, type, armor, color, power, reach, artist, rarity, faction,
                    related, provision, factionSecondary, "melee");
            cardBank.add(card);
        }
    }

    public List<Card> getCardBank() {
        return cardBank;
    }

    public void printCards() {
        for (Card card : cardBank) {
            System.out.println(card.getName());
            System.out.println("---------------");
        }
    }
}
