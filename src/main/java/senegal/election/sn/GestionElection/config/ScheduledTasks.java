package senegal.election.sn.GestionElection.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import senegal.election.sn.GestionElection.DTOClass.CandidateResult;
import senegal.election.sn.GestionElection.services.ElectionService;

@Configuration
@EnableScheduling
public class ScheduledTasks {

    @Autowired
    private ElectionService electionService; 

    @Scheduled(fixedRate = 60000) 
    public void displayTopCandidates() {
        List<CandidateResult> topCandidates = electionService.getTopCandidates(3);
        topCandidates.forEach(candidate -> System.out.println(candidate.getName() + ": " + candidate.getVoteCount()));
    }
}
