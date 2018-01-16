package online.pizzacrust.nusa.impl;

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



}
