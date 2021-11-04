package com.harsid.ipldashboard;

import com.harsid.ipldashboard.model.Match;
import com.harsid.ipldashboard.model.Team;
import com.harsid.ipldashboard.repository.MatchRepository;
import com.harsid.ipldashboard.repository.TeamRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin
public class TeamController {

    private final TeamRepository teamRepository;
    private final MatchRepository matchRepository;

    public TeamController(TeamRepository teamRepository, MatchRepository matchRepository) {
        this.teamRepository = teamRepository;
        this.matchRepository = matchRepository;
    }

    @GetMapping("/teams/{teamName}")
    public Team getTeam(@PathVariable String teamName){

        Team team = teamRepository.findByTeamName(teamName);
        Pageable pageable = PageRequest.of(0,4);
        List<Match> matches = matchRepository.findLatestMatchesByTeam(teamName, 0, 4);
        team.setMatches(matches);
        return team;
    }

    @GetMapping("/teams/{teamName}/matches")
    public List<Match> getMatchesForTeam(@PathVariable String teamName, @RequestParam int year) {

        LocalDate startDate = LocalDate.of(year, 1,1);
        LocalDate endDate = LocalDate.of(year+1, 1,1);

        return matchRepository.getMatchesByTeamBetweenDates(teamName, startDate, endDate);
    }
}
