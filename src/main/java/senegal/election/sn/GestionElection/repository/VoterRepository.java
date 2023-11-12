package senegal.election.sn.GestionElection.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import senegal.election.sn.GestionElection.entites.Voter;

public interface VoterRepository extends JpaRepository<Voter, Long> {
    Optional<Voter> findByVoterId(String voterId);
    Optional<Voter> findByVoterIdAndPassword(String voterId, String password);
}