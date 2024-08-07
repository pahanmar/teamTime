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
    private TeamRepository teamRepository;

    @GetMapping("/entries")
    public String entriesMain(@RequestParam(required = false) Long teamId, Model model) {
        Iterable<Entry> entries;
        if (teamId != null) {
            Team team = teamRepository.findById(teamId).orElse(new Team());
            entries = entryRepository.findByTeam(team);
            model.addAttribute("filterTeam", team);
        } else {
            entries = entryRepository.findAll();
        }
        model.addAttribute("entries", entries);
        model.addAttribute("teams", teamRepository.findAll());
        return "entries";
    }

    @GetMapping("/entries/add")
    public String showEntryForm(Model model) {
        model.addAttribute("entry", new Entry());
        model.addAttribute("teams", teamRepository.findAll());
        return "entries_add";
    }

    @PostMapping("/entries")
    public String saveEntry(@ModelAttribute Entry entry) {
        entryRepository.save(entry);
        return "redirect:/entries";
    }

    @GetMapping("/entries/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Entry entry = entryRepository.findById(id).orElse(new Entry());
        model.addAttribute("entry", entry);
        model.addAttribute("teams", teamRepository.findAll());
        return "entries_edit";
    }

    @PostMapping("/entries/edit")
    public String editEntry(@ModelAttribute Entry entry) {
        entryRepository.save(entry);
        return "redirect:/entries";
    }

    @GetMapping("/entries/delete/{id}")
    public String deleteEntry(@PathVariable("id") Long id) {
        entryRepository.deleteById(id);
        return "redirect:/entries";
    }
}