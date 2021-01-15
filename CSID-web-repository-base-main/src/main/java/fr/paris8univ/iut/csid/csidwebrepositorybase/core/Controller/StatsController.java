package fr.paris8univ.iut.csid.csidwebrepositorybase.core.Controller;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.stats.Stats;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.stats.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/repositories/{name}")
public class StatsController {

    private final StatsService statsService;

    @Autowired
    public StatsController(StatsService statsService) { this.statsService=statsService; }

    @GetMapping("/stats")
    public List<Stats> getRepositories(@PathVariable String name, @RequestParam String type) {
        return this.statsService.getStats(name, type);
    }
}