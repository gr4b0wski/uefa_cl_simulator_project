package com.knf.dev.CL_Simulator.service;

import com.knf.dev.CL_Simulator.entity.Match;
import com.knf.dev.CL_Simulator.entity.Team;
import com.knf.dev.CL_Simulator.entity.TeamStandings;

import java.util.*;

public class MatchService {
    // ["Team1", "Team2", "Team3", "Team4", "Team5", "Team6", "Team7", "Team8", "Team9"],  # Koszyk 1
    // ["Team10", "Team11", "Team12", "Team13", "Team14", "Team15", "Team16", "Team17", "Team18"],  # Koszyk 2
    // ["Team19", "Team20", "Team21", "Team22", "Team23", "Team24", "Team25", "Team26", "Team27"],  # Koszyk 3
    // ["Team28", "Team29", "Team30", "Team31", "Team32", "Team33", "Team34", "Team35", "Team36"],  # Koszyk 4

    public ArrayList<Match> setLeaguePhase(List<Team> pot1, List<Team> pot2, List<Team> pot3, List<Team> pot4) {
        ArrayList<Match> matches = new ArrayList<>();
        Collections.shuffle(pot1); // mieszanie koszyków
        Collections.shuffle(pot2);
        Collections.shuffle(pot3);
        Collections.shuffle(pot4);


        // 1 kolejka
        matches.add(new Match(pot3.get(7), pot4.get(3)));
        matches.add(new Match(pot2.get(3), pot3.get(2)));
        matches.add(new Match(pot2.get(8), pot1.get(4)));
        matches.add(new Match(pot1.get(2), pot3.get(3)));
        matches.add(new Match(pot1.get(0), pot4.get(6)));
        matches.add(new Match(pot3.get(1), pot3.get(5)));
        matches.add(new Match(pot4.get(2), pot3.get(4)));
        matches.add(new Match(pot4.get(4), pot2.get(7)));
        matches.add(new Match(pot3.get(8), pot4.get(0)));
        matches.add(new Match(pot2.get(6), pot1.get(6)));
        matches.add(new Match(pot1.get(1), pot1.get(5)));
        matches.add(new Match(pot1.get(3), pot4.get(5)));
        matches.add(new Match(pot3.get(0), pot2.get(0)));
        matches.add(new Match(pot3.get(6), pot2.get(4)));
        matches.add(new Match(pot4.get(1), pot1.get(8)));
        matches.add(new Match(pot2.get(2), pot2.get(5)));
        matches.add(new Match(pot2.get(1), pot1.get(7)));
        matches.add(new Match(pot4.get(8), pot4.get(7)));
        // 2 kolejka
        matches.add(new Match(pot3.get(4), pot4.get(8)));
        matches.add(new Match(pot4.get(6), pot4.get(2)));
        matches.add(new Match(pot2.get(5), pot1.get(3)));
        matches.add(new Match(pot2.get(0), pot2.get(8)));
        matches.add(new Match(pot1.get(6), pot3.get(8)));
        matches.add(new Match(pot1.get(8), pot3.get(7)));
        matches.add(new Match(pot1.get(5), pot3.get(6)));
        matches.add(new Match(pot3.get(2), pot3.get(1)));
        matches.add(new Match(pot4.get(0), pot1.get(1)));
        matches.add(new Match(pot2.get(7), pot2.get(2)));
        matches.add(new Match(pot4.get(5), pot3.get(0)));
        matches.add(new Match(pot4.get(3), pot1.get(2)));
        matches.add(new Match(pot3.get(3), pot4.get(1)));
        matches.add(new Match(pot1.get(4), pot4.get(4)));
        matches.add(new Match(pot3.get(5), pot1.get(0)));
        matches.add(new Match(pot1.get(7), pot2.get(3)));
        matches.add(new Match(pot4.get(7), pot2.get(6)));
        matches.add(new Match(pot2.get(4), pot2.get(1)));
        // kolejka 3
        matches.add(new Match(pot2.get(8), pot2.get(6)));
        matches.add(new Match(pot4.get(1), pot3.get(6)));
        matches.add(new Match(pot2.get(5), pot2.get(7)));
        matches.add(new Match(pot4.get(3), pot4.get(4)));
        matches.add(new Match(pot4.get(5), pot4.get(0)));
        matches.add(new Match(pot2.get(3), pot4.get(6)));
        matches.add(new Match(pot1.get(3), pot3.get(2)));
        matches.add(new Match(pot1.get(0), pot1.get(6)));
        matches.add(new Match(pot4.get(7), pot3.get(1)));
        matches.add(new Match(pot2.get(2), pot3.get(8)));
        matches.add(new Match(pot4.get(8), pot2.get(0)));
        matches.add(new Match(pot2.get(1), pot3.get(5)));
        matches.add(new Match(pot3.get(7), pot1.get(5)));
        matches.add(new Match(pot1.get(8), pot1.get(2)));
        matches.add(new Match(pot3.get(4), pot3.get(3)));
        matches.add(new Match(pot1.get(1), pot4.get(2)));
        matches.add(new Match(pot1.get(7), pot1.get(4)));
        matches.add(new Match(pot2.get(4), pot3.get(0)));
        // kolejka 4
        matches.add(new Match(pot3.get(2), pot4.get(5)));
        matches.add(new Match(pot4.get(0), pot3.get(3)));
        matches.add(new Match(pot4.get(4), pot4.get(1)));
        matches.add(new Match(pot1.get(6), pot4.get(7)));
        matches.add(new Match(pot3.get(8), pot1.get(7)));
        matches.add(new Match(pot1.get(4), pot2.get(0)));
        matches.add(new Match(pot3.get(5), pot2.get(3)));
        matches.add(new Match(pot1.get(0), pot2.get(8)));
        matches.add(new Match(pot3.get(1), pot1.get(1)));
        matches.add(new Match(pot2.get(6), pot4.get(3)));
        matches.add(new Match(pot2.get(7), pot3.get(7)));
        matches.add(new Match(pot4.get(2), pot4.get(8)));
        matches.add(new Match(pot1.get(2), pot2.get(4)));
        matches.add(new Match(pot1.get(5), pot2.get(5)));
        matches.add(new Match(pot3.get(0), pot3.get(4)));
        matches.add(new Match(pot3.get(6), pot1.get(8)));
        matches.add(new Match(pot1.get(3), pot2.get(1)));
        matches.add(new Match(pot4.get(6), pot2.get(2)));
        // kolejka 5
        matches.add(new Match(pot4.get(2), pot2.get(1)));
        matches.add(new Match(pot4.get(0), pot2.get(8)));
        matches.add(new Match(pot2.get(0), pot3.get(4)));
        matches.add(new Match(pot3.get(7), pot2.get(2)));
        matches.add(new Match(pot1.get(8), pot4.get(8)));
        matches.add(new Match(pot1.get(2), pot1.get(3)));
        matches.add(new Match(pot1.get(5), pot1.get(7)));
        matches.add(new Match(pot1.get(1), pot3.get(0)));
        matches.add(new Match(pot3.get(1), pot2.get(5)));
        matches.add(new Match(pot3.get(6), pot4.get(6)));
        matches.add(new Match(pot4.get(7), pot4.get(5)));
        matches.add(new Match(pot4.get(1), pot2.get(4)));
        matches.add(new Match(pot4.get(3), pot2.get(3)));
        matches.add(new Match(pot4.get(4), pot3.get(5)));
        matches.add(new Match(pot3.get(8), pot2.get(6)));
        matches.add(new Match(pot3.get(3), pot1.get(6)));
        matches.add(new Match(pot1.get(4), pot1.get(0)));
        matches.add(new Match(pot3.get(2), pot2.get(7)));
        // kolejka 6
        matches.add(new Match(pot4.get(5), pot1.get(4)));
        matches.add(new Match(pot3.get(3), pot3.get(8)));
        matches.add(new Match(pot2.get(2), pot1.get(0)));
        matches.add(new Match(pot2.get(0), pot1.get(5)));
        matches.add(new Match(pot2.get(6), pot3.get(1)));
        matches.add(new Match(pot3.get(4), pot1.get(3)));
        matches.add(new Match(pot2.get(7), pot1.get(2)));
        matches.add(new Match(pot1.get(7), pot4.get(3)));
        matches.add(new Match(pot4.get(8), pot3.get(2)));
        matches.add(new Match(pot2.get(1), pot4.get(0)));
        matches.add(new Match(pot3.get(5), pot4.get(7)));
        matches.add(new Match(pot2.get(8), pot3.get(6)));
        matches.add(new Match(pot2.get(5), pot4.get(1)));
        matches.add(new Match(pot1.get(6), pot1.get(8)));
        matches.add(new Match(pot3.get(0), pot4.get(2)));
        matches.add(new Match(pot2.get(3), pot1.get(1)));
        matches.add(new Match(pot2.get(4), pot4.get(4)));
        matches.add(new Match(pot4.get(6), pot3.get(7)));
        // 7 kolejka
        matches.add(new Match(pot4.get(1), pot4.get(3)));
        matches.add(new Match(pot2.get(2), pot4.get(7)));
        matches.add(new Match(pot2.get(1), pot2.get(0)));
        matches.add(new Match(pot4.get(4), pot1.get(6)));
        matches.add(new Match(pot2.get(6), pot2.get(3)));
        matches.add(new Match(pot3.get(6), pot3.get(2)));
        matches.add(new Match(pot1.get(4), pot3.get(5)));
        matches.add(new Match(pot4.get(0), pot4.get(6)));
        matches.add(new Match(pot2.get(4), pot1.get(8)));
        matches.add(new Match(pot2.get(7), pot4.get(8)));
        matches.add(new Match(pot1.get(7), pot3.get(1)));
        matches.add(new Match(pot2.get(8), pot4.get(5)));
        matches.add(new Match(pot4.get(2), pot1.get(5)));
        matches.add(new Match(pot2.get(5), pot3.get(3)));
        matches.add(new Match(pot3.get(8), pot3.get(7)));
        matches.add(new Match(pot3.get(0), pot1.get(2)));
        matches.add(new Match(pot1.get(3), pot1.get(1)));
        matches.add(new Match(pot1.get(0), pot3.get(4)));
        // kolejka 8
        matches.add(new Match(pot4.get(3), pot3.get(8)));
        matches.add(new Match(pot2.get(0), pot4.get(2)));
        matches.add(new Match(pot1.get(6), pot2.get(7)));
        matches.add(new Match(pot3.get(7), pot3.get(6)));
        matches.add(new Match(pot1.get(8), pot2.get(2)));
        matches.add(new Match(pot1.get(2), pot4.get(0)));
        matches.add(new Match(pot1.get(5), pot4.get(1)));
        matches.add(new Match(pot3.get(4), pot2.get(1)));
        matches.add(new Match(pot4.get(5), pot2.get(5)));
        matches.add(new Match(pot3.get(3), pot2.get(8)));
        matches.add(new Match(pot2.get(3), pot2.get(4)));
        matches.add(new Match(pot3.get(5), pot3.get(0)));
        matches.add(new Match(pot1.get(1), pot2.get(6)));
        matches.add(new Match(pot3.get(2), pot1.get(4)));
        matches.add(new Match(pot4.get(7), pot1.get(7)));
        matches.add(new Match(pot3.get(1), pot4.get(4)));
        matches.add(new Match(pot4.get(8), pot1.get(0)));
        matches.add(new Match(pot4.get(6), pot1.get(3)));
        return matches;
    }

