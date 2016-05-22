package davis.gametracker.service.impl;

import org.springframework.stereotype.Service;

import davis.gametracker.domain.db.GameSystem;
import davis.gametracker.domain.json.GameSystemData;
import davis.gametracker.service.GameSystemConverter;

@Service
public class GameSystemConverterImpl implements GameSystemConverter
{
	@Override
	public void populate(GameSystem record, GameSystemData data)
	{
		record.setName(data.getName());
	}

	@Override
	public GameSystem initialize(GameSystemData data)
	{
		GameSystem newSystem = new GameSystem(data.getId());

		populate(newSystem, data);

		return newSystem;
	}

	@Override
	public GameSystemData convert(GameSystem record)
	{
		return new GameSystemData(record.getId())
				.setName(record.getName());
	}

}
