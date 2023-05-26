public class Card {
    private String name;
    private String category;
    private String ability;
    private String abilityHtml;
    private String keywordHtml;
    private String flavor;
    private int art;
    private int card;
    private int audio;
    private String cardSet;
    private String cardType;
    private int armor;
    private String color;
    private int power;
    private int reach;
    private String artist;
    private String rarity;
    private String faction;
    private String related;
    private int provision;
    private String factionSecondary;
    private String boardType;

    // Constructor
    public Card(String name, String category, String ability, String abilityHtml, String keywordHtml, String flavor,
                int art, int card, int audio, String cardSet, String cardType, int armor, String color, int power,
                int reach, String artist, String rarity, String faction, String related, int provision,
                String factionSecondary, String boardType) {
        this.name = name;
        this.category = category;
        this.ability = ability;
        this.abilityHtml = abilityHtml;
        this.keywordHtml = keywordHtml;
        this.flavor = flavor;
        this.art = art;
        this.card = card;
        this.audio = audio;
        this.cardSet = cardSet;
        this.cardType = cardType;
        this.armor = armor;
        this.color = color;
        this.power = power;
        this.reach = reach;
        this.artist = artist;
        this.rarity = rarity;
        this.faction = faction;
        this.related = related;
        this.provision = provision;
        this.factionSecondary = factionSecondary;
        this.boardType = boardType;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getAbility() {
        return ability;
    }

    public String getAbilityHtml() {
        return abilityHtml;
    }

    public String getKeywordHtml() {
        return keywordHtml;
    }

    public int getArmor() {
        return armor;
    }

    public int getArt() {
        return art;
    }

    public int getCard() {
        return card;
    }

    public int getAudio() {
        return audio;
    }

    public String getFlavor() {
        return flavor;
    }

    public int getPower() {
        return power;
    }

    public int getProvision() {
        return provision;
    }

    public int getReach() {
        return reach;
    }

    public String getArtist() {
        return artist;
    }

    public String getCardSet() {
        return cardSet;
    }

    public String getCardType() {
        return cardType;
    }

    public String getColor() {
        return color;
    }

    public String getFaction() {
        return faction;
    }

    public String getFactionSecondary() {
        return factionSecondary;
    }

    public String getRarity() {
        return rarity;
    }

    public String getRelated() {
        return related;
    }

    public String getBoardType() {
        return boardType;
    }

    public void setBoardType(String boardType) {
        this.boardType = boardType;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setAbility(String ability) {
        this.ability = ability;
    }

    public void setAbilityHtml(String abilityHtml) {
        this.abilityHtml = abilityHtml;
    }

    public void setKeywordHtml(String keywordHtml) {
        this.keywordHtml = keywordHtml;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    public void setArt(int art) {
        this.art = art;
    }

    public void setCard(int card) {
        this.card = card;
    }

    public void setAudio(int audio) {
        this.audio = audio;
    }

    public void setCardSet(String cardSet) {
        this.cardSet = cardSet;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public void setReach(int reach) {
        this.reach = reach;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public void setFaction(String faction) {
        this.faction = faction;
    }

    public void setRelated(String related) {
        this.related = related;
    }

    public void setProvision(int provision) {
        this.provision = provision;
    }

    public void setFactionSecondary(String factionSecondary) {
        this.factionSecondary = factionSecondary;
    }

    public void specialAbility(){
        //when played
        System.out.println(this.ability);
    }
}



