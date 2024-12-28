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
    public String showMatches(Model model, HttpSession session) throws Exception {
        MatchService service = new MatchService();

        List<Team> pot1 = (List<Team>) session.getAttribute("pot1");
        List<Team> pot2 = (List<Team>) session.getAttribute("pot2");
        List<Team> pot3 = (List<Team>) session.getAttribute("pot3");
        List<Team> pot4 = (List<Team>) session.getAttribute("pot4");

        ArrayList<Match> matches = service.setLeaguePhase(pot1, pot2, pot3, pot4);
        List<Match> fixture1 = matches.subList(0, 18); // Kolejka 1
        List<Match> fixture2 = matches.subList(18, 36);
        List<Match> fixture3 = matches.subList(36, 54);
        List<Match> fixture4 = matches.subList(54, 72);
        List<Match> fixture5 = matches.subList(72, 90);
        List<Match> fixture6 = matches.subList(90, 108);
        List<Match> fixture7 = matches.subList(108, 126);
        List<Match> fixture8 = matches.subList(126, 144); // Kolejka 8

        model.addAttribute("matches", matches);
        model.addAttribute("fixture1", fixture1);
        model.addAttribute("fixture2", fixture2);
        model.addAttribute("fixture3", fixture3);
        model.addAttribute("fixture4", fixture4);
        model.addAttribute("fixture5", fixture5);
        model.addAttribute("fixture6", fixture6);
        model.addAttribute("fixture7", fixture7);
        model.addAttribute("fixture8", fixture8);

        return "matches";
    }
}
