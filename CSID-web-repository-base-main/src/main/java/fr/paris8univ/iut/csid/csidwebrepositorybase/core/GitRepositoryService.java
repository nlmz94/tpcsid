package fr.paris8univ.iut.csid.csidwebrepositorybase.core;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao.GitRepositoryEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao.GithubRepositoryDto;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.domain.GitRepository;
import org.springframework.stereotype.Service;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GitRepositoryService {

    private final GitRepositoryRepository gitRepositoryRepository;

    public GitRepositoryService(GitRepositoryRepository gitRepositoryRepository) {
        this.gitRepositoryRepository = gitRepositoryRepository;
    }

    public Optional<GitRepository> getOneRepository(String name) throws URISyntaxException {
        return gitRepositoryRepository.findById(name);
    }


    public List<GitRepository> getRepositories() {
        List<GitRepositoryEntity> repositories = gitRepositoryRepository.findAll();
        return repositories.stream().map(x -> new GitRepository(x.getName(), x.getOwner(), x.getIssues(), x.getForks())).collect(Collectors.toList());
    }

    public void createOneRepository(GitRepository gitRepo){
        gitRepositoryRepository.save(new GitRepositoryEntity(gitRepo.getName(), gitRepo.getOwner(), gitRepo.getIssues(), gitRepo.getForks()));
    }

    public void deleteOneRepository(String name) {
        gitRepositoryRepository.deleteById(name);
    }

    public void putOneRepository(String name, GitRepository gitRepo) throws URISyntaxException {
        if (gitRepositoryRepository.findById(name).isPresent())
            this.deleteOneRepository(name);
        this.createOneRepository(gitRepo);
    }

    public void patchOneRepository(String name, GitRepository gitRepo) throws URISyntaxException {
        GitRepository newRepo = new GitRepository("placeholder", "placeholder", 270, 270);
        GitRepositoryEntity originalRepoEntity = gitRepositoryRepository.getOne(name);

        newRepo.setName(originalRepoEntity.getName());
        newRepo.setOwner(originalRepoEntity.getOwner());
        newRepo.setIssues(originalRepoEntity.getIssues());
        newRepo.setForks(originalRepoEntity.getForks());

            if (gitRepo.getOwner() != null)
                newRepo.setOwner(gitRepo.getOwner());

            if(gitRepo.getIssues() != null)
                newRepo.setIssues(gitRepo.getIssues());

            if(gitRepo.getForks() != null)
                newRepo.setForks(gitRepo.getForks());

        this.putOneRepository(name, newRepo);
    }
}
