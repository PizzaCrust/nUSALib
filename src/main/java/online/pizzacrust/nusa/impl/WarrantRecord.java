package online.pizzacrust.nusa.impl;

import online.pizzacrust.nusa.api.PreservedRecord;
import online.pizzacrust.nusa.trello.Card;
import online.pizzacrust.nusa.trello.DefaultStrategy;
import online.pizzacrust.nusa.trello.TrelloRecord;

public class WarrantRecord extends TrelloRecord implements PreservedRecord {

    private final int id;
    private final boolean is;
    private final boolean was;

    public WarrantRecord(int robloxUserId) {
        super("wyKybjGu");
        this.id = robloxUserId;
        Card[] cards = getCardsInList("AoS Players");
        Card[] wasCards = getCardsInList("AoS Served");
        Card aos = DefaultStrategy.HASH_COLON_STRATEGY.getStrategy().find(cards, "" +
                robloxUserId);
        Card wasAos = DefaultStrategy.HASH_COLON_STRATEGY.getStrategy().find(wasCards, "" +
                robloxUserId);
        this.is = aos != null;
        this.was = wasAos != null;
    }

    public boolean is() {
        return is;
    }

    public int getId() {
        return id;
    }

    public boolean was() {
        return was;
    }

    public static void main(String... args) throws Exception {
        WarrantRecord warrantRecord = new WarrantRecord(38043848);
        System.out.println("was: " +warrantRecord.was);
        System.out.println("is: " + warrantRecord.is);
    }
}
