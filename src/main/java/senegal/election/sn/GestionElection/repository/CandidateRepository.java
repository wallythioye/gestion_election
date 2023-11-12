package senegal.election.sn.GestionElection.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import senegal.election.sn.GestionElection.entites.Candidate;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {
    Optional<Candidate> findByCandidateId(String candidateId);
    Optional<Candidate> findByName(String name);
    boolean existsByName(String name);
}
