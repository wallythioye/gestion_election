package senegal.election.sn.GestionElection.DTOClass;

public class CandidateResult {
    private Long candidateId;
    private String name;
    private String party;
    private Long voteCount;

    public Long getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(Long candidateId) {
        this.candidateId = candidateId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public Long getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Long voteCount) {
        this.voteCount = voteCount;
    }

    public CandidateResult(Long candidateId, String name, String party, Long voteCount) {
        this.candidateId = candidateId;
        this.name = name;
        this.party = party;
        this.voteCount = voteCount;
    }
}
