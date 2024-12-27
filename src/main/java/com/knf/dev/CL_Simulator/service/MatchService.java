package com.knf.dev.CL_Simulator.service;

import com.knf.dev.CL_Simulator.entity.Match;
import com.knf.dev.CL_Simulator.entity.Team;

import java.util.*;

import java.util.*;

public class MatchService {
    // ["Team1", "Team2", "Team3", "Team4", "Team5", "Team6", "Team7", "Team8", "Team9"],  # Koszyk 1
    // ["Team10", "Team11", "Team12", "Team13", "Team14", "Team15", "Team16", "Team17", "Team18"],  # Koszyk 2
    // ["Team19", "Team20", "Team21", "Team22", "Team23", "Team24", "Team25", "Team26", "Team27"],  # Koszyk 3
    // ["Team28", "Team29", "Team30", "Team31", "Team32", "Team33", "Team34", "Team35", "Team36"],  # Koszyk 4

    public ArrayList<Match> setLeaguePhase(List<Team> pot1, List<Team> pot2, List<Team> pot3, List<Team> pot4) {
        ArrayList<Match> matches = new ArrayList<>();
        List<List<Team>> pots = Arrays.asList(pot1, pot2, pot3, pot4);

        Map<Team, List<Team>> opponents = new HashMap<>();
        Map<Team, int[]> numberOfMatchesFromEachPot = new HashMap<>();
        for (List<Team> pot : pots) {
            for (Team team : pot) {
                opponents.put(team, new ArrayList<>());
                numberOfMatchesFromEachPot.put(team, new int[]{0,0,0,0});
            }
        }

        return matches;
    }

    private void createMatches(Team team, List<Team> opponents,
                               Map<Team, Set<Team>> playedMatches, Map<Team, Integer> teamMatchCount,
                               ArrayList<Match> matches, int matchesToCreate, Random random) {
        int attempts = 0;
        int maxAttempts = 50; // Maksymalna liczba prób na dobranie przeciwnika

        while (matchesToCreate > 0 && attempts < maxAttempts) {
            Team opponent = opponents.get(random.nextInt(opponents.size()));

            if (!opponent.equals(team) &&
                    !playedMatches.get(team).contains(opponent) &&
                    !team.getCountryCode().equals(opponent.getCountryCode()) &&
                    teamMatchCount.get(team) < 8 &&
                    teamMatchCount.get(opponent) < 8) {

                // Dodajemy mecz
                matches.add(new Match(team, opponent));
                playedMatches.get(team).add(opponent);
                playedMatches.get(opponent).add(team);
                teamMatchCount.put(team, teamMatchCount.get(team) + 1);
                teamMatchCount.put(opponent, teamMatchCount.get(opponent) + 1);
                matchesToCreate--;
            }
            attempts++;
        }

        if (attempts >= maxAttempts) {
            System.out.println("Uwaga: Nie udało się znaleźć przeciwnika dla drużyny: " + team.getTeamName());
        }
    }
}
