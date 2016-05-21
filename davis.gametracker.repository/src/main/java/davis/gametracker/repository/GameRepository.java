package davis.gametracker.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import davis.gametracker.domain.db.Game;

@Repository
public interface GameRepository extends CrudRepository<Game, Integer>
{

}
