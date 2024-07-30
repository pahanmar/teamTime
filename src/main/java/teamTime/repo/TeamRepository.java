package teamTime.repo;

import org.springframework.data.repository.CrudRepository;
import teamTime.models.Team;

public interface TeamRepository extends CrudRepository<Team, Long> {
}

