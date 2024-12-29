package com.knf.dev.CL_Simulator.controller;

import com.knf.dev.CL_Simulator.entity.Match;
import com.knf.dev.CL_Simulator.entity.Team;
import com.knf.dev.CL_Simulator.entity.TeamStandings;
import com.knf.dev.CL_Simulator.service.MatchService;
import com.knf.dev.CL_Simulator.service.TeamService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Controller
public class MatchController {
    @GetMapping("/matches")
    public String showMatches(Model model, HttpSession session) throws Exception {
        MatchService service = new MatchService();

        @SuppressWarnings("unchecked")
        ArrayList<Match> matches = (ArrayList<Match>) session.getAttribute("matches");

        // Jeśli terminarz nie istnieje w sesji, wygeneruj go i zapisz
        if (matches == null) {
            List<Team> pot1 = (List<Team>) session.getAttribute("pot1");
            List<Team> pot2 = (List<Team>) session.getAttribute("pot2");
            List<Team> pot3 = (List<Team>) session.getAttribute("pot3");
            List<Team> pot4 = (List<Team>) session.getAttribute("pot4");

            if (pot1 == null || pot2 == null || pot3 == null || pot4 == null) {
                throw new Exception("Koszyki drużyn nie zostały poprawnie załadowane.");
            }

            matches = service.setLeaguePhase(pot1, pot2, pot3, pot4);
            session.setAttribute("matches", matches);
            session.setAttribute("currentFixture", 1);
        }

        List<TeamStandings> standings = service.calculateLeagueTable(matches);
        model.addAttribute("standings", standings);
        // Podziel mecze na kolejki
        List<Match> fixture1 = matches.subList(0, 18);
        List<Match> fixture2 = matches.subList(18, 36);
        List<Match> fixture3 = matches.subList(36, 54);
        List<Match> fixture4 = matches.subList(54, 72);
        List<Match> fixture5 = matches.subList(72, 90);
        List<Match> fixture6 = matches.subList(90, 108);
        List<Match> fixture7 = matches.subList(108, 126);
        List<Match> fixture8 = matches.subList(126, 144);

        Integer currentFixture = (Integer) session.getAttribute("currentFixture");
        if (currentFixture == null) {
            currentFixture = 1;
        }
        // Dodaj dane do modelu
        model.addAttribute("matches", matches);
        model.addAttribute("fixture1", fixture1);
        model.addAttribute("fixture2", fixture2);
        model.addAttribute("fixture3", fixture3);
        model.addAttribute("fixture4", fixture4);
        model.addAttribute("fixture5", fixture5);
        model.addAttribute("fixture6", fixture6);
        model.addAttribute("fixture7", fixture7);
        model.addAttribute("fixture8", fixture8);

        model.addAttribute("currentFixture", currentFixture);
        return "matches";
    }

    @GetMapping("/simulate")
    public String simulateFixture(HttpSession session, Model model) throws Exception {
        @SuppressWarnings("unchecked")
        ArrayList<Match> matches = (ArrayList<Match>) session.getAttribute("matches");

        if (matches == null) {
            throw new Exception("Nie wygenerowano terminarza. Odwiedź /matches, aby wygenerować mecze.");
        }

        // Pobierz aktualny numer kolejki z sesji, domyślnie 1
        Integer currentFixture = (Integer) session.getAttribute("currentFixture");
        if (currentFixture == null) {
            currentFixture = 1;
        }

        // Wyznacz zakres meczów dla aktualnej kolejki
        int startIndex = (currentFixture - 1) * 18;
        int endIndex = currentFixture * 18;

        if (startIndex < 0 || endIndex > matches.size()) {
            throw new IllegalArgumentException("Nieprawidłowy numer kolejki.");
        }
        MatchService matchService = new MatchService();
        // Symuluj mecze dla aktualnej kolejki
        for (int i = startIndex; i < endIndex; i++) {
            matchService.simulateMatch(matches.get(i));
        }

        // Zwiększ numer kolejki w sesji
        session.setAttribute("currentFixture", currentFixture + 1);

        // Przekieruj z powrotem do widoku meczów
        return "redirect:/matches";
    }

