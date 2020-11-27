package fr.paris8univ.iut.csid.csidwebrepositorybase.core;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao.GitRepositoryDao;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao.GitRepositoryEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao.GithubRepositoryDao;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao.GithubRepositoryDto;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.domain.GitRepository;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.domain.GithubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public GitRepositoryRepository( GitRepositoryDao gitRepositoryDao, GithubRepositoryDao githubRepositoryDao ) throws URISyntaxException {
        this.gitRepositoryDao=gitRepositoryDao;
        this.githubRepositoryDao = githubRepositoryDao;
    }

/*
    public GitRepository getRepository() throws URISyntaxException {
        GithubRepositoryDto tmp = this.githubRepositoryDao.getGitRepositoryFromInternetToDto();
        GitRepository gitRepo = new GitRepository();
        gitRepo.setName(tmp.getId());
        return gitRepo;
    }
*/

    public List<GitRepository> getRepositories() {
        List<GitRepositoryEntity> repositories = gitRepositoryDao.findAll();
        return repositories.stream().map(x -> new GitRepository(x.getName(), x.getOwner(), x.getIssues(), x.getForks())).collect(Collectors.toList());
    }

    public Optional<GitRepository> findById(String s) throws URISyntaxException {
        Optional<GitRepositoryEntity> byId = gitRepositoryDao.findById(s);
        GithubRepositoryDto gitRepositoryFromInternetToDto = githubRepositoryDao.getGitRepositoryFromInternetToDto(byId.get().getName(), byId.get().getOwner());
        GitRepository gitRepository = new GitRepository();
        gitRepository.setName(gitRepositoryFromInternetToDto.getId());
        gitRepository.setOwner(gitRepositoryFromInternetToDto.getOwner());
        return Optional.of(gitRepository);
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


    /*
    public <S extends GitRepositoryEntity> Optional<S> findOne(Example<S> example) { return this.gitRepositoryDao.findOne(example); }
    public <S extends GitRepositoryEntity> Page<S> findAll(Example<S> example, Pageable pageable) { return this.gitRepositoryDao.findAll(example, pageable); }
    public <S extends GitRepositoryEntity> long count(Example<S> example) { return this.gitRepositoryDao.count(example); }
    public <S extends GitRepositoryEntity> boolean exists(Example<S> example) { return this.gitRepositoryDao.exists(example); }
    public void delete(GitRepositoryEntity gitRepositoryEntity) { this.gitRepositoryDao.delete(gitRepositoryEntity);}
    public List<GitRepositoryEntity> findAllById(Iterable<String> iterable) { return this.gitRepositoryDao.findAllById(iterable); }
    */
}

