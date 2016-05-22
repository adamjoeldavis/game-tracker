package davis.gametracker.service.game;

import davis.gametracker.domain.db.Game;
import davis.gametracker.domain.json.GameData;
import davis.gametracker.service.CrudService;

/**
 * CRUD service for {@link Game} entities
 * 
 * @author Adam Davis
 */
public interface GameService extends CrudService<Integer, Game, GameData>
{
}
