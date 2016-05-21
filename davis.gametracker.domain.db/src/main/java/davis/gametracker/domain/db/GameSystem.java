package davis.gametracker.domain.db;

import javax.persistence.Entity;

@Entity
public class GameSystem extends EntityBase<GameSystem, Integer, String>
{
	private String	name;

	private String	description;

	protected GameSystem()
	{
		; // for hibernate
	}

	public GameSystem(String id)
	{
		setId(id);
	}

	public String getName()
	{
		return name;
	}

	public GameSystem setName(String name)
	{
		this.name = name;

		return this;
	}

	public String getDescription()
	{
		return description;
	}

	public GameSystem setDescription(String description)
	{
		this.description = description;

		return this;
	}
}
