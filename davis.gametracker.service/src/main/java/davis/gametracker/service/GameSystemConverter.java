package davis.gametracker.service;

import davis.gametracker.domain.db.GameSystem;
import davis.gametracker.domain.json.GameSystemData;

public interface GameSystemConverter
{
	public void populate(GameSystem record, GameSystemData data);

	public GameSystem initialize(GameSystemData data);

	public GameSystemData convert(GameSystem record);
}
