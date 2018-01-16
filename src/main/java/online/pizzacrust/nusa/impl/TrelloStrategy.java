package online.pizzacrust.nusa.impl;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public interface TrelloStrategy {

    Card find(Card[] cards, String source);

    /**
     * Standard strategy to find the user id. Always assumes that [1]  (USERID)
     * (USERNAME[DIVIDER]USERID) is the user id. Only takes in toFind which is the id.
     *
     * Processes trello cards to find the specific card in toFind (ID).
     */
    class IdStrategy implements TrelloStrategy {

        private final String divider;

        public IdStrategy(String divider) {
            this.divider = divider;
        }

        public Card find(Card[] cards, String toFind) {
            for (Card card : cards) {
                String[] splitted = card.getName().split(divider);
                if (splitted.length >= 2) {
                    if (toFind.equalsIgnoreCase(splitted[1])) {
                        return card;
                    }
                }
            }
            return null;
        }
    }

    /**
     * Assumes the cards are formatted like:
     * - USERNAME#USERID
     * - USERNAME:USERID
     */
    class HashColonStrategy implements TrelloStrategy {

        public Card find(Card[] cards, String toFind) {
            for (Card card : cards) {
                String[] colon = card.getName().split(":");
                String[] hash = card.getName().split("#");
                if (colon.length >= 2) {
                    if (toFind.equalsIgnoreCase(colon[1])) {
                        return card;
                    }
                }
                if (hash.length >= 2) {
                    if (toFind.equalsIgnoreCase(hash[1])) {
                        return card;
                    }
                }
            }
            return null;
        }

    }

    /**
     * Assumes cards are formatted like:
     * - USERNAME
     * - USERNAME/USERNAME <- splits into two diff
     * source = ROBLOX USER ID TARGET
     */
    class UsernameStrategy implements TrelloStrategy {

        public static class UsernameResponse {
            public Integer Id;
        }

        private Integer getIdOfName(String username) {
            String url = "https://api.roblox.com/users/get-by-username?username=" + username;
            try {
                return Unirest.get(url).asObject(UsernameResponse.class).getBody().Id;
            } catch (UnirestException e) {
                e.printStackTrace();
            }
            return null;
        }

        public Card find(Card[] cards, String source) {
            for (Card card : cards) {
                String[] slash = card.getName().split("/");
                if (slash.length >= 2) {
                    Integer user1 = getIdOfName(slash[0]);
                    Integer user2 = getIdOfName(slash[1]);
                    if (user1 != null) {
                        if ((user1 + "").equalsIgnoreCase(source)) {
                            return card;
                        }
                    }
                    if (user2 != null) {
                        if ((user2 + "").equalsIgnoreCase(source)) {
                            return card;
                        }
                    }
                }
                Integer userId = getIdOfName(card.getName());
                if (userId != null) {
                    if ((userId + "").equalsIgnoreCase(source)) {
                        return card;
                    }
                }
            }
            return null;
        }
    }

}
