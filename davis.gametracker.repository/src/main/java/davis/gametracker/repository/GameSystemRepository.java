package davis.gametracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import davis.gametracker.domain.db.GameSystem;

@Repository
public interface GameSystemRepository extends JpaRepository<GameSystem, Integer>
{
	public GameSystem findById(String id);
}
