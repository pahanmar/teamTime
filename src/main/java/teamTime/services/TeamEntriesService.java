package teamTime.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import teamTime.models.Entry;
import teamTime.models.Team;
import teamTime.repo.EntryRepository;
import teamTime.repo.TeamRepository;

@Service
public class TeamEntriesService {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private EntryRepository entryRepository;

    public void saveTeamsWithEntries(Team team) {
        teamRepository.save(team);
    }
    public void saveEntries(Entry entry) {
        entryRepository.save(entry);
    }
}