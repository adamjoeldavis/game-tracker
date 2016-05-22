package davis.gametracker.domain.db;

import javax.persistence.Entity;

@Entity
public class GameSystem extends EntityBase<GameSystem, Integer, String>
{
	private String name;

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
}
