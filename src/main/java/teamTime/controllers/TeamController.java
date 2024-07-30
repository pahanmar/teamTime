package teamTime.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import teamTime.models.Team;
import teamTime.repo.EntryRepository;
import teamTime.repo.TeamRepository;
import teamTime.services.TeamEntriesService;

@Controller
public class TeamController {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private EntryRepository entryRepository;

    @GetMapping("/teams")
    public String teamsMain(Model model) {
        Iterable<Team> teams = teamRepository.findAll();
        model.addAttribute("teams", teams);
        return "team_main";
    }

    @GetMapping("/teams/add")
    public String teamsAdd(Model model) {
        return "team_add";
    }

    @PostMapping("/teams/add")
    public String teamPostAdd(@RequestParam String name, @RequestParam String color, @RequestParam Long time, Model model) {
        Team team = new Team(name, color, time);
        teamRepository.save(team);
        return "redirect:/";
    }
    @Autowired
    private TeamEntriesService teamEntriesService;

    @PostMapping("/teams")
    public String saveTeam(@ModelAttribute Team team) {
        teamEntriesService.saveTeamsWithEntries(team);
        return "redirect:/teams?team=" + team.getId();
    }
    @GetMapping("/teams/{id}")
    public String viewTeamEntries(@PathVariable Long id, Model model) {
        Team team = teamRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid team Id:" + id));
        model.addAttribute("team", team);
        model.addAttribute("entries", entryRepository.findByTeamId(id));
        return "team_entries";
    }
}

