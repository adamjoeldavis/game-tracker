package davis.gametracker.domain.db;

import javax.persistence.Entity;

@Entity
public class System extends EntityBase<System, Integer>
{
	private String	name;

	private String	id;

	private String	description;

	public System()
	{
		;
	}

	public System(String id, String name)
	{
		setId(id);
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

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}
}
