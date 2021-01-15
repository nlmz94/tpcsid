package fr.paris8univ.iut.csid.csidwebrepositorybase.core.gitRepo;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.Issue.GithubIssue;
import org.springframework.stereotype.Service;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@Service
public class GitRepositoryService {

    private final GitRepositoryRepository gitRepositoryRepository;

    public GitRepositoryService(GitRepositoryRepository gitRepositoryRepository) {
        this.gitRepositoryRepository = gitRepositoryRepository;
    }

    public Optional<GitRepository> getOneRepositoryGithub(String name) throws URISyntaxException {
        return this.gitRepositoryRepository.findByIdGithub(name);
    }

    public List<GitRepository> getRepositories() {
        return this.gitRepositoryRepository.getRepositories();
    }

    public void createOneRepository(GitRepository gitRepo) {
        this.gitRepositoryRepository.createOneRepository(gitRepo);
    }

    public void deleteOneRepository(String name) {
        this.gitRepositoryRepository.deleteOneRepository(name);
    }

    public void putOneRepository(String name, GitRepository gitRepo) {
        this.gitRepositoryRepository.putOneRepository(name, gitRepo);
    }

    public void patchOneRepository(String name, GitRepository gitRepo) {
        this.gitRepositoryRepository.patchOneRepository(name, gitRepo);
    }

    public GithubIssue postOneIssue(String title) {
        return gitRepositoryRepository.postOneIssue(title);
    }
}
