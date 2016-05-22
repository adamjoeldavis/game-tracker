package davis.gametracker.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import davis.gametracker.domain.db.Game;
import davis.gametracker.domain.json.GameData;
import davis.gametracker.repository.GameRepository;
import davis.gametracker.service.GameConverter;
import davis.gametracker.service.GameService;

@Service
public class GameServiceImpl implements GameService
{
	private GameConverter	converter;
	private GameRepository	repository;

	@Autowired
	public GameServiceImpl(GameConverter converter, GameRepository repository)
	{
		this.converter = converter;
		this.repository = repository;
	}

	@Override
	public Game create(GameData newGame)
	{
		return repository.saveAndFlush(converter.initialize(newGame));
	}

	@Override
	public Game update(Integer primaryKey, GameData contents)
	{
		Game existingGame = load(primaryKey);

		converter.populate(existingGame, contents);

		return repository.saveAndFlush(existingGame);
	}

	@Override
	public Game load(Integer primaryKey)
	{
		return repository.findOne(primaryKey);
	}

	@Override
	public List<Game> list()
	{
		return repository.findAll();
	}

}
