package fr.paris8univ.iut.csid.csidwebrepositorybase.core.Controller;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.Issue.GithubIssue;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.gitRepo.GitRepository;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.gitRepo.GitRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/* psql -h localhost -d springdb -U root -p 5432 */

@RestController
@RequestMapping(value = "/repositories")
public class GitRepositoryController {

    private final GitRepositoryService repositoryService;

    @Autowired
    public GitRepositoryController(GitRepositoryService repoServ) {
        this.repositoryService = repoServ;
    }

    @GetMapping
    public List<GitRepository> getRepositories() {
        return this.repositoryService.getRepositories();
    }

    @GetMapping("/{name}")
    public GitRepository getOneRepository(@PathVariable String name) throws URISyntaxException {
        return this.repositoryService.getOneRepositoryGithub(name).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()).getBody();
    }


    @PostMapping
    public ResponseEntity<GitRepository> createOneRepository(@RequestBody GitRepository gitRepo) throws URISyntaxException {
        repositoryService.createOneRepository(gitRepo);
        URI location = new URI("/repositories/add/" + gitRepo.getName());
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<GitRepository> deleteOneRepository(@PathVariable String name) throws URISyntaxException {
        repositoryService.deleteOneRepository(name);
        URI location = new URI("/repositories/delete/" + name);
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{name}")
    public ResponseEntity<GitRepository> putOneRepository(@PathVariable String name, @RequestBody GitRepository gitRepo) throws URISyntaxException {
        repositoryService.putOneRepository(name, gitRepo);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{name}")
    public ResponseEntity<GitRepository> patchOneRepository(@PathVariable String name, @RequestBody GitRepository gitRepo) throws URISyntaxException {
        repositoryService.patchOneRepository(name, gitRepo);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/postIssue/{title}")
    public void postOneIssue(@PathVariable String title) {
        GithubIssue githubIssue = repositoryService.postOneIssue(title);
        System.out.println(githubIssue.getId());
        System.out.println(githubIssue.getBody());
        System.out.println(githubIssue.getHtml_url());
        System.out.println((githubIssue.getNumber()));
        System.out.println((githubIssue.getState()));
    }
}