    @GetMapping("/knockouts")
    public String generateKnockoutMatches(HttpSession session, Model model) {
        MatchService matchService = new MatchService();
        TeamService teamService = new TeamService();
        ArrayList<Match> matches = (ArrayList<Match>) session.getAttribute("matches");

        if (matches == null) {
            throw new IllegalArgumentException("Brak danych o meczach w sesji.");
        }

        List<TeamStandings> standings = matchService.calculateLeagueTable(matches);

        // Weź pierwsze 24 drużyny
        List<TeamStandings> top24Teams = standings.subList(0, Math.min(24, standings.size()));

        // Lista meczów dla fazy play-off i dalszych rund
        List<Match> knockoutMatches = new ArrayList<>();

        // Faza play-off (9-24, 10-23, ..., 16-17)
        for (int i = 8; i < 16; i++) {
            int opponentIndex = 31 - i; // Indeksy przeciwników (dla drużyn 9-24)
            TeamStandings team1tabela = top24Teams.get(i);
            List<String> lista1 = Collections.singletonList(team1tabela.getTeamName());
            TeamStandings team2tabela = top24Teams.get(opponentIndex);
            List<String> lista2 = Collections.singletonList(team2tabela.getTeamName());
            List<Team> team1 = teamService.getTeamsByName(lista1);
            List<Team> team2 = teamService.getTeamsByName(lista2);
            knockoutMatches.add(new Match(team1.get(0), team2.get(0)));
        }

        // Dodaj dane do modelu
        model.addAttribute("knockoutMatches", knockoutMatches);
        session.setAttribute("knockoutMatches", knockoutMatches);
        return "knockouts"; // Widok knockouts.html
    }


    @GetMapping("/simulatePlayoffs")
    public String simulatePlayoffs(HttpSession session, Model model) throws Exception {
        @SuppressWarnings("unchecked")
        List<Match> knockoutMatches = (List<Match>) session.getAttribute("knockoutMatches");

        MatchService matchService = new MatchService();
        TeamService teamService = new TeamService();
        ArrayList<Match> matches = (ArrayList<Match>) session.getAttribute("matches");
        List<TeamStandings> standings = matchService.calculateLeagueTable(matches);

        if (knockoutMatches == null || knockoutMatches.isEmpty()) {
            throw new Exception("Brak danych o meczach w fazie play-off.");
        }

        // Symulacja wyników meczów w fazie play-off
        for (int i = 0; i < 8; i++) {
            do {
                Match match = knockoutMatches.get(i);
                matchService.simulateMatch(match);
                if (match.getGoals1() > match.getGoals2()) {
                    knockoutMatches.add(new Match(match.getTeam1(), teamService.getTeamsByName(Collections.singletonList(standings.get(i).getTeamName())).get(0)));
                }
                else if (match.getGoals1() < match.getGoals2()) {
                    knockoutMatches.add(new Match(match.getTeam2(), teamService.getTeamsByName(Collections.singletonList(standings.get(i).getTeamName())).get(0)));
                }


            } while (knockoutMatches.get(i).getGoals1() == knockoutMatches.get(i).getGoals2());
              // Symulacja meczu
        }

        // Zaktualizowane mecze w sesji
        model.addAttribute("knockoutMatches", knockoutMatches);  // Dodaj do modelu
        session.setAttribute("knockoutMatches", knockoutMatches); // Zapisz w sesji
        model.addAttribute("buttonText", "Go to 1/8 phase");

        // Po zakończeniu symulacji przekierowanie do strony fazy play-off
        return "knockouts";  // Powrót do strony, gdzie wyświetlane są wyniki
    }


    @GetMapping("/roundOf16")
    public String knockoutFinals(HttpSession session, Model model) throws Exception {
        @SuppressWarnings("unchecked")
        List<Match> knockoutMatches = (List<Match>) session.getAttribute("knockoutMatches");

        if (knockoutMatches == null || knockoutMatches.isEmpty()) {
            throw new Exception("Brak danych o meczach w fazie pucharowej.");
        }

        // Załaduj mecze finałowe do modelu
        model.addAttribute("knockoutMatches", knockoutMatches);

        return "roundOf16"; // Zwracamy widok knockoutfinals
    }

