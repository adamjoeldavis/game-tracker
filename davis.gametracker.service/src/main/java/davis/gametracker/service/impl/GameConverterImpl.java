package davis.gametracker.service.impl;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import davis.gametracker.domain.db.Game;
import davis.gametracker.domain.json.GameData;
import davis.gametracker.domain.json.GameSystemData;
import davis.gametracker.service.GameConverter;
import davis.gametracker.service.GameSystemConverter;
import davis.gametracker.service.GameSystemService;

@Service
public class GameConverterImpl implements GameConverter
{
	private GameSystemConverter	systemConverter;
	private GameSystemService	systemService;

	@Autowired
	public GameConverterImpl(GameSystemConverter systemConverter, GameSystemService systemService)
	{
		this.systemConverter = systemConverter;
		this.systemService = systemService;
	}

	@Override
	public void populate(Game record, GameData data)
	{
		for (GameSystemData system : data.getOwnedOn())
		{
			record.ownedOn(systemService.load(system.getId()));
		}
	}

	@Override
	public Game initialize(GameData data)
	{
		Game newGame = new Game(data.getName());

		populate(newGame, data);

		return newGame;
	}

	@Override
	public GameData convert(Game record)
	{
		return new GameData(record.getName())
				.setOwnedOn(record.getOwnedOn().stream()
						.map(systemConverter::convert)
						.collect(Collectors.toSet()));
	}

}
