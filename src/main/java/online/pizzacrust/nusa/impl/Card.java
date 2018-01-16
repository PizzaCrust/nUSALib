package online.pizzacrust.nusa.impl;

public class Card {

    public String name;
    public String desc;

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public Card(String name, String desc) {
        this.name = name;
        this.desc = desc;
        TrelloRecord.initTrello();
    }

    public Card() {
        TrelloRecord.initTrello();
    }
}
