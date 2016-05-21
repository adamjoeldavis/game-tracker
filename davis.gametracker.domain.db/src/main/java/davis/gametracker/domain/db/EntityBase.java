package davis.gametracker.domain.db;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

@MappedSuperclass
public abstract class EntityBase<E extends EntityBase<E, K>, K>
{
	@Id
	@GeneratedValue
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