    @GetMapping("/simulateRoundOf16")
    public String simulateRoundOf16(HttpSession session, Model model) throws Exception {
        @SuppressWarnings("unchecked")
        List<Match> knockoutMatches = (List<Match>) session.getAttribute("knockoutMatches");

        MatchService matchService = new MatchService();

        if (knockoutMatches == null || knockoutMatches.isEmpty()) {
            throw new Exception("Brak danych o meczach w fazie 1/8.");
        }

        // Symulacja wyników meczów w fazie 1/8
        List<Team> winners = new ArrayList<>();
        for (int i = 8; i < 16; i++) {
            Match match = knockoutMatches.get(i);
            do {
                matchService.simulateMatch(match);
                if (match.getGoals1() > match.getGoals2()) {
                    winners.add(match.getTeam1());
                }
                else if (match.getGoals1() < match.getGoals2()) {
                    winners.add(match.getTeam2());
                }
            } while (knockoutMatches.get(i).getGoals1() == knockoutMatches.get(i).getGoals2());
        }
        knockoutMatches.add(new Match(winners.get(0), winners.get(1)));
        knockoutMatches.add(new Match(winners.get(2), winners.get(3)));
        knockoutMatches.add(new Match(winners.get(4), winners.get(5)));
        knockoutMatches.add(new Match(winners.get(6), winners.get(7)));

        // Zaktualizowane mecze w sesji
        model.addAttribute("knockoutMatches", knockoutMatches);  // Dodaj do modelu
        session.setAttribute("knockoutMatches", knockoutMatches); // Zapisz w sesji
        model.addAttribute("buttonText", "Go to quarterfinals");

        // Po zakończeniu symulacji przekierowanie do strony fazy play-off
        return "roundOf16";  // Powrót do strony, gdzie wyświetlane są wyniki
    }

    @GetMapping("/quarterfinals")
    public String quarterfinals(HttpSession session, Model model) throws Exception {
        @SuppressWarnings("unchecked")
        List<Match> knockoutMatches = (List<Match>) session.getAttribute("knockoutMatches");

        if (knockoutMatches == null || knockoutMatches.isEmpty()) {
            throw new Exception("Brak danych o meczach w fazie pucharowej.");
        }

        // Załaduj mecze finałowe do modelu
        model.addAttribute("knockoutMatches", knockoutMatches);

        return "quarterfinals"; // Zwracamy widok knockoutfinals
    }

    @GetMapping("/simulateQuarterfinals")
    public String simulateQuarterfinals(HttpSession session, Model model) throws Exception {
        @SuppressWarnings("unchecked")
        List<Match> knockoutMatches = (List<Match>) session.getAttribute("knockoutMatches");

        MatchService matchService = new MatchService();

        if (knockoutMatches == null || knockoutMatches.isEmpty()) {
            throw new Exception("Brak danych o meczach w fazie 1/4.");
        }

        List<Team> winners = new ArrayList<>();
        for (int i = 16; i < 20; i++) {
            Match match = knockoutMatches.get(i);
            do {
                matchService.simulateMatch(match);
                if (match.getGoals1() > match.getGoals2()) {
                    winners.add(match.getTeam1());
                }
                else if (match.getGoals1() < match.getGoals2()) {
                    winners.add(match.getTeam2());
                }
            } while (knockoutMatches.get(i).getGoals1() == knockoutMatches.get(i).getGoals2());
        }
        knockoutMatches.add(new Match(winners.get(0), winners.get(1)));
        knockoutMatches.add(new Match(winners.get(2), winners.get(3)));

        // Zaktualizowane mecze w sesji
        model.addAttribute("knockoutMatches", knockoutMatches);  // Dodaj do modelu
        session.setAttribute("knockoutMatches", knockoutMatches); // Zapisz w sesji
        model.addAttribute("buttonText", "Go to semifinals");

        return "quarterfinals";  // Powrót do strony, gdzie wyświetlane są wyniki
    }

