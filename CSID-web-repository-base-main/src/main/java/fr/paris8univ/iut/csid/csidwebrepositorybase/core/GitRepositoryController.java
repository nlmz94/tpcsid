package fr.paris8univ.iut.csid.csidwebrepositorybase.core;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.domain.GitRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/repositories")
public class GitRepositoryController {
    private final List<GitRepository> repositories =  new ArrayList<>();

    public void fillRepositories() {
        this.repositories.add(new GitRepository("Quentin", "Professor"));
        this.repositories.add(new GitRepository("Nael", "Student"));
    }

    @GetMapping
    public List<GitRepository> getRepositories() {
        return this.repositories;
    }

    @GetMapping("/{name}")
    public ResponseEntity<GitRepository> getOneRepository(@PathVariable String name) {
        return repositories.stream().filter( x -> x.getName().equalsIgnoreCase(name))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}