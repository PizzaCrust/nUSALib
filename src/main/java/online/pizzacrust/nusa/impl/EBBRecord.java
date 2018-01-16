package online.pizzacrust.nusa.impl;

import online.pizzacrust.nusa.trello.Card;
import online.pizzacrust.nusa.trello.DefaultStrategy;
import online.pizzacrust.nusa.trello.RobloxTrelloRecord;
import online.pizzacrust.nusa.trello.TrelloRecord;

public class EBBRecord extends RobloxTrelloRecord {

    public EBBRecord(int robloxUserId) {
        super("FD8R2W64", robloxUserId);
    }

    protected boolean handle() {
        Card[] cards = getCardsInList("Executive Blacklist");
        Card potential = DefaultStrategy.USERNAME_STRATEGY.getStrategy().find(cards, "" +
                this.robloxUserId);
        return potential != null;
    }

    public static void main(String... args) throws Exception {
        //303513030, 25489628
        EBBRecord ebbRecord = new EBBRecord(25489628);
        System.out.println("p_rge " + ebbRecord.is());
        System.out.println("legendaryglo " + new EBBRecord(303513030).is());
    }
}
