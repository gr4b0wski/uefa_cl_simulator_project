package com.knf.dev.CL_Simulator.service;

import com.knf.dev.CL_Simulator.entity.Match;
import com.knf.dev.CL_Simulator.entity.Team;

import java.util.*;

public class MatchService {
    // ["Team1", "Team2", "Team3", "Team4", "Team5", "Team6", "Team7", "Team8", "Team9"],  # Koszyk 1
    // ["Team10", "Team11", "Team12", "Team13", "Team14", "Team15", "Team16", "Team17", "Team18"],  # Koszyk 2
    // ["Team19", "Team20", "Team21", "Team22", "Team23", "Team24", "Team25", "Team26", "Team27"],  # Koszyk 3
    // ["Team28", "Team29", "Team30", "Team31", "Team32", "Team33", "Team34", "Team35", "Team36"],  # Koszyk 4

    public ArrayList<Match> setLeaguePhase(List<Team> pot1, List<Team> pot2, List<Team> pot3, List<Team> pot4) {
        ArrayList<Match> matches = new ArrayList<>();
        Random random = new Random();
        // 1 kolejka
        matches.add(new Match(pot3.get(7), pot4.get(3)));
        matches.add(new Match(pot2.get(3), pot3.get(1)));
        matches.add(new Match(pot2.get(8), pot1.get(4)));
        matches.add(new Match(pot1.get(2), pot3.get(3)));
        matches.add(new Match(pot1.get(0), pot4.get(6)));
        matches.add(new Match(pot3.get(1), pot3.get(6)));
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
        matches.add(new Match(pot3.get(1), pot1.get(2)));
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


}