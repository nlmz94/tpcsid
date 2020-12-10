package fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
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
}