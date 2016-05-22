package davis.gametracker.domain.db;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;

@Entity
public class Game extends EntityBase<Game, Integer, String>
{
	@ManyToMany
	@OrderBy("id")
	private Set<GameSystem> ownedOn = new TreeSet<>(Comparator.comparing(GameSystem::getId));

	protected Game()
	{
		; // for hibernate
	}

	public Game(String name)
	{
		setId(name);
	}

	public String getName()
	{
		return getId();
	}

	public Game ownedOn(GameSystem system)
	{
		ownedOn.add(system);

		return this;
	}

	public Set<GameSystem> getOwnedOn()
	{
		return ownedOn;
	}
}
