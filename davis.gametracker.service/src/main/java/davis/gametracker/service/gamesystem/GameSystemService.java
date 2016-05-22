package davis.gametracker.service.gamesystem;

import davis.gametracker.domain.db.GameSystem;
import davis.gametracker.domain.json.GameSystemData;
import davis.gametracker.service.CrudService;

/**
 * CRUD service for {@link GameSystem} entities
 * 
 * @author Adam Davis
 */
public interface GameSystemService extends CrudService<Integer, GameSystem, GameSystemData>
{
	/**
	 * Loads a {@link GameSystem} record by its ID
	 * 
	 * @param id
	 *            ID of the system to load
	 * @return loaded system
	 * @throws IllegalArgumentException
	 *             if no record is found
	 */
	public GameSystem load(String id) throws IllegalArgumentException;
}
