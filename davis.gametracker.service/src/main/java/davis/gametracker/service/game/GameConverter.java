package davis.gametracker.service.game;

import davis.gametracker.domain.db.Game;
import davis.gametracker.domain.json.GameData;
import davis.gametracker.service.ConverterService;

/**
 * Converter for {@link Game} entities.
 * 
 * @author Adam Davis
 */
public interface GameConverter extends ConverterService<Game, GameData>
{
}
