package davis.gametracker.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

import davis.gametracker.controller.config.TestModuleConfiguration;
import davis.gametracker.domain.db.Game;
import davis.gametracker.domain.db.GameSystem;
import davis.gametracker.domain.json.GameData;
import davis.gametracker.domain.json.GameSystemData;
import davis.gametracker.repository.GameRepository;
import davis.gametracker.repository.GameSystemRepository;

/**
 * Integration Tests for the {@link GameController} class
 * 
 * @author Adam Davis
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TestModuleConfiguration.class)
@Transactional
public class GameControllerTest
{
	private static final List<String>	DEFAULT_GAMES	= Lists.newArrayList("Bloodborne", "GTA V");
	private static final String			DEFAULT_SYSTEM	= "PS4";
	private static final String			UNUSED_SYSTEM	= "PS3";

	@Autowired
	private GameRepository				gameRepository;

	@Autowired
	private GameSystemRepository		systemRepository;

	@Autowired
	private GameController				controller;

	private Map<String, Integer>		defaultGameRecords;

	@Before
	public void loadData()
	{
		GameSystem defaultSystem = systemRepository.save(new GameSystem(DEFAULT_SYSTEM));
		systemRepository.save(new GameSystem(UNUSED_SYSTEM));
		systemRepository.flush();

		List<Game> savedGames = gameRepository.save(DEFAULT_GAMES.stream()
				.map(Game::new)
				.map(g -> g.ownedOn(defaultSystem))
				.collect(Collectors.toList()));

		gameRepository.flush();

		defaultGameRecords = savedGames.stream()
				.collect(Collectors.toMap(Game::getName, Game::getPrimaryKey));
	}

	@Test
	public void testListGames()
	{
		List<GameData> games = controller.listGames();

		assertEquals(games.size(), DEFAULT_GAMES.size());

		for (int i = 0; i < games.size(); i++)
		{
			standardAssertions(games.get(i), DEFAULT_GAMES.get(i), DEFAULT_SYSTEM);
		}
	}

	@Test
	public void testGetGame_ExistingRecord()
	{
		Integer gameKey = defaultGameRecords.get(DEFAULT_GAMES.get(0));

		GameData loadedGame = controller.getGame(gameKey);

		standardAssertions(loadedGame, DEFAULT_GAMES.get(0), DEFAULT_SYSTEM);
	}

	@Test
	public void testGetGame_NoMatch()
	{
		Collection<Integer> existingKeys = defaultGameRecords.values();

		Integer falseGameKey = 1;
		// make sure we have a unique key - should never run more than twice but
		// cut it off at 5 just to be safe
		for (int i = 0; i < 5 && existingKeys.contains(falseGameKey); i++, falseGameKey++)
		{
			;
		}

		GameData loadedGame = controller.getGame(falseGameKey);

		assertNull(loadedGame);
	}

	@Test
	public void testAddGame()
	{
		final String gameName = "Dark Souls III";
		GameData newGame = new GameData(gameName);
		newGame.getOwnedOn().add(new GameSystemData(DEFAULT_SYSTEM));

		GameData savedVersion = controller.addGame(newGame);

		standardAssertions(savedVersion, gameName, DEFAULT_SYSTEM);

		List<GameData> games = controller.listGames();

		assertEquals(DEFAULT_GAMES.size() + 1, games.size());
	}

	@Test
	public void testAddGame_Duplicate()
	{
		GameData duplicateGame = new GameData(DEFAULT_GAMES.get(0));

		try
		{
			controller.addGame(duplicateGame);
			fail();
		} catch (Exception exception)
		{
			; // expected
		}
	}

	@Test
	public void testUpdateGame()
	{
		GameData existingGame = new GameData(DEFAULT_GAMES.get(0));
		existingGame.getOwnedOn().add(new GameSystemData(DEFAULT_SYSTEM));

		// this is the modification
		existingGame.getOwnedOn().add(new GameSystemData(UNUSED_SYSTEM));

		GameData savedVersion = controller
				.updateGame(defaultGameRecords.get(existingGame.getName()), existingGame);

		standardAssertions(savedVersion, DEFAULT_GAMES.get(0), UNUSED_SYSTEM, DEFAULT_SYSTEM);
	}

	private static void standardAssertions(GameData game, String name, String... systems)
	{
		assertNotNull(game);
		assertEquals(name, game.getName());
		assertEquals(systems.length, game.getOwnedOn().size());

		Iterator<GameSystemData> ownedOnIterator = game.getOwnedOn().iterator();
		int index = 0;
		while (ownedOnIterator.hasNext())
		{
			assertEquals(systems[index++], ownedOnIterator.next().getId());
		}
	}
}
