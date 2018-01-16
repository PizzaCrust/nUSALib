package online.pizzacrust.nusa.impl;

import online.pizzacrust.nusa.trello.Card;
import online.pizzacrust.nusa.trello.DefaultStrategy;
import online.pizzacrust.nusa.trello.RobloxTrelloRecord;

public class NSTRecord extends EBBRecord {

    public NSTRecord(int robloxUserId) {
        super(robloxUserId);
    }

    @Override
    protected boolean handle() {
        Card[] cards = getCardsInList("Executive Blacklist");
        Card potential = DefaultStrategy.USERNAME_STRATEGY.getStrategy().find(cards, "" +
                this.robloxUserId);
        return potential != null;
    }

}
