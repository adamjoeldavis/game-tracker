package davis.gametracker.repository;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import davis.gametracker.domain.db.GameSystem;

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
	public void testUniqueNaturalId()
	{
		final String duplicateId = "PS4";

		repository.saveAndFlush(new GameSystem(duplicateId));
		try
		{
			repository.saveAndFlush(new GameSystem(duplicateId));
			Assert.fail();
		} catch (Exception exception)
		{
			; // expected
		}
	}

	@Test
	public void testFindById()
	{
		final String expectedId = "PS4";
		final String unexpectedId = "PS3";

		repository.saveAndFlush(new GameSystem(expectedId));
		repository.saveAndFlush(new GameSystem(unexpectedId));

		Assert.assertEquals(2, repository.count());

		List<GameSystem> foundSystems = repository.findById(expectedId);

		Assert.assertEquals(1, foundSystems.size());
		Assert.assertEquals(expectedId, foundSystems.get(0).getId());
	}
}
