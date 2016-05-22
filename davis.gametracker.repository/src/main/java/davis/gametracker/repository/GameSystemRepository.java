package davis.gametracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import davis.gametracker.domain.db.GameSystem;

/**
 * Repository for database interactions on a {@link GameSystem} entity
 * 
 * @author Adam Davis
 */
@Repository
public interface GameSystemRepository extends JpaRepository<GameSystem, Integer>
{
    /**
     * Finds a system by its ID
     * 
     * @param id
     *            the id to match on
     * @return the matching system
     */
    public GameSystem findById(String id);
}
