package davis.gametracker.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import davis.gametracker.domain.db.GameSystem;

/**
 * Integration tests for the {@link GameSystemRepository} class
 * 
 * @author Adam Davis
 */
public class GameSystemRepositoryTest extends RepositoryTestBase
{
	@Autowired
	private GameSystemRepository repository;

	@Test
	public void testInjection()
	{
		; // will fail if the bean wasn't auto-wired correctly
	}

	@Test
	public void testUniqueNaturalId_PreventsSave()
	{
		final String duplicateId = "PS4";

		repository.saveAndFlush(new GameSystem(duplicateId));
		try
		{
			repository.saveAndFlush(new GameSystem(duplicateId));
			fail();
		} catch (Exception exception)
		{
			; // expected
		}
	}

	@Test
	public void testFindById()
	{
		// prepare for the test by inserting some data
		final String expectedId = "PS4";
		final String unexpectedId = "PS3";

		repository.saveAndFlush(new GameSystem(expectedId));
		repository.saveAndFlush(new GameSystem(unexpectedId));

		assertEquals(2, repository.count());

		GameSystem foundSystem = repository.findById(expectedId);

		assertEquals(expectedId, foundSystem.getId());
	}
}
