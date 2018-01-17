package online.pizzacrust.nusa.api;

import java.util.List;

public interface Election {

    String getName();

    List<ElectionCandidate> getElectionCandidates();

}
