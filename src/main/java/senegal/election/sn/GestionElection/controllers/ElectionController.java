package senegal.election.sn.GestionElection.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import senegal.election.sn.GestionElection.DTOClass.CandidateResult;
import senegal.election.sn.GestionElection.entites.Candidate;
import senegal.election.sn.GestionElection.entites.Vote;
import senegal.election.sn.GestionElection.services.CandidateService;
import senegal.election.sn.GestionElection.services.ElectionService;
import senegal.election.sn.GestionElection.services.VoteService;
import senegal.election.sn.GestionElection.services.VoterService;

@RestController
@RequestMapping("/api")
public class ElectionController {

    private final ElectionService electionService;
    private final VoterService voterService;
    private final CandidateService candidateService;
    private final VoteService voteService;

    @Autowired
    public ElectionController(ElectionService electionService, VoterService voterService,
                              CandidateService candidateService, VoteService voteService) {
        this.electionService = electionService;
        this.voterService = voterService;
        this.candidateService = candidateService;
        this.voteService = voteService;
    }


    @PostMapping("/voting/cast-vote")
    public ResponseEntity<String> castVote(@RequestBody Vote vote) {
        String resultMessage = voteService.castVote(vote);
        return ResponseEntity.ok(resultMessage);
    }

    @GetMapping("/candidates")
    public ResponseEntity<List<Candidate>> getAllCandidates() {
        List<Candidate> candidates = candidateService.getAllCandidates();
        return ResponseEntity.ok(candidates);
    }

    @GetMapping("/results")
    public ResponseEntity<List<CandidateResult>> getProvisionalResults() {
        List<CandidateResult> provisionalResults = electionService.getTopCandidates(3);
        return ResponseEntity.ok(provisionalResults);
    }
}