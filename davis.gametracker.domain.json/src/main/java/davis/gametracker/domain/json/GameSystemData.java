package davis.gametracker.domain.json;

import java.util.Objects;

/**
 * View of a GameSystem entity
 * 
 * @author Adam Davis
 */
public class GameSystemData
{
    private String id;

    private String name;

    /**
     * This is necessary for jackson deserialization
     */
    @SuppressWarnings("unused")
    private GameSystemData()
    {
        ;
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
