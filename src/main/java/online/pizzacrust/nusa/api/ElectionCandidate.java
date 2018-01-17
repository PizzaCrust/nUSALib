package online.pizzacrust.nusa.api;

/**
 * Usually immutable
 */
public interface ElectionCandidate {

    String getNativeName();

    default String getUsername() {
        if (getNativeName().split(" ").length >= 2) {
            return getNativeName().split(" ")[1];
        }
        return getNativeName();
    }

    int getVotes();

}
