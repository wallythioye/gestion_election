package senegal.election.sn.GestionElection.services;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import senegal.election.sn.GestionElection.DTOClass.CandidateResult;
import senegal.election.sn.GestionElection.entites.Candidate;
import senegal.election.sn.GestionElection.entites.Vote;
import senegal.election.sn.GestionElection.repository.VoteRepository;

@Service
public class ElectionService {

    private final VoteRepository voteRepository;

    @Autowired
    public ElectionService(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    public List<CandidateResult> getTopCandidates(int numberOfCandidates) {
       
        List<Vote> allVotes = voteRepository.findAll();

        Map<Candidate, Long> votesPerCandidate = allVotes.stream()
                .collect(Collectors.groupingBy(Vote::getCandidate, Collectors.counting()));

        List<Map.Entry<Candidate, Long>> votesPerCandidateList = votesPerCandidate.entrySet().stream().collect(Collectors.toList());

        List<CandidateResult> topCandidates = votesPerCandidateList.stream()
                .sorted((entry1, entry2) -> Long.compare(entry2.getValue(), entry1.getValue()))
                .limit(numberOfCandidates)
                .map(entry -> new CandidateResult(entry.getKey().getId(), entry.getKey().getName(), entry.getKey().getParty(), entry.getValue()))
                .collect(Collectors.toList());

        return topCandidates;
    }
}
