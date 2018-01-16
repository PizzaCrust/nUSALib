package online.pizzacrust.nusa.trello;

public abstract class RobloxTrelloRecord extends TrelloRecord {

    protected final int robloxUserId;
    private final boolean is;

    protected RobloxTrelloRecord(String boardId, int robloxUserId) {
        super(boardId);
        this.robloxUserId = robloxUserId;
        this.is = handle();
    }

    protected abstract boolean handle();

    public boolean is() {
        return is;
    }

    public int getId() {
        return robloxUserId;
    }
}
