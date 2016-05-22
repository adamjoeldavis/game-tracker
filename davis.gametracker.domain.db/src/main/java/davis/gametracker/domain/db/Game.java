package davis.gametracker.domain.db;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;

/**
 * Game entity
 * 
 * @author Adam Davis
 */
@Entity
public class Game extends EntityBase<Game, Integer, String>
{
    /**
     * Mapping of all systems this game is owned on. Saving does not cascade, so
     * any new systems present in this list would have to have been saved by
     * themselves beforehand.
     */
    @ManyToMany
    @OrderBy("id")
    private Set<GameSystem> ownedOn = new TreeSet<>(Comparator.comparing(GameSystem::getId));

    protected Game()
    {
        ; // for hibernate
    }

    public Game(String name)
    {
        setId(name);
    }

    public String getName()
    {
        return getId();
    }

    public Game ownedOn(GameSystem system)
    {
        ownedOn.add(system);

        return this;
    }

    public Set<GameSystem> getOwnedOn()
    {
        return ownedOn;
    }
}
