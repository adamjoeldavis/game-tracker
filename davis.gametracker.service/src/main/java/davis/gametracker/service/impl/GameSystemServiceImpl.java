package davis.gametracker.service.impl;

import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import davis.gametracker.domain.db.EntityBase;
import davis.gametracker.domain.db.GameSystem;
import davis.gametracker.domain.json.GameSystemData;
import davis.gametracker.repository.GameSystemRepository;
import davis.gametracker.service.gamesystem.GameSystemConverter;
import davis.gametracker.service.gamesystem.GameSystemService;

/**
 * CRUD service implementation for {@link GameSystem} entities
 * 
 * @author Adam Davis
 */
@Service
public class GameSystemServiceImpl implements GameSystemService
{
    private Logger               log = LoggerFactory.getLogger(getClass());

    private GameSystemConverter  converter;
    private GameSystemRepository repository;

    @Autowired
    public GameSystemServiceImpl(GameSystemConverter converter, GameSystemRepository repository)
    {
        Objects.requireNonNull(converter);
        Objects.requireNonNull(repository);

        this.converter = converter;
        this.repository = repository;
    }

    @Override
    public GameSystem create(GameSystemData data)
    {
        Objects.requireNonNull(data);

        return repository.saveAndFlush(converter.toRecord(data));
    }

    @Override
    public GameSystem update(Integer key, GameSystemData data) throws IllegalArgumentException
    {
        Objects.requireNonNull(key);
        Objects.requireNonNull(data);

        GameSystem system = load(key);

        if (system == null)
        {
            log.error("Invalid key passed to update: {}", key);

            throw new IllegalArgumentException("Given key is invalid");
        }

        converter.populate(system, data);

        return repository.saveAndFlush(system);
    }

    @Override
    public GameSystem load(Integer key) throws IllegalArgumentException
    {
        Objects.requireNonNull(key);

        GameSystem loadedSystem = repository.findOne(key);

        if (loadedSystem == null)
        {
            log.error("Invalid key passed to load: {}", key);

            throw new IllegalArgumentException("Given key is invalid");
        }

        return loadedSystem;
    }

    @Override
    public GameSystem load(String id) throws IllegalArgumentException
    {
        Objects.requireNonNull(id);

        GameSystem loadedSystem = repository.findById(id);

        if (loadedSystem == null)
        {
            log.error("Invalid ID passed to load: {}", id);

            throw new IllegalArgumentException("Given ID is invalid");
        }

        return loadedSystem;
    }

    @Override
    public List<GameSystem> list()
    {
        return repository.findAll(new Sort(EntityBase.DEFAULT_SORT_COLUMN));
    }

}
