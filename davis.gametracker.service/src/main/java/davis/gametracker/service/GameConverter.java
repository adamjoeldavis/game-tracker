package davis.gametracker.service;

import davis.gametracker.domain.db.Game;
import davis.gametracker.domain.json.GameData;

public interface GameConverter
{
	public void populate(Game record, GameData data);

	public Game initialize(GameData data);

	public GameData convert(Game record);
}
