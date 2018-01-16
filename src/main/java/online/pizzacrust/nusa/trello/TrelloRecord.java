package online.pizzacrust.nusa.trello;

import com.google.gson.Gson;

import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import online.pizzacrust.nusa.api.Record;

public abstract class TrelloRecord implements Record {

    private final String boardId;

    protected TrelloRecord(String boardId) {
        this.boardId = boardId;
        initTrello();
    }

    public static boolean TRELLO_INIT = false;

    public static void initTrello() {
        TRELLO_INIT = false;
        Unirest.setObjectMapper(new ObjectMapper() {
            public <T> T readValue(String s, Class<T> aClass) {
                return new Gson().fromJson(s, aClass);
            }

            public String writeValue(Object o) {
                return new Gson().toJson(o);
            }
        });
    }

    public Card[] getCards() {
        String string = "https://api.trello.com/1/boards/" + boardId + "/cards";
        try {
            return Unirest.get(string).asObject(Card[].class).getBody();
        } catch (UnirestException e) {
            e.printStackTrace();
            return new Card[0];
        }
    }

    public Card[] getCardsInList(String listName) {
        try {
            TrelloList[] lists = Unirest.get("https://api.trello.com/1/boards/" + boardId + "/lists")
                    .asObject(TrelloList[].class).getBody();
            for (TrelloList list : lists) {
                if (list.getName().equalsIgnoreCase(listName)) {
                    String url = "https://api.trello.com/1/lists/" + list.getId() +"/cards";
                    return Unirest.get(url).asObject(Card[].class).getBody();
                }
            }
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return new Card[0];
    }

    public Card findCardByName(Card[] cards, String name) {
        for (Card card : cards) {
            if (card.getName().equalsIgnoreCase(name)) {
                return card;
            }
        }
        return null;
    }

}
