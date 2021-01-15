package fr.paris8univ.iut.csid.csidwebrepositorybase.core.gitRepo;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.Issue.GithubIssue;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.Issue.Issue;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao.GitRepositoryDao;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao.GithubRepositoryDao;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.stats.Stats;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.stats.StatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class GitRepositoryRepository {

    private final GitRepositoryDao gitRepositoryDao;
    private final GithubRepositoryDao githubRepositoryDao;
    private final StatsRepository statsRepository;

    @Autowired
    public GitRepositoryRepository(GitRepositoryDao gitRepositoryDao, GithubRepositoryDao githubRepositoryDao, StatsRepository statsRepository) {
        this.gitRepositoryDao = gitRepositoryDao;
        this.githubRepositoryDao = githubRepositoryDao;
        this.statsRepository = statsRepository;
    }

    public Optional<GitRepository> findByIdGithub(String s) throws URISyntaxException {

        if (gitRepositoryDao.findById(s).isPresent()) {
            GitRepositoryEntity byId = gitRepositoryDao.findById(s).get();
            GitRepository gitRepository = new GitRepository(byId.getName(), byId.getOwner(), byId.getIssues(), byId.getForks());
            GithubRepositoryDto gitRepositoryFromInternetToDto = githubRepositoryDao.getGitRepositoryFromInternetToDto(gitRepository.getName(), gitRepository.getOwner());
            //database update
            //if (compareDates(stringToLocalDateTime(gitRepository.get)))
            gitRepository.setForks(gitRepositoryFromInternetToDto.getForks());
            gitRepository.setIssues(gitRepositoryFromInternetToDto.getOpen_issues());
            //save stats in database.
            this.statsRepository.createOneStats(new Stats(gitRepository.getName(), "issues", getDate(), gitRepository.getIssues()));
            this.statsRepository.createOneStats(new Stats(gitRepository.getName(), "forks", getDate(), gitRepository.getForks()));
            //end save.
            patchOneRepository(gitRepository.getName(), gitRepository);
            //final patch
            return Optional.of(gitRepository);
        }
        else return Optional.empty();
    }

    public void createOneRepository(GitRepository gitRepo) {
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

    public GithubIssue postOneIssue(String title) {
        Issue is = new Issue();
        is.setTitle(title);
        is.setBody("body69420");
        return githubRepositoryDao.getReturnValFromGithub(is);
    }

    public String getDate(){ return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now()); }
    public LocalDateTime stringToLocalDateTime(String stringDate){ return LocalDateTime.parse(stringDate, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")); }
    public int compareDates(LocalDateTime then, LocalDateTime now){ return Math.toIntExact(ChronoUnit.MINUTES.between(then,now)); }
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

