package fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.stats.StatsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatsDao extends JpaRepository<StatsEntity, String> {
    @Query(value = "SELECT * FROM statistiques AS stat WHERE stat.name= ?1 AND stat.entry_type= ?2 ORDER BY stat.value ASC ",nativeQuery=true)
    List<StatsEntity> findAllStatsWithSpecificNameAndTypeOrderByValue(String repositoryName, String statType);
}