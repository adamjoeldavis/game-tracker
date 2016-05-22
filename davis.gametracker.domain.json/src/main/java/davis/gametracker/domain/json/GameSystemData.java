package davis.gametracker.domain.json;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude
public class GameSystemData
{
	@JsonProperty
	private String	id;

	@JsonProperty
	private String	name;

	@SuppressWarnings("unused")
	private GameSystemData()
	{
		; // for jackson
	}

	public GameSystemData(String id)
	{
		this.id = id;
	}

	public String getId()
	{
		return id;
	}

	public String getName()
	{
		return name;
	}

	public GameSystemData setName(String name)
	{
		this.name = name;

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

		return Objects.equals(getId(), ((GameSystemData) other).getId());
	}

	@Override
	public int hashCode()
	{
		return Objects.hashCode(getId());
	}
}
