package fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.Issue.GithubIssue;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.Issue.Issue;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.gitRepo.GithubRepositoryDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.net.URI;
import java.net.URISyntaxException;

@Component
public class GithubRepositoryDao {

    private final RestTemplate restTemplate;
    private @Value("${githubToken}") String token;

    public GithubRepositoryDao(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public GithubRepositoryDto getGitRepositoryFromInternetToDto(String name, String owner) throws URISyntaxException {
        return restTemplate.getForEntity(new URI("https://api.github.com/repos/" + owner + "/" + name), GithubRepositoryDto.class).getBody();
    }

    public GithubIssue getReturnValFromGithub(Issue issue, String owner, String repository) {
        String url = "https://api.github.com/repos/" + owner + "/" + repository + "/issues";
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity<Issue> entity = new HttpEntity<>(issue, headers);
        return restTemplate.exchange(url, HttpMethod.POST, entity, GithubIssue.class).getBody();
    }
}