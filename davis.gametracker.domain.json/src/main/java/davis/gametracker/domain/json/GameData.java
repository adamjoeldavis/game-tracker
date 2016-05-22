package davis.gametracker.domain.json;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GameData
{
	private String					name;

	private List<GameSystemData>	ownedOn	= new ArrayList<>();

	@SuppressWarnings("unused")
	private GameData()
	{
		; // for jackson
	}

	public GameData(String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return name;
	}

	public List<GameSystemData> getOwnedOn()
	{
		return ownedOn;
	}

	public GameData ownedOn(GameSystemData data)
	{
		getOwnedOn().add(data);

		return this;
	}

	public GameData setOwnedOn(List<GameSystemData> ownedOn)
	{
		if (ownedOn == null)
		{
			ownedOn = new ArrayList<>();
		}

		this.ownedOn = ownedOn;

		return this;
	}

	@Override
	public boolean equals(Object other)
	{
		if (this == other)
		{
			return true;
		}

		if (other == null || getClass() != other.getClass())
		{
			return false;
		}

		return Objects.equals(getName(), ((GameData) other).getName());
	}

	@Override
	public int hashCode()
	{
		return Objects.hashCode(getName());
	}
}
