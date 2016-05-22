package davis.gametracker.service;

import java.util.List;

import davis.gametracker.domain.db.Game;
import davis.gametracker.domain.json.GameData;

public interface GameService
{
	public Game create(GameData newGame);

	public Game update(Integer primaryKey, GameData contents);

	public Game load(Integer primaryKey);

	public List<Game> list();
}
