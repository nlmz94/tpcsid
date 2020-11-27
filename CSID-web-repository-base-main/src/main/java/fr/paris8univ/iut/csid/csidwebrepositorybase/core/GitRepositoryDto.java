package fr.paris8univ.iut.csid.csidwebrepositorybase.core;

public class GitRepositoryDto {
    private String name;
    private String owner;
    private Integer issues;
    private Integer forks;

    public GitRepositoryDto() {}

    public GitRepositoryDto(String name, String owner, Integer issues, Integer forks) {
        this.name=name;
        this.owner=owner;
        this.issues=issues;
        this.forks=forks;
    }

    public Integer getIssues() { return issues; }
    public void setIssues(Integer issues) { this.issues = issues; }
    public Integer getForks() { return forks; }
    public void setForks(Integer forks) { this.forks = forks; }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getOwner() {
        return owner;
    }
    public void setOwner(String owner) {
        this.owner = owner;
    }
}
