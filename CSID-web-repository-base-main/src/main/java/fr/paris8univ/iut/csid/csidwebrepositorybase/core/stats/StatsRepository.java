package fr.paris8univ.iut.csid.csidwebrepositorybase.core.stats;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao.StatsDao;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class StatsRepository {

    private final StatsDao statsDao;

    public StatsRepository(StatsDao statsDao) {
        this.statsDao = statsDao;
    }

    public List<Stats> getStatsWithSpecificNameAndType(String repositoryName, String statsType) {
        List<StatsEntity> statsEntities = statsDao.findAll();
        List<Stats> list = statsEntities.stream().map(x -> new Stats(x.getRepository_name(), x.getEntry_type(), x.getDate_time(), x.getValue())).collect(Collectors.toList());
        list.removeIf(stat -> !(stat.getRepository_name().equals(repositoryName)) || !(stat.getEntry_type().equals(statsType)));
        return list;
        /*
        List<StatsEntity> statsEntities = statsDao.findAllStatsWithSpecificNameAndTypeOrderByValue(repositoryName, statsType);
        return statsEntities.stream().map(x -> new Stats(x.getRepository_name(), x.getEntry_type(), x.getDate_time(), x.getValue())).collect(Collectors.toList()); */
    }


    public void createOneStats(Stats stats){
        StatsEntity s = new StatsEntity(stats.getRepository_name(), stats.getEntry_type(), stats.getDate_time(), stats.getValue());
        this.save(s);
    }
    public List<Stats> getStats (String repositoryName, String statsType) {
        List<StatsEntity> statsEntities = statsDao.findAll();
        return statsEntities.stream().map(x -> new Stats(x.getRepository_name(), x.getEntry_type(), x.getDate_time(), x.getValue())).collect(Collectors.toList());
    }
    public <S extends StatsEntity> S save(S s) { return this.statsDao.save(s); }
}