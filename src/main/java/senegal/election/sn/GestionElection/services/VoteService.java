package senegal.election.sn.GestionElection.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import senegal.election.sn.GestionElection.entites.Vote;
import senegal.election.sn.GestionElection.repository.VoteRepository;

@Service
public class VoteService {

    private final VoteRepository voteRepository;

    @Autowired
    public VoteService(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    public String castVote(Vote vote) {
        if (vote.getVoter() == null || vote.getVoter().getId() == null) {
            throw new IllegalArgumentException("Le vote doit avoir un électeur associé avec un ID valide.");
        }

        if (vote.getCandidate() == null || vote.getCandidate().getId() == null) {
            throw new IllegalArgumentException("Le vote doit avoir un candidat associé avec un ID valide.");
        }

        Optional<Vote> existingVote = voteRepository.findByVoterIdAndCandidateId(
                vote.getVoter().getId(),
                vote.getCandidate().getId()
        );

        if (existingVote.isPresent()) {
            return "L'électeur avec l'ID " + vote.getVoter().getId() +
                    " a déjà voté pour le candidat avec l'ID " + vote.getCandidate().getId();
        } else {
            voteRepository.save(vote);
            return "Vote enregistré avec succès";
        }
    }

    public Optional<Vote> getVoteByVoterId(Long voterId) {
        return voteRepository.findByVoterId(voterId);
    }
}
