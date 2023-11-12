package senegal.election.sn.GestionElection.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import senegal.election.sn.GestionElection.entites.Vote;
import senegal.election.sn.GestionElection.entites.Voter;
import senegal.election.sn.GestionElection.repository.VoteRepository;
import senegal.election.sn.GestionElection.repository.VoterRepository;

@Service
public class VoterService {

    private final VoterRepository voterRepository;
    private final VoteRepository voteRepository;

    @Autowired
    public VoterService(VoterRepository voterRepository, VoteRepository voteRepository) {
        this.voterRepository = voterRepository;
        this.voteRepository = voteRepository;
    }

    public Voter addVoter(Voter voter) {
        Optional<Voter> existingVoter = voterRepository.findByVoterId(voter.getVoterId());

        if (existingVoter.isPresent()) {
            throw new RuntimeException("L'électeur avec l'identifiant " + voter.getVoterId() + " existe déjà.");
        } else {
            return voterRepository.save(voter);
        }
    }

    public List<Voter> getAllVoters() {
        return voterRepository.findAll();
    }

    public Optional<Voter> getVoterById(Long voterId) {
        return voterRepository.findById(voterId);
    }

    public Voter updateVoter(Long voterId, Voter voter) {
        Optional<Voter> existingVoter = voterRepository.findById(voterId);

        if (existingVoter.isPresent()) {
            voter.setId(voterId);
            return voterRepository.save(voter);
        } else {
            throw new RuntimeException("L'électeur avec l'ID " + voterId + " n'a pas été trouvé.");
        }
    }

    public void deleteVoter(Long voterId) {
        voterRepository.deleteById(voterId);
    }

    public Optional<Vote> getVoteByVoterId(Long voterId) {
        return voteRepository.findByVoterId(voterId);
    }

}
