package com.knf.dev.CL_Simulator.service;
import com.knf.dev.CL_Simulator.entity.Team;
import org.springframework.stereotype.Service;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class TeamService {
	public List<Team> loadTeamsFromCsv() {
		List<Team> teams = new ArrayList<>();
		try {
			// Wczytanie pliku CSV z katalogu src/main/resources/data
			ClassPathResource resource = new ClassPathResource("data/teams.csv");
			BufferedReader br = new BufferedReader(new FileReader(resource.getFile()));

			String line;
			while ((line = br.readLine()) != null) {
				String[] values = line.split(";");
				if (values.length == 3) {
					Team team = new Team();
					team.setCountryCode(values[0]);
					team.setTeamName(values[1]);
					team.setEloPoints(Integer.parseInt(values[2]));
					teams.add(team);
				}
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		teams.sort(Comparator.comparingInt(Team::getEloPoints).reversed());

		return teams;
	}

	public List<Team> getTeamsByName(List<String> teamNames) {
		// Pobierz wszystkie drużyny
		List<Team> allTeams = loadTeamsFromCsv();

		// Filtrowanie drużyn na podstawie nazw
		List<Team> selectedTeams = new ArrayList<>();
		for (String teamName : teamNames) {
			allTeams.stream()
					.filter(team -> team.getTeamName().equals(teamName))
					.findFirst()
					.ifPresent(selectedTeams::add);
		}

		return selectedTeams;
	}
}
