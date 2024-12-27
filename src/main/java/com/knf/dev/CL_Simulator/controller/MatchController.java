package com.knf.dev.CL_Simulator.controller;

import com.knf.dev.CL_Simulator.entity.Match;
import com.knf.dev.CL_Simulator.entity.Team;
import com.knf.dev.CL_Simulator.service.MatchService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class MatchController {
    @GetMapping("/matches")
    public String showMatches(Model model, HttpSession session) {
        MatchService service = new MatchService();

        List<Team> pot1 = (List<Team>) session.getAttribute("pot1");
        List<Team> pot2 = (List<Team>) session.getAttribute("pot2");
        List<Team> pot3 = (List<Team>) session.getAttribute("pot3");
        List<Team> pot4 = (List<Team>) session.getAttribute("pot4");

        ArrayList<Match> matches = service.setLeaguePhase(pot1, pot2, pot3, pot4);

        model.addAttribute("matches", matches);
        return "matches";
    }
}
