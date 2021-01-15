package fr.paris8univ.iut.csid.csidwebrepositorybase.core.gitRepo;

public class GitRepository {

    private String name;
    private String owner;
    private Integer issues;
    private Integer forks;

    public GitRepository() {}

    public GitRepository(String name, String owner, Integer issues, Integer forks) {
        this.name=name;
        this.owner=owner;
        this.issues=issues;
        this.forks =forks;
    }
    public String getName() {
        return this.name;
    }
    public String getOwner() {
        return this.owner;
    }
    public void setName(String name) { this.name = name; }
    public void setOwner(String owner) { this.owner = owner; }
    public Integer getIssues() { return issues; }
    public void setIssues(Integer issues) { this.issues = issues; }
    public Integer getForks() { return forks; }
    public void setForks(Integer forks) { this.forks = forks; }
}
