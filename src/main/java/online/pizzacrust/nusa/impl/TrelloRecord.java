package online.pizzacrust.nusa.impl;

import com.google.gson.Gson;

import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.util.List;

import online.pizzacrust.nusa.api.Record;

public abstract class TrelloRecord implements Record {

    private final String boardId;

    protected TrelloRecord(String boardId) {
        this.boardId = boardId;
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

    public Card findCardByName(Card[] cards, String name) {
        for (Card card : cards) {
            if (card.getName().equalsIgnoreCase(name)) {
                return card;
            }
        }
        return null;
    }

}
