package davis.gametracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import davis.gametracker.domain.db.Game;
import davis.gametracker.domain.db.GameSystem;

/**
 * Repository for database interactions on a {@link Game} entity
 * 
 * @author Adam Davis
 */
@Repository
public interface GameRepository extends JpaRepository<Game, Integer>
{
    /**
     * Finds a list of all games that are owned on a {@link GameSystem} with the
     * given ID
     * 
     * @param systemId
     *            ID of the game system to search for
     * @return list of games owned on the given system
     */
    public List<Game> findByOwnedOn_Id(String systemId);
}
