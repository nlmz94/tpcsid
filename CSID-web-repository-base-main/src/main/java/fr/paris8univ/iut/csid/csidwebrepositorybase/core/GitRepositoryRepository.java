package fr.paris8univ.iut.csid.csidwebrepositorybase.core;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao.GitRepositoryDao;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao.GitRepositoryEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao.GithubRepositoryDao;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao.GithubRepositoryDto;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.domain.GitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class GitRepositoryRepository {

    private final GitRepositoryDao gitRepositoryDao;
    private final GithubRepositoryDao githubRepositoryDao;

    @Autowired
    public GitRepositoryRepository(GitRepositoryDao gitRepositoryDao, GithubRepositoryDao githubRepositoryDao) {
        this.gitRepositoryDao = gitRepositoryDao;
        this.githubRepositoryDao = githubRepositoryDao;
    }

    public Optional<GitRepository> findByIdGithub(String s) throws URISyntaxException {
        if (gitRepositoryDao.findById(s).isPresent()) {
            Optional<GitRepositoryEntity> byId = gitRepositoryDao.findById(s);
            GithubRepositoryDto gitRepositoryFromInternetToDto = githubRepositoryDao.getGitRepositoryFromInternetToDto(byId.get().getName(), byId.get().getOwner());
            GitRepository gitRepository = new GitRepository(byId.get().getName(), byId.get().getOwner(), gitRepositoryFromInternetToDto.getOpen_issues(), gitRepositoryFromInternetToDto.getForks());
            return Optional.of(gitRepository);
        }
        else return Optional.empty();
    }



    public void createOneRepository(GitRepository gitRepo){
        this.save(new GitRepositoryEntity(gitRepo.getName(), gitRepo.getOwner(), gitRepo.getIssues(), gitRepo.getForks()));
    }

    public void deleteOneRepository(String name) {
        this.deleteById(name);
    }

    public void putOneRepository(String name, GitRepository gitRepo) {
        if (this.findById(name).isPresent())
            this.deleteOneRepository(name);
        this.createOneRepository(gitRepo);
    }

    public void patchOneRepository(String name, GitRepository gitRepo) {
        GitRepository newRepo = new GitRepository();
        GitRepositoryEntity originalRepoEntity = this.getOne(name);

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

    public Optional<GitRepository> findById(String name) {
        GitRepositoryEntity gitRepositoryEntity;
        if (this.gitRepositoryDao.findById(name).isPresent()) {
            gitRepositoryEntity = this.gitRepositoryDao.findById(name).get();
            return Optional.of(new GitRepository(gitRepositoryEntity.getName(), gitRepositoryEntity.getOwner(), gitRepositoryEntity.getIssues(), gitRepositoryEntity.getForks()));
        }
        else return Optional.empty();
    }

    public List<GitRepository> getRepositories() {
        List<GitRepositoryEntity> repositories = gitRepositoryDao.findAll();
        return repositories.stream().map(x -> new GitRepository(x.getName(), x.getOwner(), x.getIssues(), x.getForks())).collect(Collectors.toList());
    }

    public <S extends GitRepositoryEntity> S save(S s) {
        return this.gitRepositoryDao.save(s);
    }

    public List<GitRepositoryEntity> findAll() {
        return this.gitRepositoryDao.findAll();
    }

    public void deleteById(String s) {
        this.gitRepositoryDao.deleteById(s);
    }

    public GitRepositoryEntity getOne(String s) {
        return this.gitRepositoryDao.getOne(s);
    }
}

