
package senegal.election.sn.GestionElection.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import senegal.election.sn.GestionElection.entites.Candidate;
import senegal.election.sn.GestionElection.repository.CandidateRepository;

@Service
public class CandidateService {

    private final CandidateRepository candidateRepository;

    @Autowired
    public CandidateService(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    public Candidate addCandidate(Candidate candidate) {
        Optional<Candidate> existingCandidate = candidateRepository.findByName(candidate.getName());

        if (existingCandidate.isPresent()) {
            throw new RuntimeException("Le candidat avec le nom " + candidate.getName() + " existe déjà.");
        } else {
            return candidateRepository.save(candidate);
        }
    }

    public List<Candidate> getAllCandidates() {
        return candidateRepository.findAll();
    }

    public Optional<Candidate> getCandidateById(Long candidateId) {
        return candidateRepository.findById(candidateId);
    }

    public void updateCandidate(Candidate candidate) {

        if (candidateRepository.existsById(candidate.getId())) {
            Candidate existingCandidate = candidateRepository.findById(candidate.getId()).get();

            existingCandidate.setCandidateId(candidate.getCandidateId());
            existingCandidate.setName(candidate.getName());
            existingCandidate.setParty(candidate.getParty());

            candidateRepository.save(existingCandidate);
        } else {
            throw new RuntimeException("Le candidat avec l'ID " + candidate.getId() + " n'a pas été trouvé.");
        }
    }
    

    public void deleteCandidate(Long candidateId) {
        candidateRepository.deleteById(candidateId);
    }
}
