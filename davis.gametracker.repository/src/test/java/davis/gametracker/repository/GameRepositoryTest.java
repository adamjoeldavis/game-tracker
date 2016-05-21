package davis.gametracker.repository;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Lists;

import davis.gametracker.domain.db.Game;
import davis.gametracker.domain.db.GameSystem;

public class GameRepositoryTest extends RepositoryTestBase
{
	@Autowired
	private GameRepository repository;

	@Test
	public void testInjection()
	{
		; // will fail if bean wasn't injected correctly
	}

	@Test
	public void testFindBySystemId()
	{
		// insert a few records
		GameSystem ps4 = new GameSystem("PS4");
		GameSystem xbox360 = new GameSystem("X1");

		Game bloodborne = new Game("Bloodborne").ownedOn(ps4);
		Game callOfDuty = new Game("Call of Duty").ownedOn(ps4).ownedOn(xbox360);
		Game gtaV = new Game("Grand Theft Auto V").ownedOn(ps4).ownedOn(xbox360);
		Game fable = new Game("Fable").ownedOn(xbox360);

		repository.save(Lists.newArrayList(bloodborne, callOfDuty, gtaV, fable));
		repository.flush(); // make sure they're committed

		Assert.assertEquals(4, repository.count());

		List<Game> ps4Games = repository.findByOwnedOn_Id(ps4.getId());
		Assert.assertEquals(3, ps4Games.size());

		// make sure all the correct games are there
		Assert.assertTrue(
				ps4Games.stream().anyMatch(g -> g.getName().equals(bloodborne.getName())));
		Assert.assertTrue(
				ps4Games.stream().anyMatch(g -> g.getName().equals(callOfDuty.getName())));
		Assert.assertTrue(ps4Games.stream().anyMatch(g -> g.getName().equals(gtaV.getName())));
	}

	@Test
	public void testUniqueNaturalId()
	{
		final String duplicateId = "Bloodborne";

		repository.saveAndFlush(new Game(duplicateId));
		try
		{
			repository.saveAndFlush(new Game(duplicateId));
			Assert.fail();
		} catch (Exception exception)
		{
			; // expected
		}
	}
}
