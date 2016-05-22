package davis.gametracker.domain.json;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude
public class GameData
{
	@JsonProperty
	private String				name;

	@JsonProperty
	private String				description;

	@JsonProperty
	private Set<GameSystemData>	ownedOn	= new HashSet<>();

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

	public Set<GameSystemData> getOwnedOn()
	{
		return ownedOn;
	}

	public GameData ownedOn(GameSystemData data)
	{
		getOwnedOn().add(data);

		return this;
	}

	public GameData setOwnedOn(Set<GameSystemData> ownedOn)
	{
		if (ownedOn == null)
		{
			ownedOn = new HashSet<>();
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
