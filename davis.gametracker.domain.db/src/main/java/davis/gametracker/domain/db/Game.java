package davis.gametracker.domain.db;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;

@Entity
public class Game extends EntityBase<Game, Integer, String>
{
	private String			description;

	@ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	@OrderBy("id")
	private Set<GameSystem>	ownedOn	= new TreeSet<>(Comparator.comparing(GameSystem::getId));

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

	public String getDescription()
	{
		return description;
	}

	public Game setDescription(String description)
	{
		this.description = description;

		return this;
	}

	public Game ownedOn(GameSystem system)
	{
		ownedOn.add(system);

		return this;
	}
}
