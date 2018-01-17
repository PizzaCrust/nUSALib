package online.pizzacrust.nusa.impl;

import online.pizzacrust.nusa.api.ElectionCandidate;

public class BaseElectionCandidate implements ElectionCandidate {

    private final String nativeName;
    private final int votes;

    public BaseElectionCandidate(String nativeName, int votes) {
        this.nativeName = nativeName;
        this.votes = votes;
    }

    @Override
    public String getNativeName() {
        return nativeName;
    }

    @Override
    public int getVotes() {
        return votes;
    }
}
