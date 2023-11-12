package senegal.election.sn.GestionElection.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import senegal.election.sn.GestionElection.entites.Voter;
import senegal.election.sn.GestionElection.services.VoterService;

@RestController
@RequestMapping("/api/voters")
public class VoterController {

    private final VoterService voterService;

    @Autowired
    public VoterController(VoterService voterService) {
        this.voterService = voterService;
    }

    @GetMapping("/{voterId}")
    public ResponseEntity<Voter> getVoterById(@PathVariable Long voterId) {
        Optional<Voter> voterOptional = voterService.getVoterById(voterId);

        return voterOptional.map(voter -> ResponseEntity.ok(voter)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Voter>> getAllVoters() {
        List<Voter> voters = voterService.getAllVoters();
        return ResponseEntity.ok(voters);
    }

    @PostMapping("/register")
    public ResponseEntity<Voter> addVoter(@RequestBody Voter voter) {
        Voter newVoter = voterService.addVoter(voter);
        return ResponseEntity.ok(newVoter);
    }

    @PutMapping("/update/{voterId}")
    public ResponseEntity<Voter> updateVoter(@PathVariable Long voterId, @RequestBody Voter voter) {
        voter.setId(voterId);
        try {
            Voter updatedVoter = voterService.updateVoter(voterId, voter);
            return ResponseEntity.ok(updatedVoter);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


    @DeleteMapping("/delete/{voterId}")
    public ResponseEntity<String> deleteVoter(@PathVariable Long voterId) {
        voterService.deleteVoter(voterId);
        return ResponseEntity.ok("Électeur supprimé avec succès");
    }
}