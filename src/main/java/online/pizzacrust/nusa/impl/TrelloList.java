package online.pizzacrust.nusa.impl;

public class TrelloList {

    public String id;
    public String name;

    public TrelloList(String id, String name) {
        this.id = id;
        this.name = name;
        TrelloRecord.initTrello();
    }

    public TrelloList() {
        TrelloRecord.initTrello();
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
}
