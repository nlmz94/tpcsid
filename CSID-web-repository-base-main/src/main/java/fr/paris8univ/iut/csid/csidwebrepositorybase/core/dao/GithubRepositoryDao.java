package fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@Component
public class GithubRepositoryDao {

    private final RestTemplate restTemplate;
    GithubRepositoryDto githubRepositoryDto;
    URI uri;

    public GithubRepositoryDao() throws URISyntaxException {
        githubRepositoryDto = new GithubRepositoryDto();
        this.restTemplate=new RestTemplate();

    }

    public GithubRepositoryDto getGitRepositoryFromInternetToDto(String name, String owner) throws URISyntaxException {
        return restTemplate.getForEntity("https://api.github.com/repos/{owner}/{name}", GithubRepositoryDto.class, owner, name).getBody();
    }

    /*
    public String getLastUpdateTime() {
        return restTemplate.getForEntity(uri, GithubRepositoryDto.class).getBody().getUpdateTime();
    }
    */
}
