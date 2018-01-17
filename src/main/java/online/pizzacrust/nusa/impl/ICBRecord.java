package online.pizzacrust.nusa.impl;

import online.pizzacrust.nusa.trello.Card;
import online.pizzacrust.nusa.trello.DefaultStrategy;
import online.pizzacrust.nusa.trello.RobloxTrelloRecord;

public class ICBRecord extends RobloxTrelloRecord {
    protected ICBRecord(int robloxUserId) {
        super("1dfxqx0L", robloxUserId);
    }

    protected boolean handle() {
        Card[] icb = getCardsInList("IC Blacklist");
        Card potential = DefaultStrategy.USERNAME_STRATEGY.getStrategy().find(icb, "" +
                robloxUserId);
        return potential != null;
    }

    public static void main(String... args) {
        System.out.println(new ICBRecord(32067766).is());
    }

}
