package davis.gametracker.service.impl;

import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import davis.gametracker.domain.db.EntityBase;
import davis.gametracker.domain.db.Game;
import davis.gametracker.domain.json.GameData;
import davis.gametracker.repository.GameRepository;
import davis.gametracker.service.game.GameConverter;
import davis.gametracker.service.game.GameService;

/**
 * CRUD service implementation for {@link Game} entities
 * 
 * @author Adam Davis
 */
@Service
public class GameServiceImpl implements GameService
{
	private final Logger	log	= LoggerFactory.getLogger(getClass());

	private GameConverter	converter;
	private GameRepository	repository;

	@Autowired
	public GameServiceImpl(GameConverter converter, GameRepository repository)
	{
		Objects.requireNonNull(converter);
		Objects.requireNonNull(repository);

		this.converter = converter;
		this.repository = repository;
	}

	@Override
	public Game create(GameData newGame)
	{
		Objects.requireNonNull(newGame);

		return repository.saveAndFlush(converter.toRecord(newGame));
	}

	@Override
	public Game update(Integer primaryKey, GameData contents) throws IllegalArgumentException
	{
		Objects.requireNonNull(primaryKey);
		Objects.requireNonNull(contents);

		Game existingGame = load(primaryKey);

		if (existingGame == null)
		{
			log.error("Invalid key passed to update: {}", primaryKey);

			throw new IllegalArgumentException("Given key is invalid");
		}

		converter.populate(existingGame, contents);

		return repository.saveAndFlush(existingGame);
	}

	@Override
	public Game load(Integer primaryKey) throws IllegalArgumentException
	{
		Objects.requireNonNull(primaryKey);

		Game loadedGame = repository.findOne(primaryKey);

		if (loadedGame == null)
		{
			log.error("Invalid key passed to load: {}", primaryKey);

			throw new IllegalArgumentException("Given key is invalid");
		}

		return loadedGame;
	}

	@Override
	public List<Game> list()
	{
		return repository.findAll(new Sort(EntityBase.DEFAULT_SORT_COLUMN));
	}

}
