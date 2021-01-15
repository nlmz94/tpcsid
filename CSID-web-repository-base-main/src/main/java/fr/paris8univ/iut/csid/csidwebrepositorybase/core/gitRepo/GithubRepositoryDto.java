package fr.paris8univ.iut.csid.csidwebrepositorybase.core.gitRepo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GithubRepositoryDto {
    private String name;
    private String login;
    private Integer open_issues;
    private Integer forks;

    public GithubRepositoryDto() {}

    public Integer getOpen_issues() {
        return open_issues;
    }
    public void setOpen_issues(Integer open_issues) {
        this.open_issues = open_issues;
    }
    public Integer getForks() {
        return forks;
    }
    public void setForks(Integer forks) {
        this.forks = forks;
    }
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
