package fr.paris8univ.iut.csid.csidwebrepositorybase.core.stats;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatsService {

    private final StatsRepository statsRepository;

    private StatsService(StatsRepository statsRepository) {
        this.statsRepository = statsRepository;
    }

    public List<Stats> getStats(String repositoryName, String statsType) {
        return this.statsRepository.getStatsWithSpecificNameAndType(repositoryName, statsType);
    }
}