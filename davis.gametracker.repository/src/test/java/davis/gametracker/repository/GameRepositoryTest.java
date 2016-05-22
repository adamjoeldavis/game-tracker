package davis.gametracker.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Lists;

import davis.gametracker.domain.db.Game;
import davis.gametracker.domain.db.GameSystem;

/**
 * Integration tests for the {@link GameRepository} class
 * 
 * @author Adam Davis
 */
public class GameRepositoryTest extends RepositoryTestBase
{
    @Autowired
    private GameRepository       gameRepository;

    @Autowired
    private GameSystemRepository systemRepository;

    @Test
    public void testInjection()
    {
        ; // will fail if bean wasn't injected correctly
    }

    @Test
    public void testFindBySystemId()
    {
        // prepare for the test by inserting data
        GameSystem ps4 = systemRepository.save(new GameSystem("PS4"));
        GameSystem xbox360 = systemRepository.save(new GameSystem("X360"));
        systemRepository.flush();

        Game bloodborne = new Game("Bloodborne").ownedOn(ps4);
        Game callOfDuty = new Game("Call of Duty").ownedOn(ps4).ownedOn(xbox360);
        Game gtaV = new Game("Grand Theft Auto V").ownedOn(ps4).ownedOn(xbox360);
        Game fable = new Game("Fable").ownedOn(xbox360);

        gameRepository.save(Lists.newArrayList(bloodborne, callOfDuty, gtaV, fable));
        gameRepository.flush(); // make sure they're committed

        assertEquals(4, gameRepository.count());

        List<Game> ps4Games = gameRepository.findByOwnedOn_Id(ps4.getId());
        assertEquals(3, ps4Games.size());

        // make sure all the correct games are there
        assertTrue(
                ps4Games.stream().anyMatch(g -> g.getName().equals(bloodborne.getName())));
        assertTrue(
                ps4Games.stream().anyMatch(g -> g.getName().equals(callOfDuty.getName())));
        assertTrue(ps4Games.stream().anyMatch(g -> g.getName().equals(gtaV.getName())));
    }

    @Test
    public void testUniqueNaturalId_PreventsSave()
    {
        final String duplicateId = "Bloodborne";

        gameRepository.saveAndFlush(new Game(duplicateId));
        try
        {
            gameRepository.saveAndFlush(new Game(duplicateId));
            fail();
        } catch (Exception exception)
        {
            ; // expected
        }
    }
}
