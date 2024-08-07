package teamTime.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import teamTime.models.Entry;
import teamTime.models.Team;

import java.util.List;

public interface EntryRepository extends JpaRepository<Entry, Long> {
        List<Entry> findByTeamId(Long teamId);

    Iterable<Entry> findByTeam(Team team);
}


