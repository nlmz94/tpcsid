package fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.Issue.GithubIssue;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.Issue.Issue;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.gitRepo.GithubRepositoryDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@Component
public class GithubRepositoryDao {

    private final RestTemplate restTemplate;
    GithubRepositoryDto githubRepositoryDto;

    public GithubRepositoryDao(RestTemplateBuilder restTemplateBuilder) {
        githubRepositoryDto = new GithubRepositoryDto();
        this.restTemplate = restTemplateBuilder.build();
    }

    public GithubRepositoryDto getGitRepositoryFromInternetToDto(String name, String owner) throws URISyntaxException {
        return restTemplate.getForEntity(new URI("https://api.github.com/repos/" + owner + "/" + name), GithubRepositoryDto.class).getBody();
    }

    public GithubIssue getReturnValFromGithub(Issue is) {
        String token="";
        String repoName = "tpspring";
        String repoOwner = "nlmz94";
        String url = "https://api.github.com/repos/" + repoOwner + "/" + repoName + "/issues";
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File("./src/main/resources/token.txt")));
            token = br.readLine();
        } catch (IOException e){ e.printStackTrace(); }
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity<Issue> entity = new HttpEntity<>(is, headers);
        return restTemplate.exchange(url, HttpMethod.POST, entity, GithubIssue.class).getBody();
    }
}