package fr.paris8univ.iut.csid.csidwebrepositorybase.core.gitRepo;
import javax.persistence.*;

@Entity
@Table(name = "repository")
public class GitRepositoryEntity {

    @Id
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "owner")
    private String owner;

    @Column(name = "issues")
    private Integer issues;

    @Column(name = "forks")
    private Integer forks;

    public GitRepositoryEntity(){ }

    public GitRepositoryEntity(String name, String owner, Integer issues, Integer forks) {
        this.name = name;
        this.owner = owner;
        this.issues=issues;
        this.forks=forks;
    }

    public Integer getIssues() { return issues; }
    public void setIssues( Integer issues) { this.issues = issues; }
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
