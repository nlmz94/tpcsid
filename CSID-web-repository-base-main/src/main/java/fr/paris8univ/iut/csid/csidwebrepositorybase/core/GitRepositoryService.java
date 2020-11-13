package fr.paris8univ.iut.csid.csidwebrepositorybase.core;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao.GitRepositoryDao;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.domain.GitRepository;
import org.springframework.stereotype.Service;

@Service
public class GitRepositoryService {

    private final GitRepositoryDao gitRepositoryDao;

    public GitRepositoryService(GitRepositoryDao gitRepositoryDao) {
        this.gitRepositoryDao = gitRepositoryDao;
    }
    public void createRepository(String name, String owner) {

    }
    public void updateRepository(String name, String owner) {

    }
    public void deleteRepository(GitRepository gitRepo) {

    }
}
