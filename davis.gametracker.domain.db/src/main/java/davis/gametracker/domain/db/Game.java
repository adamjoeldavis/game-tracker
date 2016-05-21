package davis.gametracker.domain.db;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;

@Entity
public class Game extends EntityBase<Game, Integer>
{
	private String		name;

	private String		description;

	@ManyToMany
	@OrderBy("id")
	private Set<System>	ownedOn	= new TreeSet<>(Comparator.comparing(System::getId));

	public Game()
	{
		;
	}

	public Game(String name)
	{
		setName(name);
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public Game ownedOn(System system)
	{
		ownedOn.add(system);

		return this;
	}
}
