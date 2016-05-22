package davis.gametracker.domain.db;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import org.hibernate.annotations.NaturalId;

@MappedSuperclass
public abstract class EntityBase<E extends EntityBase<E, K, ID>, K, ID>
{
	public static final String	DEFAULT_SORT_COLUMN	= "id";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private K					primaryKey;

	@Version
	private long				version;

	@NaturalId
	@Column(insertable = true, updatable = false, nullable = false)
	private ID					id;

	public K getPrimaryKey()
	{
		return primaryKey;
	}

	public long getVersion()
	{
		return version;
	}

	public ID getId()
	{
		return id;
	}

	protected void setId(ID id)
	{
		this.id = id;
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

		return Objects.equals(getId(), ((EntityBase<?, ?, ?>) other).getId());
	}

	@Override
	public int hashCode()
	{
		return getId() == null ? 0 : getId().hashCode();
	}
}
