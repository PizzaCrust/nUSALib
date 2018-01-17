package online.pizzacrust.nusa.impl;

import java.util.ArrayList;
import java.util.List;

import online.pizzacrust.nusa.api.Election;
import online.pizzacrust.nusa.api.ElectionCandidate;
import online.pizzacrust.nusa.trello.Card;
import online.pizzacrust.nusa.trello.TrelloRecord;

public class LVCCElections extends TrelloRecord implements Election {
    public LVCCElections() {
        super("6MXxtQnN");
    }

    @Override
    public String getName() {
        return "LVCC Elections";
    }

    @Override
    public List<ElectionCandidate> getElectionCandidates() {
        List<ElectionCandidate> candidates = new ArrayList<>();
        List<String> candNames = new ArrayList<>();
        for (Card cands : getCardsInList("Candidate list")) {
            candNames.add(cands.getName());
        }
        for (String candName : candNames) {
            Card[] votes = getCardsInList(candName);
            candidates.add(new BaseElectionCandidate(candName, votes.length));
        }
        return candidates;
    }

    @Override
    @Deprecated
    public boolean is() {
        return false;
    }

    @Override
    @Deprecated
    public int getId() {
        return 0;
    }


    public static void main(String... args) {
        LVCCElections lvccElections = new LVCCElections();
        for (ElectionCandidate electionCandidate : lvccElections.getElectionCandidates()) {
            System.out.println(electionCandidate.getUsername() + " " + electionCandidate.getVotes());
        }
    }

}
