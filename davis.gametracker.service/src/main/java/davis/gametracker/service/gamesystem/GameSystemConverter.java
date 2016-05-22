package davis.gametracker.service.gamesystem;

import davis.gametracker.domain.db.GameSystem;
import davis.gametracker.domain.json.GameSystemData;
import davis.gametracker.service.ConverterService;

/**
 * Converter service for {@link GameSystem} entities
 * 
 * @author Adam Davis
 */
public interface GameSystemConverter extends ConverterService<GameSystem, GameSystemData>
{
}
