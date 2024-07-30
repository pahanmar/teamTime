package teamTime.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import teamTime.models.Entry;
import teamTime.models.Team;
import teamTime.repo.EntryRepository;
import teamTime.repo.TeamRepository;
import teamTime.services.TeamEntriesService;

@Controller
public class EntryController {

    @Autowired
    private EntryRepository entryRepository;

    @Autowired
    private TeamEntriesService teamEntriesService;

    @Autowired
    private TeamRepository teamRepository;

    @GetMapping("/entries")
    public String entriesMain(Model model){
        Iterable<Entry> entries = entryRepository.findAll();
        model.addAttribute("entries",entries);
        return "team_main";
    }
    @GetMapping("entries/add")
    public String showEntryForm(Model model) {
        model.addAttribute("entry", new Entry());
        model.addAttribute("teams", teamRepository.findAll());
        return "entries_add";
    }
    @PostMapping("/entries")
    public String saveEntry(@ModelAttribute Entry entry) {
        teamEntriesService.saveEntries(entry);
        return "redirect:/";
        }
    @PostMapping("/entries/add")
    public String entryPostAdd(@RequestParam String annotation, @RequestParam Long time,@RequestParam Team team, Model model) {
        Entry entry = new Entry(annotation, time, team);
        entryRepository.save(entry);
        return "redirect:/";
    }


}
