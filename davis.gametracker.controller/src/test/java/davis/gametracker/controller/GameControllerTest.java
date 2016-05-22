package davis.gametracker.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
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
import davis.gametracker.controller.game.GameController;
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
    /**
     * Names of the default game entries to add before each test
     */
    private static final List<String> DEFAULT_GAMES  = Lists.newArrayList("Bloodborne", "GTA V");

    /**
     * ID of the default game system that each entry in {@link #DEFAULT_GAMES}
     * will be assiged to
     */
    private static final String       DEFAULT_SYSTEM = "PS4";

    /**
     * ID of an existing, but unused, GameSystem entry. Useful for testing
     * modifications to existing Game records
     */
    private static final String       UNUSED_SYSTEM  = "PS3";

    @Autowired
    private GameRepository            gameRepository;

    @Autowired
    private GameSystemRepository      systemRepository;

    @Autowired
    private GameController            gameController;

    /**
     * Mapping of {@link Game#getName()} => {@link Game#getPrimaryKey()} for
     * each entry in {@link #DEFAULT_GAMES} that was added during
     * {@link #loadData()}. Used to map of record keys to known records without
     * having to rely on the underlying database's identity creation mechanism.
     */
    private Map<String, Integer>      defaultGameRecords;

    /**
     * Loads default data before every test. Will create a {@link GameSystem}
     * record for both {@link #DEFAULT_SYSTEM} and {@link #UNUSED_SYSTEM}. Will
     * also create {@link Game} records for each {@link #DEFAULT_GAMES} entry.
     */
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
        List<GameData> games = gameController.list();

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

        GameData loadedGame = gameController.get(gameKey);

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

        try
        {
            gameController.get(falseGameKey);
            fail();
        } catch (IllegalArgumentException exception)
        {
            ; // expected
        }
    }

    @Test
    public void testAddGame_Success()
    {
        final String gameName = "Dark Souls III";
        GameData newGame = new GameData(gameName);
        newGame.getOwnedOn().add(new GameSystemData(DEFAULT_SYSTEM));

        GameData savedVersion = gameController.add(newGame);

        standardAssertions(savedVersion, gameName, DEFAULT_SYSTEM);

        List<GameData> games = gameController.list();

        assertEquals(DEFAULT_GAMES.size() + 1, games.size());
    }

    @Test
    public void testAddGame_Duplicate()
    {
        GameData duplicateGame = new GameData(DEFAULT_GAMES.get(0));

        try
        {
            gameController.add(duplicateGame);
            fail();
        } catch (Exception exception)
        {
            ; // expected
        }
    }

    @Test
    public void testUpdateGame_Success()
    {
        GameData existingGame = new GameData(DEFAULT_GAMES.get(0));
        existingGame.getOwnedOn().add(new GameSystemData(DEFAULT_SYSTEM));

        // this is the modification
        existingGame.getOwnedOn().add(new GameSystemData(UNUSED_SYSTEM));

        GameData savedVersion = gameController
                .update(defaultGameRecords.get(existingGame.getName()), existingGame);

        standardAssertions(savedVersion, DEFAULT_GAMES.get(0), UNUSED_SYSTEM, DEFAULT_SYSTEM);
    }

    /**
     * Perform a set of standard assertions against the given GameDat object.
     * Checks the following:
     * <ul>
     * <li>game is not null
     * <li>the game's name matches exactly the given name
     * <li>the game's list of "ownedOn" systems matches the given systems list,
     * in the same order
     * </ul>
     * 
     * @param game
     *            object on which to assert
     * @param name
     *            expected exact name of the given object
     * @param systems
     *            expected list of GameSystem IDs that this game is "ownedOn",
     *            in the correct order
     */
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
