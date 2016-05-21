package davis.gametracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import davis.gametracker.domain.db.Game;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer>
{
	public List<Game> findByOwnedOn_Id(String systemId);
}