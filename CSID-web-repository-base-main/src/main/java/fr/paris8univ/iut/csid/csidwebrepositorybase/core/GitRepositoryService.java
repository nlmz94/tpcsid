package fr.paris8univ.iut.csid.csidwebrepositorybase.core;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao.GitRepositoryDao;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao.GitRepositoryEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.domain.GitRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GitRepositoryService {

    private final GitRepositoryDao gitRepositoryDao;

    public GitRepositoryService(GitRepositoryDao gitRepositoryDao) {
        this.gitRepositoryDao = gitRepositoryDao;
    }

    public List<GitRepository> getRepositories() {
        List<GitRepositoryEntity> repositories = gitRepositoryDao.findAll();
        return repositories.stream().map(x -> new GitRepository(x.getName(), x.getOwner(), x.getIssues(), x.getForks())).collect(Collectors.toList());
    }

    public Optional<GitRepository> getOneRepository(String name){
        return gitRepositoryDao.findById(name).map(x-> new GitRepository(x.getName(),x.getOwner(), x.getIssues(), x.getForks()));
    }

    public void createOneRepository(GitRepository gitRepo){
        gitRepositoryDao.save(new GitRepositoryEntity(gitRepo.getName(), gitRepo.getOwner(), gitRepo.getIssues(), gitRepo.getForks()));
    }

    public void deleteOneRepository(String name) {
        gitRepositoryDao.deleteById(name);
    }

    public void putOneRepository(String name, GitRepository gitRepo) {
        if (gitRepositoryDao.findById(name).isPresent())
            this.deleteOneRepository(name);
        this.createOneRepository(gitRepo);
    }

    public void patchOneRepository(String name, GitRepository gitRepo) {
        GitRepository newRepo = new GitRepository("placeholder", "placeholder", 270, 270);
        GitRepositoryEntity originalRepoEntity = gitRepositoryDao.getOne(name);

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
