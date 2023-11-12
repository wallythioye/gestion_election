package senegal.election.sn.GestionElection.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import senegal.election.sn.GestionElection.entites.Vote;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    Optional<Vote> findByVoterId(Long voterId);

    Optional<Vote> findByVoterIdAndCandidateId(Long id, Long id2);
}