    public void simulateMatch(Match match) {
        int elo1 = match.getTeam1().getEloPoints();
        int elo2 = match.getTeam2().getEloPoints();

        // Oblicz różnicę rankingów ELO
        int eloDifference = elo1 - elo2;

        // Dodaj przewagę gospodarza (np. 30 punktów ELO dla gospodarza)
        final int HOME_ADVANTAGE = 50;
        int adjustedEloDifference = eloDifference + HOME_ADVANTAGE;

        // Przekształć różnicę na szansę na zwycięstwo (sigmoid)
        double probability1 = 1.0 / (1.0 + Math.pow(10, -adjustedEloDifference / 400.0));
        double probability2 = 1.0 - probability1;

        // Generowanie losowej liczby bramek w zależności od szans
        Random random = new Random();
        match.setGoals1(generateGoals(probability1, random));
        match.setGoals2(generateGoals(probability2, random));
        match.setPlayed(true);
    }

    private int generateGoals(double probability, Random random) {
        // Zmniejszenie liczby bramek i większy wpływ prawdopodobieństwa
        double baseAvgGoals = 0.6; // Bazowa liczba bramek (niższa niż poprzednio)
        double dynamicFactor = 1.0 * probability; // Mniejszy wpływ prawdopodobieństwa
        double avgGoals = Math.min(baseAvgGoals + dynamicFactor, 2.5); // Ograniczenie średniej liczby bramek

        double lambda = avgGoals; // Lambda do rozkładu Poissona
        return simulatePoisson(lambda, random);
    }

