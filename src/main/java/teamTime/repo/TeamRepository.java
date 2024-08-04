package teamTime.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import teamTime.models.Team;

public interface TeamRepository extends JpaRepository<Team, Long> {
}

