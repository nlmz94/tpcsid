package fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.gitRepo.GitRepositoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GitRepositoryDao extends JpaRepository<GitRepositoryEntity, String> { }