    private int simulatePoisson(double lambda, Random random) {
        // Symulacja liczby bramek za pomocą rozkładu Poissona
        double l = Math.exp(-lambda);
        int k = 0;
        double p = 1.0;

        do {
            k++;
            p *= random.nextDouble();
        } while (p > l);

        return Math.min(k - 1, 4); // Ogranicz wynik do maksymalnie 4 bramek
    }




    public List<TeamStandings> calculateLeagueTable(List<Match> matches) {
        Map<String, TeamStandings> standingsMap = new HashMap<>();

        for (Match match : matches) {
            // Pomijaj mecze, które nie zostały rozegrane
            if (!match.isPlayed()) {
                continue;
            }

            // Przykład: Match ma team1, team2, goals1, goals2
            Team team1 = match.getTeam1();
            Team team2 = match.getTeam2();
            int goals1 = match.getGoals1();
            int goals2 = match.getGoals2();

            // Zaktualizuj dane dla drużyny 1
            standingsMap.putIfAbsent(team1.getTeamName(), new TeamStandings(team1.getCountryCode(), team1.getTeamName()));
            TeamStandings team1Standings = standingsMap.get(team1.getTeamName());
            team1Standings.setPlayed(team1Standings.getPlayed() + 1);
            team1Standings.setGoalsFor(team1Standings.getGoalsFor() + goals1);
            team1Standings.setGoalsAgainst(team1Standings.getGoalsAgainst() + goals2);
            team1Standings.setGoalDifference(team1Standings.getGoalsFor() - team1Standings.getGoalsAgainst());
            if (goals1 > goals2) {
                team1Standings.setPoints(team1Standings.getPoints() + 3);
                team1Standings.setWon(team1Standings.getWon() + 1);
            } else if (goals1 == goals2) {
                team1Standings.setPoints(team1Standings.getPoints() + 1);
                team1Standings.setDrawn(team1Standings.getDrawn() + 1);
            } else {
                team1Standings.setLost(team1Standings.getLost() + 1);
            }

            // Zaktualizuj dane dla drużyny 2
            standingsMap.putIfAbsent(team2.getTeamName(), new TeamStandings(team2.getCountryCode(), team2.getTeamName()));
            TeamStandings team2Standings = standingsMap.get(team2.getTeamName());
            team2Standings.setPlayed(team2Standings.getPlayed() + 1);
            team2Standings.setGoalsFor(team2Standings.getGoalsFor() + goals2);
            team2Standings.setGoalsAgainst(team2Standings.getGoalsAgainst() + goals1);
            team2Standings.setGoalDifference(team2Standings.getGoalsFor() - team2Standings.getGoalsAgainst());
            if (goals2 > goals1) {
                team2Standings.setPoints(team2Standings.getPoints() + 3);
                team2Standings.setWon(team2Standings.getWon() + 1);
            } else if (goals2 == goals1) {
                team2Standings.setPoints(team2Standings.getPoints() + 1);
                team2Standings.setDrawn(team2Standings.getDrawn() + 1);
            } else {
                team2Standings.setLost(team2Standings.getLost() + 1);
            }
        }

        // Posortuj tabelę wg punktów (malejąco), potem wg różnicy bramek, potem wg strzelonych goli
        List<TeamStandings> standingsList = new ArrayList<>(standingsMap.values());
        standingsList.sort((t1, t2) -> {
            int pointsCompare = Integer.compare(t2.getPoints(), t1.getPoints());
            if (pointsCompare != 0) return pointsCompare;
            int goalDifferenceCompare = Integer.compare(t2.getGoalDifference(), t1.getGoalDifference());
            if (goalDifferenceCompare != 0) return goalDifferenceCompare;
            return Integer.compare(t2.getGoalsFor(), t1.getGoalsFor());
        });

        return standingsList;
    }



}