    @GetMapping("/semifinals")
    public String semifinals(HttpSession session, Model model) throws Exception {
        @SuppressWarnings("unchecked")
        List<Match> knockoutMatches = (List<Match>) session.getAttribute("knockoutMatches");

        if (knockoutMatches == null || knockoutMatches.isEmpty()) {
            throw new Exception("Brak danych o meczach w fazie pucharowej.");
        }

        // Załaduj mecze finałowe do modelu
        model.addAttribute("knockoutMatches", knockoutMatches);

        return "semifinals"; // Zwracamy widok knockoutfinals
    }

    @GetMapping("/simulateSemifinals")
    public String simulateSemifinals(HttpSession session, Model model) throws Exception {
        @SuppressWarnings("unchecked")
        List<Match> knockoutMatches = (List<Match>) session.getAttribute("knockoutMatches");

        MatchService matchService = new MatchService();

        if (knockoutMatches == null || knockoutMatches.isEmpty()) {
            throw new Exception("Brak danych o meczach w fazie 1/4.");
        }

        List<Team> winners = new ArrayList<>();
        for (int i = 20; i < 22; i++) {
            Match match = knockoutMatches.get(i);
            do {
                matchService.simulateMatch(match);
                if (match.getGoals1() > match.getGoals2()) {
                    winners.add(match.getTeam1());
                }
                else if (match.getGoals1() < match.getGoals2()) {
                    winners.add(match.getTeam2());
                }
            } while (knockoutMatches.get(i).getGoals1() == knockoutMatches.get(i).getGoals2());
        }
        knockoutMatches.add(new Match(winners.get(0), winners.get(1)));

        // Zaktualizowane mecze w sesji
        model.addAttribute("knockoutMatches", knockoutMatches);  // Dodaj do modelu
        session.setAttribute("knockoutMatches", knockoutMatches); // Zapisz w sesji
        model.addAttribute("buttonText", "Go to the final");

        return "semifinals";  // Powrót do strony, gdzie wyświetlane są wyniki
    }

    @GetMapping("/final")
    public String finalm(HttpSession session, Model model) throws Exception {
        @SuppressWarnings("unchecked")
        List<Match> knockoutMatches = (List<Match>) session.getAttribute("knockoutMatches");

        if (knockoutMatches == null || knockoutMatches.isEmpty()) {
            throw new Exception("Brak danych o meczach w fazie pucharowej.");
        }
        model.addAttribute("knockoutMatches", knockoutMatches);

        return "final"; // Zwracamy widok knockoutfinals
    }

    @GetMapping("/simulateFinal")
    public String simulatefinal(HttpSession session, Model model) throws Exception {
        @SuppressWarnings("unchecked")
        List<Match> knockoutMatches = (List<Match>) session.getAttribute("knockoutMatches");

        MatchService matchService = new MatchService();

        if (knockoutMatches == null || knockoutMatches.isEmpty()) {
            throw new Exception("Brak danych o meczach w fazie finału.");
        }

        List<Team> winners = new ArrayList<>();
        for (int i = 22; i <= 22; i++) {
            Match match = knockoutMatches.get(i);
            do {
                matchService.simulateMatch(match);
                if (match.getGoals1() > match.getGoals2()) {
                    winners.add(match.getTeam1());
                }
                else if (match.getGoals1() < match.getGoals2()) {
                    winners.add(match.getTeam2());
                }
            } while (knockoutMatches.get(i).getGoals1() == knockoutMatches.get(i).getGoals2());
        }

        // Zaktualizowane mecze w sesji
        model.addAttribute("knockoutMatches", knockoutMatches);  // Dodaj do modelu
        session.setAttribute("knockoutMatches", knockoutMatches); // Zapisz w sesji
        model.addAttribute("buttonText", "End of the tournament :)");

        return "final";  // Powrót do strony, gdzie wyświetlane są wyniki
    }

}
