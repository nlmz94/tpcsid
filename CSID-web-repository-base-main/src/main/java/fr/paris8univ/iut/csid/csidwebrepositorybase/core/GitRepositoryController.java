package fr.paris8univ.iut.csid.csidwebrepositorybase.core;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.domain.GitRepository;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value="/repositories",  method=RequestMethod.GET)
public class GitRepositoryController { //private final GitRepositoryService repositoryService;
    private final List<GitRepository> repositories =  new ArrayList<>();
    public GitRepositoryController(GitRepositoryService repoServ) { //this.repositoryService=repoServ;
        fillRepositories();
    }

    public void fillRepositories() {
        this.repositories.add(new GitRepository("Quentin", "Professor"));
        this.repositories.add(new GitRepository("Nael", "Student"));
    }

    @GetMapping
    public List<GitRepository> getRepositories() { return this.repositories; }

    @GetMapping("/{name}")
    public Optional<GitRepository> getOneRepository(@PathVariable String name) {
        return repositories.stream().filter( x -> x.getName().equalsIgnoreCase(name)).findFirst();
    }
}