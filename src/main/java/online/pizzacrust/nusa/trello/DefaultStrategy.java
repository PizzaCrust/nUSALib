package online.pizzacrust.nusa.trello;

public enum DefaultStrategy {

    HASH_COLON_STRATEGY(new TrelloStrategy.HashColonStrategy());

    private final TrelloStrategy strategy;

    DefaultStrategy(TrelloStrategy strategy) {
        this.strategy = strategy;
    }

    public TrelloStrategy getStrategy() {
        return strategy;
    }
}
