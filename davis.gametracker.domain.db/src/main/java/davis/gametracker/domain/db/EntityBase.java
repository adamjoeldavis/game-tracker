package davis.gametracker.domain.db;

import javax.persistence.Id;
import javax.persistence.Version;

public abstract class EntityBase<E extends EntityBase<E, K>, K>
{
	@Id
	private K		primaryKey;

	@Version
	private long	version;

	public K getPrimaryKey()
	{
		return primaryKey;
	}

	public long getVersion()
	{
		return version;
	}
}
