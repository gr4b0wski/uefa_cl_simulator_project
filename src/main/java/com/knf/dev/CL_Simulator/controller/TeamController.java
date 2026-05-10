package com.knf.dev.CL_Simulator.controller;
import com.knf.dev.CL_Simulator.entity.Team;
import com.knf.dev.CL_Simulator.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class TeamController {

	@Autowired
	private TeamService teamService;

	private List<Team> selectedTeams = new ArrayList<>();

	@GetMapping("/")
	public String home() {
		return "index";
	}

	@GetMapping("/teams")
	public String showTeams(Model model, HttpSession session) {
		List<Team> teams = teamService.loadTeamsFromCsv();  // Ładowanie drużyn// Logowanie liczby drużyn
		model.addAttribute("teams", teams);
		session.setAttribute("teams", teams);// Dodanie do modelu
		return "team-list";
	}


	@PostMapping("/draw")
	public String drawTeams(@RequestParam List<String> selectedTeams, HttpSession session, Model model) {
		// 1. Pobierz wszystkie drużyny z sesji (lub pliku)
		List<Team> allTeams = (List<Team>) session.getAttribute("teams");
		if (allTeams == null) {
			allTeams = teamService.loadTeamsFromCsv();
		}

		// 2. PRZEFILTRUJ drużyny - zostaw tylko te, które użytkownik zaznaczył w HTML
		List<Team> pickedTeams = allTeams.stream()
				.filter(team -> selectedTeams.contains(team.getTeamName()))
				.toList(); // W starszej Javie: .collect(Collectors.toList());

		// Opcjonalnie: zabezpieczenie, gdyby przyszło mniej lub więcej niż 36 drużyn
		if (pickedTeams.size() != 36) {
			return "redirect:/teams"; // Zwróć użytkownika na stronę wyboru, jeśli coś zepsuł w HTML
		}

		// 3. Posortuj TYLKO wybrane drużyny po ELO
		// Używamy kopii listy (ArrayList), bo wynik toList() ze streama może być niemodyfikowalny
		List<Team> sortedTeams = new ArrayList<>(pickedTeams);
		sortedTeams.sort(Comparator.comparingInt(Team::getEloPoints).reversed());

		// 4. Podziel przefiltrowane i posortowane drużyny na koszyki
		List<Team> pot1 = sortedTeams.subList(0, 9);
		List<Team> pot2 = sortedTeams.subList(9, 18);
		List<Team> pot3 = sortedTeams.subList(18, 27);
		List<Team> pot4 = sortedTeams.subList(27, 36);

		// Zapisz do sesji i modelu
		session.setAttribute("pot1", pot1);
		session.setAttribute("pot2", pot2);
		session.setAttribute("pot3", pot3);
		session.setAttribute("pot4", pot4);

		model.addAttribute("pot1", pot1);
		model.addAttribute("pot2", pot2);
		model.addAttribute("pot3", pot3);
		model.addAttribute("pot4", pot4);

		return "draw";
	}


	@PostMapping("/update-elo")
	public String updateElo(@RequestParam String teamName, @RequestParam int eloPoints, HttpSession session, Model model) {
		// Wywołanie metody w serwisie, aby zaktualizować ELO w sesji
		teamService.updateEloForTeam(teamName, eloPoints, session);

		// Załaduj drużyny po zaktualizowaniu ELO
		List<Team> updatedTeams = (List<Team>) session.getAttribute("teams");
		model.addAttribute("teams", updatedTeams);

		// Po zaktualizowaniu wróć do strony z listą drużyn
		return "team-list"; // Można zmienić na "redirect:/teams" w zależności od preferencji
	}




}