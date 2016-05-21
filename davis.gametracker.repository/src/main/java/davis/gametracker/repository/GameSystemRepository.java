package davis.gametracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import davis.gametracker.domain.db.GameSystem;

@Repository
public interface GameSystemRepository extends JpaRepository<GameSystem, Integer>
{
	public List<GameSystem> findById(String id);
}
