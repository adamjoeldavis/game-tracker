package davis.gametracker.service;

import java.util.List;

import davis.gametracker.domain.db.GameSystem;
import davis.gametracker.domain.json.GameSystemData;

public interface GameSystemService
{
	public GameSystem create(GameSystemData data);

	public GameSystem update(Integer key, GameSystemData data);

	public GameSystem load(Integer key);

	public GameSystem load(String id);

	public List<GameSystem> list();
}
