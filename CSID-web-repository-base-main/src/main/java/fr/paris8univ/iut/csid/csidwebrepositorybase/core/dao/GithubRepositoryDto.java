package fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GithubRepositoryDto {
    private String id;
    private String owner;

    public GithubRepositoryDto() {}

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /*
    private String name;
    private String owner;
    private Integer issues;
    private Integer forks;

    public GithubRepositoryDto() {}

    public GithubRepositoryDto(String name, String owner, Integer issues, Integer forks) {
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
    */
}
